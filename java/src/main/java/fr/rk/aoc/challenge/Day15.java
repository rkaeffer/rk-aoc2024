package fr.rk.aoc.challenge;

import javax.management.DescriptorRead;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Day15 {

    public static long getGridScore(List<String> input) {
        Grid g = parseGrid(input);
        List<Direction> instr = parseDirections(input);
        for (Direction d : instr) {
            g.applyDirection(d, false);
            //  g.printGrid(false);
        }
        return g.calculateScore();
    }

    public static long getDoubleGridScore(List<String> input) {
        Grid g = parseGrid(input).doubleGrid();
        List<Direction> instr = parseDirections(input);
        g.printGrid(true);
        for (Direction d : instr) {
            g.applyDirection(d, true);
            g.printGrid(true);
        }
        return -1L;
        // return g.calculateScore();
    }

    private static Grid parseGrid(List<String> input) {
        int nbLine = 0;
        int robotX = 0;
        int robotY = 0;
        while (!Objects.equals(input.get(nbLine), "")) {
            nbLine++;
        }
        Cell[][] cells = new Cell[nbLine][input.get(0).length()];
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                char cur = input.get(i).charAt(j);
                if (cur == '@') {
                    robotX = j;
                    robotY = i;
                }
                cells[i][j] = new Cell(j, i, cur);
            }
        }
        return new Grid(cells, robotX, robotY);
    }

    private static List<Direction> parseDirections(List<String> input) {
        List<Direction> directions = new ArrayList<>();
        int i = 0;
        while (!Objects.equals(input.get(i), "")) {
            i++;
        }
        for (int j = i + 1; j < input.size(); j++) {
            for (char c : input.get(j).toCharArray()) {
                directions.add(parseDirection(c));
            }
        }
        return directions;
    }

    private static Direction parseDirection(char c) {
        switch (c) {
            case '^':
                return Direction.UP;
            case '>':
                return Direction.RIGHT;
            case '<':
                return Direction.LEFT;
            default:
                return Direction.DOWN;
        }
    }

    static class Grid {
        int robotx;
        int roboty;
        Cell[][] cells;

        public Grid(Cell[][] cells, int x, int y) {
            this.cells = cells;
            this.robotx = x;
            this.roboty = y;
        }

        public Grid doubleGrid() {
            Cell[][] doubleCell = new Cell[cells.length][cells[0].length * 2];
            for (int i = 0; i < doubleCell.length; i++) {
                for (int j = 0; j < doubleCell[i].length; j++) {
                    Cell curCell = cells[i][j / 2];
                    if (curCell.isBox) {
                        doubleCell[i][j] = new Cell(j, i, 'O');
                        doubleCell[i][j].isOpenBox = true;
                        doubleCell[i][j].isCloseBox = false;
                        doubleCell[i][j + 1] = new Cell(j + 1, i, 'O');
                        doubleCell[i][j + 1].isOpenBox = false;
                        doubleCell[i][j + 1].isCloseBox = true;
                    } else if (curCell.isWall) {
                        doubleCell[i][j] = new Cell(j, i, '#');
                        doubleCell[i][j + 1] = new Cell(j + 1, i, '#');
                    } else {
                        doubleCell[i][j] = new Cell(j, i, '.');
                        doubleCell[i][j + 1] = new Cell(j + 1, i, '.');
                    }
                    j++;
                }
            }
            return new Grid(doubleCell, this.robotx * 2, this.roboty);
        }

        public long calculateScore() {
            long score = 0L;
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j].isBox) {
                        score += 100L * i;
                        score += j;
                    }
                }
            }
            return score;
        }

        public void printGrid(boolean doubleBox) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (j == robotx && i == roboty) {
                        System.out.print("@");
                    } else {
                        Cell c = cells[i][j];
                        if (!doubleBox) {
                            System.out.print(c.isWall ? '#' : c.isBox ? 'O' : '.');
                        } else {
                            System.out.print(c.isWall ? '#' : c.isBox ? c.isOpenBox ? '[' : ']' : '.');
                        }
                    }
                }
                System.out.println();
            }
            System.out.println();
        }


        public void applyDirection(Direction d, boolean doubleCell) {
            List<Cell> nextCells = doubleCell ? getNextFreeDoubleCell(this.robotx, this.roboty, d) : getNextFreeCell(this.robotx, this.roboty, d);
            if (nextCells.size() == 1 && !nextCells.get(0).isWall) {
                roboty += d.y;
                robotx += d.x;
            } else if (nextCells.size() > 1 && !nextCells.get(nextCells.size() - 1).isWall) {
                nextCells.get(0).isBox = false;
                nextCells.get(nextCells.size() - 1).isBox = true;
                roboty += d.y;
                robotx += d.x;
                if(doubleCell && (d == Direction.LEFT || d == Direction.RIGHT)) {
                    nextCells.get(0).isOpenBox = false;
                    nextCells.get(0).isCloseBox = false;
                    nextCells.get(nextCells.size() - 1).isCloseBox = d == Direction.RIGHT;
                    nextCells.get(nextCells.size() - 1).isOpenBox = d == Direction.LEFT;
                    for(int i=1; i<nextCells.size() - 1; i++) {
                        nextCells.get(i).isCloseBox = !nextCells.get(i).isCloseBox;
                        nextCells.get(i).isOpenBox = !nextCells.get(i).isOpenBox;
                    }
                }
            }
        }

        private List<Cell> getNextFreeCell(int curX, int curY, Direction d) {
            List<Cell> toMoves = new ArrayList<>();
            int nextX = curX + d.x;
            int nextY = curY + d.y;
            while (isPosSafe(nextX, nextY, this.cells)) {
                toMoves.add(cells[nextY][nextX]);
                if (cells[nextY][nextX].isWall || (!cells[nextY][nextX].isBox && !cells[nextY][nextX].isWall)) {
                    return toMoves;
                }
                nextX = nextX + d.x;
                nextY = nextY + d.y;
            }
            return toMoves;
        }

        private List<Cell> getNextFreeDoubleCell(int curX, int curY, Direction d) {
            List<Cell> toMoves = new ArrayList<>();
            int nextX = curX + d.x;
            int nextY = curY + d.y;
            if (d == Direction.RIGHT || d == Direction.LEFT) {
                while (isPosSafe(nextX, nextY, this.cells)) {
                    toMoves.add(cells[nextY][nextX]);
                    if (cells[nextY][nextX].isWall || (!cells[nextY][nextX].isBox && !cells[nextY][nextX].isWall)) {
                        return toMoves;
                    }
                    nextX = nextX + d.x;
                    nextY = nextY + d.y;
                }
            } else {
                //TODO recursive "pyramide"
            }
            return toMoves;
        }
    }

    static class Cell {
        int x;
        int y;
        boolean isBox;

        boolean isOpenBox;
        boolean isCloseBox;
        boolean isWall;

        public Cell(int x, int y, char c) {
            this.x = x;
            this.y = y;
            if (c == '#') {
                this.isWall = true;
                this.isBox = false;
            } else if (c == 'O') {
                this.isWall = false;
                this.isBox = true;
            } else {
                this.isWall = false;
                this.isBox = false;
            }
        }
    }

    static enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);
        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isPosSafe(int x, int y, Cell[][] cells) {
        return x >= 0 && y >= 0 && x < cells[0].length && y < cells.length;
    }
}
