package fr.rk.aoc.challenge;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Day7 {

    public static long getSumOfSolvableEquation(List<String> input, boolean withThirdOperator) {
        List<Equation> equations = input.stream().map(s -> new Equation(s, withThirdOperator ? 3 : 2)).collect(Collectors.toList());
        equations.forEach(Equation::solveEquation);
        return equations.stream().filter(e -> !e.possibleSolutions.isEmpty()).map(e -> e.result).mapToLong(Long::longValue).sum();
    }

    /**
     * 0 = +
     * 1 = *
     */
    static class Equation {
        long result;

        long[] numbers;

        int base;

        List<String> possibleSolutions;

        public Equation(String input, int base) {
            String[] parts = input.split(":");
            result = Long.parseLong(parts[0]);
            String[] subNumbers = parts[1].split(" ");
            numbers = new long[subNumbers.length-1];
            for(int i=1; i<subNumbers.length; i++) {
                numbers[i-1] = Long.parseLong(subNumbers[i]);
            }
            possibleSolutions = new ArrayList<>();
            this.base = base;
        }

        public void solveEquation() {
            long possibilities = (long) Math.pow(this.base, numbers.length - 1);
            int refLength = Long.toString(possibilities, this.base).length() - 1;
            for(long i=0; i<possibilities; i++) {
                String binary = StringUtils.leftPad(Long.toString(i, this.base), refLength, "0");
                long curResult=-1;
                for(int j=0; j<binary.length(); j++) {
                    if(binary.charAt(j) == '2') {
                        if(curResult == -1) {
                            curResult = Long.parseLong(Long.toString(numbers[j]).concat(Long.toString(numbers[j+1])));
                        } else {
                            curResult = Long.parseLong(Long.toString(curResult).concat(Long.toString(numbers[j+1])));
                        }
                    } else if(binary.charAt(j) == '1') {
                        if(curResult == -1) {
                            curResult = numbers[j] * numbers[j+1];
                        } else {
                            curResult *= numbers [j+1];
                        }
                    } else {
                        if(curResult == -1) {
                            curResult = numbers[j] + numbers[j+1];
                        } else {
                            curResult += numbers [j+1];
                        }
                    }
                }
                if(curResult == result) {
                    possibleSolutions.add(binary);
                }
            }
        }
    }
}
