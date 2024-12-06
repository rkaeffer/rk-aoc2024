package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day6Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#..."
    ));

    @Test
    public void testNbPosition() {
        MatcherAssert.assertThat("Sum is 41", Day6.getNbOfVisitPosition(inputTest), Matchers.equalTo(41L));
    }

    @Test
    public void testNbPositionStuck() {
        MatcherAssert.assertThat("Sum is 6", Day6.getNbOfStuckPosition(inputTest), Matchers.equalTo(6L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 6).ifPresent(lines -> log.info("The answer is {}", Day6.getNbOfVisitPosition(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 6).ifPresent(lines -> log.info("The answer is {}", Day6.getNbOfStuckPosition(lines)));
    }
}
