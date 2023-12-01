package com.klapperking.aoc.day01.solution.part01;

public class Part01Solution {

  public static String extractOriginal(String lineContent) {

    String digits = lineContent.replaceAll("[^0-9]", "");
    int lastIndex = digits.length() - 1 ;

    String original = "" + digits.charAt(0) + digits.charAt(lastIndex);
    return original;
  }

}
