package com.klapperking.aoc.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.klapperking.aoc.day02.solution.part01.Part01Solution;
import com.klapperking.aoc.day02.solution.part02.Part02Solution;

public class Main {
  public static void main(String[] args) {

    String input;

    // read input file
    try {
      String filePath = Main.class.getResource("/day02/input.txt").getPath();
      input = Files.readString(Path.of(filePath));

    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    int result = Day02Solution(true, input);
    System.out.println("Part 1 result: " + result);

    result = Day02Solution(false, input);
    System.out.println("Part 1 result: " + result);
  }

  private static int Day02Solution(boolean part1, String input) {

    String[] lines = input.split("\\n");
    int result = 0;

    for (String line : lines) {

      if (part1 == true) {

        int gameId = Part01Solution.getGameId(line);

        result += gameId;
      } else {
        int power = Part02Solution.powerOfMinimumCubesToGame(line);

        result += power;
      }
    }

    return result;
  }
}
