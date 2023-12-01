package com.klapperking.aoc.day01;

import java.nio.file.Files;
import java.nio.file.Path;

import com.klapperking.aoc.day01.solution.Part01Solution;

public class Main {
  public static void main(String[] args) {
    try {

      String filePath = Main.class.getResource("/day01/input.txt").getPath();
      String input = Files.readString(Path.of(filePath));

      int result = Part01Solution.solution(input);
      System.out.println("Day 1 Solution: " + result);

    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
