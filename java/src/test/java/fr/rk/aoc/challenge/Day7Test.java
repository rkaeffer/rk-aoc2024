package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day7Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "190: 10 19",
            "3267: 81 40 27",
            "83: 17 5",
            "156: 15 6",
            "7290: 6 8 6 15",
            "161011: 16 10 13",
            "192: 17 8 14",
            "21037: 9 7 18 13",
            "292: 11 6 16 20"
    ));

    @Test
    public void testPossibleEquation() {
        MatcherAssert.assertThat("Sum is 3749", Day7.getSumOfSolvableEquation(inputTest, false), Matchers.equalTo(3749L));
        MatcherAssert.assertThat("Sum is 3749", Day7.getSumOfSolvableEquation(inputTest, true), Matchers.equalTo(11387L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 7).ifPresent(lines -> log.info("The answer is {}", Day7.getSumOfSolvableEquation(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 7).ifPresent(lines -> log.info("The answer is {}", Day7.getSumOfSolvableEquation(lines, true)));
    }
}
