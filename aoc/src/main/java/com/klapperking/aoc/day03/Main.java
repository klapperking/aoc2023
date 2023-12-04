package com.klapperking.aoc.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.klapperking.aoc.day03.solution.part01.Part01Solution;

public class Main {
  public static void main(String[] args) {

    // read the text file
    String input;

    try {
    String filePath = Main.class.getResource("/day03/input.txt").getPath();
    input = Files.readString(Path.of(filePath));
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    long result = Part01Solution.solution(input, true);
    System.out.println("Part 1 result: " + result);

    result = Part01Solution.solution(input, false);
    System.out.println("Part 2 result: " + result);
  }
}
