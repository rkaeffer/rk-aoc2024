package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public final class Day9Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "12345"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "2333133121414131402"
    ));

    @Test
    public void testFileSystemChecksum() {
        MatcherAssert.assertThat("Checksum is 60", Day9.getFileSystemChecksum(inputTest, false), Matchers.equalTo(60L));
        MatcherAssert.assertThat("Checksum is 1928", Day9.getFileSystemChecksum(inputTest2, false), Matchers.equalTo(1928L));
    }

    @Test
    public void testFileSystemChecksumWithPartition() {
        MatcherAssert.assertThat("Checksum is 2858", Day9.getFileSystemChecksum(inputTest2, true), Matchers.equalTo(2858L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 9).ifPresent(lines -> log.info("The answer is {}", Day9.getFileSystemChecksum(lines, false)));
    }

    @Test
    public void getSecondCHallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 9).ifPresent(lines -> log.info("The answer is {}", Day9.getFileSystemChecksum(lines, true)));
    }
}
