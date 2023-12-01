package com.klapperking.aoc.day01.solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01SolutionTest {
  @Test
  void testPart01Solution() {

    String testInput = new StringBuilder()
      .append("1abc2\n")
      .append("pqr3stu8vwx\n")
      .append("a1b2c3d4e5f\n")
      .append("treb7uchet\n")
      .toString();

    Integer result = Part01Solution.solution(testInput);
    assertEquals(142, result);
  }
}
