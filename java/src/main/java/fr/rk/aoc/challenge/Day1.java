package fr.rk.aoc.challenge;

import java.util.*;
import java.util.stream.IntStream;

public final class Day1 {

    public static long getSumAppart(List<String> input) {
        List<Long> firstList = new ArrayList<>();
        List<Long> secondList = new ArrayList<>();

        input.forEach(s -> {
            String[] splitted = s.split(" ");
            firstList.add(Long.parseLong(splitted[0]));
            secondList.add(Long.parseLong(splitted[splitted.length-1]));
        });

        Collections.sort(firstList);
        Collections.sort(secondList);

        return IntStream.range(0, firstList.size()).mapToLong(i -> Math.abs(firstList.get(i) - secondList.get(i))).sum();
    }

    public static long getSimilarityScore(List<String> input) {
        List<Long> firstList = new ArrayList<>();
        List<Long> secondList = new ArrayList<>();

        input.forEach(s -> {
            String[] splitted = s.split(" ");
            firstList.add(Long.parseLong(splitted[0]));
            secondList.add(Long.parseLong(splitted[splitted.length-1]));
        });

        Map<Long, Long> occurences = new HashMap<>();

        secondList.forEach(l -> {
            if(occurences.get(l) == null) {
                occurences.put(l, 1L);
            } else {
                occurences.put(l, occurences.get(l) + 1);
            }
        });

        long sum = 0L;

        for (long current : firstList) {
            long multiplier = occurences.get(current) != null ? occurences.get(current) : 0;
            sum += current * multiplier;
        }

        return sum;
    }

}
