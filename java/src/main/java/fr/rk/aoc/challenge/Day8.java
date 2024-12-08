package fr.rk.aoc.challenge;
import java.util.*;

public final class Day8 {

    public static long getNbPositionWithAntinode(List<String> input, boolean withHarmony) {
        Grid g = new Grid(input);
        g.calculateAntiNodes(withHarmony);
        long nbAntiNodePos = 0;

        List<Coord> counted = new ArrayList<>();
        for(Coord c : g.antinodes) {
            if(c.x >= 0 && c.y >= 0 && c.x < g.coords.length && c.y < g.coords[0].length && !alreadyCounted(c.x, c.y, counted)) {
                nbAntiNodePos++;
                counted.add(new Coord(c.x, c.y, '$'));
            }
        }
        return nbAntiNodePos;
    }

    static class Grid {
        Coord[][] coords;

        Map<Character, List<Coord>> frequences;

        Set<Coord> antinodes;


        public Grid(List<String> input) {
            this.coords = new Coord[input.size()][input.get(0).length()];
            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < input.get(i).length(); j++) {
                    this.coords[i][j] = new Coord(j, i, input.get(i).charAt(j));
                }
            }
            this.frequences = new HashMap<>();
            for(int i = 0; i < coords.length; i++){
                for(int j = 0; j < coords[i].length; j++){
                    if(coords[i][j].antenna != '.'){
                        frequences.computeIfAbsent(coords[i][j].antenna, k -> new ArrayList<>());
                        frequences.get(coords[i][j].antenna).add(new Coord(i,j, '$'));
                    }
                }
            }
            this.antinodes = new HashSet<>();
        }

        public void calculateAntiNodes(boolean withHarmony) {
            for (Character c : frequences.keySet()) {
                List<Coord> coordinates = frequences.get(c);
                for (int i = 0; i < coordinates.size(); i++) {
                    for (int j = i + 1; j < coordinates.size(); j++) {
                        Coord a = coordinates.get(i);
                        Coord b = coordinates.get(j);
                        addAntiNodes(a, b, antinodes, withHarmony);
                    }
                }
            }
        }
        private void addAntiNodes(Coord a, Coord b, Set<Coord> antinodes, boolean withHarmony){
            int diffX = a.x - b.x;
            int diffY = a.y - b.y;
            if (!withHarmony) {
                antinodes.add(new Coord(b.x - diffX, b.y - diffY, '$'));
                antinodes.add(new Coord(a.x + diffX, a.y + diffY, '$'));
            } else {
                int gcd = greatestCommonDivisor(diffY, diffX);
                for(int i = 0; i < coords.length; i++){
                    antinodes.add(new Coord(b.x - (i * diffX/gcd), b.y - (i * diffY/gcd), '$'));
                    antinodes.add(new Coord(b.x + (i * diffX/gcd), b.y + (i * diffY/gcd), '$'));
                }
            }
        }
    }

    public static int greatestCommonDivisor(int a, int b) {
        if (b == 0) return a;
        return greatestCommonDivisor(b, a % b);
    }

    static class Coord {
        char antenna;

        int x;

        int y;

        Coord(int x, int y, char c) {
            this.antenna = c;
            this.x = x;
            this.y = y;
        }
    }

    public static boolean alreadyCounted(int x, int y, List<Coord> coords) {
        for(Coord c : coords) {
            if(c.x == x && c.y == y) {
                return true;
            }
        }
        return false;
    }

}
