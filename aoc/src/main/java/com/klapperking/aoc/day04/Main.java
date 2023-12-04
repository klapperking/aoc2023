package com.klapperking.aoc.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.klapperking.aoc.day04.solution.part01.Solution;

public class Main {
  public static void main(String[] args) {

    String input;

    // read the text file
    try {
    String filePath = Main.class.getResource("/day04/input.txt").getPath();
    input = Files.readString(Path.of(filePath));
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    int result = Solution.solution(input, true);
    System.out.println("Part 1 Solution: " + result);

    result = Solution.solution(input, false);
    System.err.println("Part 2 Solution: " + result);
  }
}
