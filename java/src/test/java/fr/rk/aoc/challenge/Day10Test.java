package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day10Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "89010123",
            "78121874",
            "87430965",
            "96549874",
            "45678903",
            "32019012",
            "01329801",
            "10456732"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "0123",
            "1234",
            "8765",
            "9876"
    ));

    ArrayList<String> inputTest3 = new ArrayList<>(Arrays.asList(
            "1110111",
            "1111111",
            "1112111",
            "6543456",
            "7111117",
            "8111118",
            "9111119"
    ));

    ArrayList<String> inputTest4 = new ArrayList<>(Arrays.asList(
            "..90..9",
            "...1.98",
            "...2..7",
            "6543456",
            "765.987",
            "876....",
            "987...."
    ));

    @Test
    public void testMapScore() {
       MatcherAssert.assertThat("Score is 1", Day10.getMapScore(inputTest2, true), Matchers.equalTo(1L));
       MatcherAssert.assertThat("Score is 2", Day10.getMapScore(inputTest3, true), Matchers.equalTo(2L));
       MatcherAssert.assertThat("Score is 36", Day10.getMapScore(inputTest, true), Matchers.equalTo(36L));
       MatcherAssert.assertThat("Score is 81", Day10.getMapScore(inputTest, false), Matchers.equalTo(81L));

    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 10).ifPresent(lines -> log.info("The answer is {}", Day10.getMapScore(lines, true)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 10).ifPresent(lines -> log.info("The answer is {}", Day10.getMapScore(lines, false)));
    }
}
