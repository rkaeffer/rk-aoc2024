package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Day6 {

    public static long getNbOfVisitPosition(List<String> input) {
        PositionGrid pg = new PositionGrid(input);
        List<VisitCoord> vcs = pg.getVisitPath();
        return vcs.stream().filter(vc -> !vc.duplicate).count();
    }

    public static long getNbOfStuckPosition(List<String> input) {
        long nbStuck = 0;
        PositionGrid pg = new PositionGrid(input);
        List<VisitCoord> vcs = pg.getVisitPath();

        for(VisitCoord vc : vcs) {
            if(!vc.duplicate) {
                if(pg.grid[vc.c.y][vc.c.x] == '.') {
                    PositionGrid pgClone = new PositionGrid(pg);
                    pgClone.grid[vc.c.y][vc.c.x] = '#';
                    pgClone.getVisitPath();
                    if(pgClone.isStuck) {
                        nbStuck++;
                    }
                }
            }
        }
        return nbStuck;
    }

    static class PositionGrid {

        char[][] grid;

        int startx;
        int starty;

        Direction startDirection;

        boolean isStuck;

        PositionGrid(List<String> input) {
            this.grid = new char[input.size()][input.get(0).length()];
            this.isStuck = false;
            for(int i=0; i<input.size(); i++) {
                for (int j=0; j<input.get(i).length(); j++) {
                    if(input.get(i).charAt(j) == '^') {
                        this.startx = j;
                        this.starty = i;
                        this.startDirection = Direction.UP;
                        this.grid[i][j] = '.';
                    } else {
                        this.grid[i][j] = input.get(i).charAt(j);
                    }
                }
            }
        }

        public PositionGrid(PositionGrid pg) {
            this.startx = pg.startx;
            this.starty = pg.starty;
            this.startDirection = pg.startDirection;
            this.grid = new char[pg.grid.length][];
            for(int i = 0; i < pg.grid.length; i++) {
                this.grid[i] = pg.grid[i].clone();
            }
            this.isStuck = false;
        }

        public List<VisitCoord> getVisitPath() {
            List<VisitCoord> vcs = new ArrayList<>();
            vcs.add(new VisitCoord(new Coord(startx, starty), startDirection));
            int curx = startx;
            int cury = starty;
            Direction curdir = startDirection;
            boolean allVisited = false;
            while(!allVisited)
            {
                int newx = curx+curdir.x;
                int newy = cury+curdir.y;
                if(validCoord(newx, newy)) {
                    VisitCoord newVc = new VisitCoord(new Coord(newx, newy), curdir);
                    for(VisitCoord vc : vcs) {
                        if(vc.equals(newVc)) {
                            allVisited = true;
                            newVc.duplicate = true;
                            this.isStuck = true;
                            break;
                        }
                    }
                    for(VisitCoord vc : vcs) {
                        if(newVc.c.equals(vc.c)) {
                            newVc.duplicate = true;
                            break;
                        }
                    }
                    vcs.add(newVc);
                    curx = newx;
                    cury = newy;
                } else {
                    if(newx < 0 || newx >= grid[0].length || newy < 0 || newy >= grid.length) {
                        allVisited = true;
                    } else {
                        switch (curdir) {
                            case UP:
                                curdir = Direction.RIGHT;
                                break;
                            case RIGHT:
                                curdir = Direction.DOWN;
                                break;
                            case DOWN:
                                curdir = Direction.LEFT;
                                break;
                            case LEFT:
                                curdir = Direction.UP;
                                break;
                        }
                    }
                }
            }
            return vcs;
        }

        public boolean validCoord(int x, int y) {
            return x >= 0 && x<grid[0].length && y>=0 && y<grid.length && grid[y][x] != '#';
        }
    }

    static enum Direction {
        UP(0,-1),
        DOWN(0,1),
        LEFT(-1,0),
        RIGHT(1,0);
        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Coord c) {
            return this.x == c.x && this.y == c.y;
        }
    }

    static class VisitCoord {

        Coord c;
        Direction d;

        boolean duplicate;

        VisitCoord(Coord c, Direction d) {
            this.c = c;
            this.d = d;
            this.duplicate = false;
        }

        public boolean equals(VisitCoord vc) {
            return vc.c.x == this.c.x && vc.c.y == this.c.y && vc.d == this.d;
        }
    }


}
