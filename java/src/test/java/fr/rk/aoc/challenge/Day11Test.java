package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day11Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
       "125 17"
    ));

    @Test
    public void testBlink() {
        MatcherAssert.assertThat("Nb stone is 3", Day11.getNbStoneAfterNStep(inputTest, 1), Matchers.equalTo(3L));
        MatcherAssert.assertThat("Nb stone is 4", Day11.getNbStoneAfterNStep(inputTest, 2), Matchers.equalTo(4L));
        MatcherAssert.assertThat("Nb stone is 5", Day11.getNbStoneAfterNStep(inputTest, 3), Matchers.equalTo(5L));
        MatcherAssert.assertThat("Nb stone is 9", Day11.getNbStoneAfterNStep(inputTest, 4), Matchers.equalTo(9L));
        MatcherAssert.assertThat("Nb stone is 13", Day11.getNbStoneAfterNStep(inputTest, 5), Matchers.equalTo(13L));
        MatcherAssert.assertThat("Nb stone is 22", Day11.getNbStoneAfterNStep(inputTest, 6), Matchers.equalTo(22L));
        MatcherAssert.assertThat("Nb stone is 55312", Day11.getNbStoneAfterNStep(inputTest, 25), Matchers.equalTo(55312L));

    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 11).ifPresent(lines -> log.info("The answer is {}", Day11.getNbStoneAfterNStep(lines, 25)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 11).ifPresent(lines -> log.info("The answer is {}", Day11.getNbStoneAfterNStep(lines, 75)));
    }
}
