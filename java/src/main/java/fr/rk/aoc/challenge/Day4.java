package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;

public final class Day4 {

    static int[] xDir = { -1, -1, -1, 0, 0, 1, 1, 1};
    static int[] yDir = { -1, 0, 1, -1, 1, -1, 0, 1};

    static int[] xDirDiag = { -1, -1, 1, 1};
    static int[] yDirDiag = { -1, 1, -1, 1};

    public static long findSearchWordX(List<String> input) {
        char[][] grid = parseGrid(input);
        long nbXmas = 0;

        List<Coord> match = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                for (int k = 0; k < 4 ; k++) {
                    if (findWord(0, "MAS", grid, i, j, xDirDiag[k], yDirDiag[k])) {
                        match.add(new Coord(i+xDirDiag[k], j+yDirDiag[k]));
                    }
                }
                grid[i][j] = input.get(i).charAt(j);
            }
        }

        for (int i = 0; i <match.size(); i++) {
            for (int j = 0; j <match.size() ; j++) {
                Coord c1 = match.get(i);
                Coord c2 = match.get(j);
                if(c1 != c2 && c1.x == c2.x && c1.y == c2.y) {
                    nbXmas++;
                    match.remove(c1);
                    match.remove(c2);
                    i--;
                    break;
                }
            }
        }

        return nbXmas;
    }

    public static long findSearchWord(List<String> input) {
        char[][] grid = parseGrid(input);
        long nbXmas = 0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                for (int k = 0; k < 8 ; k++) {
                    if (findWord(0, "XMAS", grid, i, j, xDir[k], yDir[k])) {
                        nbXmas++;
                    }
                }
                grid[i][j] = input.get(i).charAt(j);
            }
        }
        return nbXmas;
    }

    public static boolean findWord(int index, String word, char[][] grid, int x, int y, int dirX, int dirY) {
        if(index == word.length()) {
            return true;
        }

        if(validCoord(x, y, grid.length, grid[0].length) && word.charAt(index) == grid[x][y]) {
            return findWord(index+1, word, grid, x+dirX, y+dirY, dirX, dirY);
        }

        return false;
    }

    public static boolean validCoord(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static char[][] parseGrid(List<String> input) {
        char[][] grid = new char[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = input.get(i).charAt(j);
            }
        }

        return grid;
    }

    static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
