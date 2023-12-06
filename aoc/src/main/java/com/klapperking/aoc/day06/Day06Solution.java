package com.klapperking.aoc.day06;

import java.util.*;
import java.io.*;

public class Day06Solution {
  public static void main(String[] args) {

    boolean part1 = false;

    InputStream inputStream = Day06Solution.class.getResourceAsStream("/day06/input.txt");
    Day06Parser fileParser = new Day06Parser(inputStream, part1);
    Map<String, Long[]> raceInfo = fileParser.getRaceInfo();

    List<Integer> possiblesCount = new ArrayList<>();

    for (HashMap.Entry<String, Long[]> entry : raceInfo.entrySet()) {

      Long time = entry.getValue()[0];
      Long distance = entry.getValue()[1];

      List<Integer> possibles = new ArrayList<>();

      for (int t = 0; t < time; t++) {

        // distance based on keypress
        long canTravel = time * t - (long) Math.pow(t, 2);

        if (!(canTravel > distance)) {
          continue;
        }

        possibles.add(t);
      }

      possiblesCount.add(possibles.size());
    }

    if (part1 == false) {
      int result = 0;
      for (int i = 0; i < possiblesCount.size(); i++) {
        result += possiblesCount.get(i);
      }
      System.out.println("Part 2: " + result);
      return;
    }

    int result = possiblesCount.get(0);
    for (int i = 1; i < possiblesCount.size(); i++) {

      result *= possiblesCount.get(i);

    }

    System.out.println("Part 1: " + result);
  }
}
