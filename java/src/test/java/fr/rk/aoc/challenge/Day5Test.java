package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day5Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "47|53",
            "97|13",
            "97|61",
            "97|47",
            "75|29",
            "61|13",
            "75|53",
            "29|13",
            "97|29",
            "53|29",
            "61|53",
            "97|53",
            "61|29",
            "47|13",
            "75|47",
            "97|75",
            "47|61",
            "75|61",
            "47|29",
            "75|13",
            "53|13",
            "",
            "75,47,61,53,29",
            "97,61,53,29,13",
            "75,29,13",
            "75,97,47,61,53",
            "61,13,29",
            "97,13,75,29,47"
    ));

    @Test
    public void testSumMiddlePage() {
        MatcherAssert.assertThat("Sum is 143", Day5.getSumOfMiddlePage(inputTest, false), Matchers.equalTo(143L));
    }

    @Test
    public void testSumMiddlePageWithUpdate() {
        MatcherAssert.assertThat("Sum is 123", Day5.getSumOfMiddlePage(inputTest, true), Matchers.equalTo(123L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 5).ifPresent(lines -> log.info("The answer is {}", Day5.getSumOfMiddlePage(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 5).ifPresent(lines -> log.info("The answer is {}", Day5.getSumOfMiddlePage(lines, true)));
    }

}
