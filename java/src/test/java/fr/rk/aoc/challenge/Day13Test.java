package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day13Test {

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "Button A: X+94, Y+34",
            "Button B: X+22, Y+67",
            "Prize: X=8400, Y=5400",
            "",
            "Button A: X+26, Y+66",
            "Button B: X+67, Y+21",
            "Prize: X=12748, Y=12176"
    ));

    ArrayList<String> inputTest1 = new ArrayList<>(Arrays.asList(
            "Button A: X+94, Y+34",
            "Button B: X+22, Y+67",
            "Prize: X=8400, Y=5400"
    ));

    ArrayList<String> inputTest3 = new ArrayList<>(Arrays.asList(
            "Button A: X+94, Y+34",
            "Button B: X+22, Y+67",
            "Prize: X=8400, Y=5400",
            "",
            "Button A: X+26, Y+66",
            "Button B: X+67, Y+21",
            "Prize: X=12748, Y=12176",
            "",
            "Button A: X+17, Y+86",
            "Button B: X+84, Y+37",
            "Prize: X=7870, Y=6450",
            "",
            "Button A: X+69, Y+23",
            "Button B: X+27, Y+71",
            "Prize: X=18641, Y=10279"
    ));

    @Test
    public void testNbToken() {
        MatcherAssert.assertThat("Nb token is 280", Day13.getNbTokenToWin(inputTest1, true), Matchers.equalTo(280L));
        MatcherAssert.assertThat("Nb token is 0", Day13.getNbTokenToWin(inputTest2, true), Matchers.equalTo(280L));
        MatcherAssert.assertThat("Nb token is 480", Day13.getNbTokenToWin(inputTest3, true), Matchers.equalTo(480L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 13).ifPresent(lines -> log.info("The answer is {}", Day13.getNbTokenToWin(lines, true)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 13).ifPresent(lines -> log.info("The answer is {}", Day13.getNbTokenToWin(lines, false)));
    }


}
