package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day8Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "............",
            "........0...",
            ".....0......",
            ".......0....",
            "....0.......",
            "......A.....",
            "............",
            "............",
            "........A...",
            ".........A..",
            "............",
            "............"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "..........",
            "..........",
            "..........",
            "....a.....",
            "..........",
            ".....a....",
            "..........",
            "..........",
            "..........",
            ".........."
    ));

    ArrayList<String> inputTest3 = new ArrayList<>(Arrays.asList(
            "..........",
            "..........",
            "..........",
            "....a.....",
            "........a.",
            ".....a....",
            "..........",
            "..........",
            "..........",
            ".........."
    ));

    ArrayList<String> inputTest4 = new ArrayList<>(Arrays.asList(
            "..........",
            "..........",
            "..........",
            "....a.....",
            "........a.",
            ".....a....",
            "..........",
            "......A...",
            "..........",
            ".........."
    ));

    ArrayList<String> inputTest5 = new ArrayList<>(Arrays.asList(
            "T.........",
            "...T......",
            ".T........",
            "..........",
            "..........",
            "..........",
            "..........",
            "..........",
            "..........",
            ".........."
    ));

    @Test
    public void testNbAntinode() {
       MatcherAssert.assertThat("Nb antinode is 2", Day8.getNbPositionWithAntinode(inputTest2, false), Matchers.equalTo(2L));
       MatcherAssert.assertThat("Nb antinode is 4", Day8.getNbPositionWithAntinode(inputTest3, false), Matchers.equalTo(4L));
       MatcherAssert.assertThat("Nb antinode is 4", Day8.getNbPositionWithAntinode(inputTest4, false), Matchers.equalTo(4L));
       MatcherAssert.assertThat("Nb antinode is 14", Day8.getNbPositionWithAntinode(inputTest, false), Matchers.equalTo(14L));
    }

    @Test
    public void testNbAntinodeWithHarmony() {
        MatcherAssert.assertThat("Nb antinode is 9", Day8.getNbPositionWithAntinode(inputTest5, true), Matchers.equalTo(9L));
        MatcherAssert.assertThat("Nb antinode is 34", Day8.getNbPositionWithAntinode(inputTest, true), Matchers.equalTo(34L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 8).ifPresent(lines -> log.info("The answer is {}", Day8.getNbPositionWithAntinode(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 8).ifPresent(lines -> log.info("The answer is {}", Day8.getNbPositionWithAntinode(lines, true)));
    }


}
