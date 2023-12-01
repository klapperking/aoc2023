package com.klapperking.aoc.day01.solution.part02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Part02SolutionTest {
  @Test
  void testExtractOriginal() {

    StringBuilder input = new StringBuilder();
    input.append("1one2asdfva34one");

    String digits = Part02Solution.extractOriginal(input.toString());

    assertEquals("11", digits);
  }
}
