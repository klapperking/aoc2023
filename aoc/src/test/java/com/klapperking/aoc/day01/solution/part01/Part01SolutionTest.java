package com.klapperking.aoc.day01.solution.part01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Part01SolutionTest {
  @Test
  void testExtractOriginal() {
    StringBuilder input = new StringBuilder();
    input.append("1one2asdfva34");

    String digits = Part01Solution.extractOriginal(input.toString());

    assertEquals("14", digits);
  }
}
