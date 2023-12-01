package com.klapperking.aoc.day01;

import java.nio.file.Files;
import java.nio.file.Path;

import com.klapperking.aoc.day01.solution.part01.Part01Solution;
import com.klapperking.aoc.day01.solution.part02.Part02Solution;

public class Main {
  public static void main(String[] args) {
    try {

      String filePath = Main.class.getResource("/day01/input.txt").getPath();
      String input = Files.readString(Path.of(filePath));

      int result = Day01Solution(true, input);
      System.out.println("Part 1 Solution: " + result);

      result = Day01Solution(false, input);
      System.out.println("Part 2 Solution: " + result);

    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  private static Integer Day01Solution(boolean part1, String input) {

    String lines[] = input.split("\\n");
    Integer result = 0;
    String calibrationNumber;

    for (String line : lines) {

      if (part1 == true) {
        calibrationNumber = Part01Solution.extractOriginal(line);
      } else {
        calibrationNumber = Part02Solution.extractOriginal(line);
      }

      result += Integer.parseInt(calibrationNumber);
    }

    return result;
  }
}
