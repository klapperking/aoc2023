package com.klapperking.aoc.day01.solution;

public class Part01Solution {

  public static Integer solution(String input) {

    String lines[] = input.split("\\n");
    Integer result = 0;

    for (String line : lines) {
      String justDigits = line.replaceAll("[^0-9]", "");
      int lastIndex = justDigits.length() - 1;

      String calibrationNumber = "" + justDigits.charAt(0) + justDigits.charAt(lastIndex);

      result += Integer.parseInt(calibrationNumber);
    }

    return result;
  }
}
