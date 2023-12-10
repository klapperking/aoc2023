package com.klapperking.aoc.day09;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Day09Solution {

  public static void main(String[] args) {

    boolean part1 = true;

    InputStream inputStream = Day09Solution.class.getResourceAsStream("/day09/input.txt");
    Day09Parser parser = new Day09Parser(inputStream);
    List<List<Long>> allReadings = parser.allReadings;


    long finalResult = 0;
    // part1
    for (List<Long> lineReadings : allReadings) {

      if (!part1) {
        Collections.reverse(lineReadings);
      }
      List<Long> lastElements = new ArrayList<>();
      boolean allZeros = false;

      while (!allZeros) {

        // keep last element for later
        lastElements.add(lineReadings.get(lineReadings.size() - 1));

        for (int i = 0; i < lineReadings.size() - 1; i += 1) {

          Long first = lineReadings.get(i);
          Long second = lineReadings.get(i + 1);
          Long result = second - first;

          lineReadings.set(i, result);
        }

        lineReadings.remove(lineReadings.size() - 1);
        allZeros = lineReadings.stream().allMatch(value -> value.equals(0L));
      }

      // sum up last elements in order to get next element in original
      long nextValue = 0;

      for (int i = 0; i < lastElements.size(); i++) {
        nextValue += lastElements.get(i);
      }
      finalResult += nextValue;
    }

    System.out.println("Part1: " + finalResult);
  }

}
