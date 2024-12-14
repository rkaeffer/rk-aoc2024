package fr.rk.aoc.challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Day11 {

    public static Long getNbStoneAfterNStep(List<String> input, int nbStep) {
        Map<String, Long> cache = new HashMap<>();
        return  Arrays.stream(input.get(0).split(" ")).map(Long::parseLong).map(stone -> nbStoneAfterBlink(stone, 0, nbStep, cache)).reduce(0L, Long::sum);
    }

    public static long nbStoneAfterBlink(long stone, int curBlink, int nbBlink, Map<String, Long> cache) {
        String stoneKey = stone + "-" + (nbBlink - curBlink);
        if(cache.containsKey(stoneKey)) {
            return cache.get(stoneKey);
        }
        if(curBlink == nbBlink) {
            return 1L;
        } else {
            long res;
            if (stone == 0L) {
                res = nbStoneAfterBlink(1, curBlink + 1, nbBlink, cache);
            } else if (Long.toString(stone).length() % 2 == 0) {
                String stoneString = Long.toString(stone);
                String part1 = stoneString.substring(0, stoneString.length() / 2);
                String part2 = stoneString.substring(stoneString.length() / 2);
                res = nbStoneAfterBlink(Long.parseLong(part1), curBlink + 1, nbBlink, cache) + nbStoneAfterBlink(Long.parseLong(part2), curBlink + 1, nbBlink, cache);
            } else {
                res = nbStoneAfterBlink(stone * 2024L, curBlink + 1, nbBlink, cache);
            }
            cache.put(stoneKey, res);
            return res;
        }
    }


}
