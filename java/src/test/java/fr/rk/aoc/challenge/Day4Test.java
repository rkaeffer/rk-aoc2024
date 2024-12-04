package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day4Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "M.S",
            ".A.",
            "M.S"
    ));

    @Test
    public void testSearchWord() {
        MatcherAssert.assertThat("18 occurences of XMAS", Day4.findSearchWord(inputTest), Matchers.equalTo(18L));
    }

    @Test
    public void testSearchWordX() {
        MatcherAssert.assertThat("18 occurences of XMAS", Day4.findSearchWordX(inputTest), Matchers.equalTo(9L));
    }
    @Test
    public void testSearchWordXbasic() {
        MatcherAssert.assertThat("1 occurences of XMAS", Day4.findSearchWordX(inputTest2), Matchers.equalTo(1L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 4).ifPresent(lines -> log.info("The answer is {}", Day4.findSearchWord(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 4).ifPresent(lines -> log.info("The answer is {}", Day4.findSearchWordX(lines)));
    }
}
