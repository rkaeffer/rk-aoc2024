package fr.rk.aoc.challenge;


import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day2Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "7 6 4 2 1",
            "1 2 7 8 9",
            "9 7 6 2 1",
            "1 3 2 4 5",
            "8 6 4 4 1",
            "1 3 6 7 9"
    ));

    @Test
    public void testSafeReport() {
        MatcherAssert.assertThat("Nb of safe report is 2", Day2.getNbSafeReport(inputTest, false), Matchers.equalTo(2L));
    }

    @Test
    public void testSafeReportWithTolerance() {
        MatcherAssert.assertThat("Nb of safe report is 4", Day2.getNbSafeReport(inputTest, true), Matchers.equalTo(4L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 2).ifPresent(lines -> log.info("The answer is {}", Day2.getNbSafeReport(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 2).ifPresent(lines -> log.info("The answer is {}", Day2.getNbSafeReport(lines, true)));
    }
}
