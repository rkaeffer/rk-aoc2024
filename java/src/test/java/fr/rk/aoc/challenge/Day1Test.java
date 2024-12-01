package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day1Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "3   4",
            "4   3",
            "2   5",
            "1   3",
            "3   9",
            "3   3"
    ));

    @Test
    public void testDistanceAppart() {
        MatcherAssert.assertThat("Sum of distance is 11", Day1.getSumAppart(inputTest), Matchers.equalTo(11L));
    }

    @Test
    public void testSimilarityScore() {
        MatcherAssert.assertThat(" Similarity score is 31", Day1.getSimilarityScore(inputTest), Matchers.equalTo(31L));
    }


    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 1).ifPresent(lines -> log.info("The answer is {}", Day1.getSumAppart(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 1).ifPresent(lines -> log.info("The answer is {}", Day1.getSimilarityScore(lines)));
    }


}
