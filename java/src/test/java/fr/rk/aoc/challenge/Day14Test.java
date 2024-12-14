package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day14Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "p=0,4 v=3,-3",
            "p=6,3 v=-1,-3",
            "p=10,3 v=-1,2",
            "p=2,0 v=2,-1",
            "p=0,0 v=1,3",
            "p=3,0 v=-2,-2",
            "p=7,6 v=-1,-3",
            "p=3,0 v=-1,-2",
            "p=9,3 v=2,3",
            "p=7,3 v=-1,2",
            "p=2,4 v=2,-3",
            "p=9,5 v=-3,-3"
    ));

    @Test
    public void testSafetyScore() {
        MatcherAssert.assertThat("Score is 12", Day14.getSafetyScore(inputTest,11,7,100), Matchers.equalTo(12L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 14).ifPresent(lines -> log.info("The answer is {}", Day14.getSafetyScore(lines, 101,103,100)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 14).ifPresent(lines -> log.info("The answer is {}", Day14.getNbStepForChristmasTree(lines, 101,103)));
    }
}
