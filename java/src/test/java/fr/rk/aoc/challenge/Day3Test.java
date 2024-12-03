package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day3Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    ));

    @Test
    public void testSumOfInstr() {
        MatcherAssert.assertThat("Sum is 161", Day3.getSumOfMulInstr(inputTest, false), Matchers.equalTo(161L));
    }

    @Test
    public void testSumOfInstrWithFilter() {
        MatcherAssert.assertThat("Sum is 48", Day3.getSumOfMulInstr(inputTest2, true), Matchers.equalTo(48L));
    }


    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 3).ifPresent(lines -> log.info("The answer is {}", Day3.getSumOfMulInstr(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 3).ifPresent(lines -> log.info("The answer is {}", Day3.getSumOfMulInstr(lines, true)));
    }

}
