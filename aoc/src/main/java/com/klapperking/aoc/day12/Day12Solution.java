package com.klapperking.aoc.day12;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Day12Solution {

  public static void main(String[] args) {

    boolean part1 = false;

    InputStream inputStream = Day12Solution.class.getResourceAsStream("/day12/input.txt");
    Day12Parser parser = new Day12Parser(inputStream, part1);
    LinkedHashMap<String, String[]> springsGroups = parser.springsGroups;

    long result = 0;
    for (HashMap.Entry<String, String[]> entry : springsGroups.entrySet()) {

      String springs = entry.getKey().replaceAll("\\d", "");
      List<String> brokenGroups = new ArrayList<>(Arrays.asList(entry.getValue()));

      if (!part1) {
        springs = springs + "?" + springs + "?" + springs + "?" + springs + "?" + springs;
        int count = 0;
        int limit = brokenGroups.size();
        while (count < 4) {
          for (int i = 0; i < limit; i++) {
            brokenGroups.add(brokenGroups.get(i));
          }
          count++;
        }
      }

      if (part1) {
        result += countCombinations(springs, brokenGroups, 0);
      } else {
        Day12Part02 solver = new Day12Part02();
        long intermediateResult = solver.part2(springs, brokenGroups, 0, 0, 0);
        System.out.println(intermediateResult);
        result += intermediateResult;
      }
    }

    System.out.println(result);
  }

  private static boolean canBuildGroups(String springs, List<String> groupInfo) {

    List<Integer> groupSizes = new ArrayList<>();

    int currentGroupSize = 0;
    for (int i = 0; i < springs.length(); i++) {

      // determine where the group ends
      if (String.valueOf(springs.charAt(i)).equals(".")) {

        if (currentGroupSize > 0) {
          groupSizes.add(currentGroupSize);
        }
        currentGroupSize = 0;

      } else if (String.valueOf(springs.charAt(i)).equals("#")) {

        currentGroupSize += 1;
      }

      // if there is a group leftover that wasn't closed, close it
      if (currentGroupSize > 0 && i == springs.length() - 1) {
        groupSizes.add(currentGroupSize);
      }
    }

    // return if there are fewer groups found than are needed
    if (groupInfo.size() != groupSizes.size()) {
      return false;
    }

    // compare if the groups are the same
    for (int i = 0; i < groupInfo.size(); i++) {

      if (!(groupSizes.get(i) == Integer.parseInt(groupInfo.get(i)))) {
        return false;
      }
    }

    return true;
  }

  private static int countCombinations(String currentSprings, List<String> groupInfo, int currentPosition)  {

    // else start recursion
    int count = 0;

    // exit condition: a combination as long as the input string
    if (currentPosition == currentSprings.length()) {

      // result + 1 if it fits
      if (canBuildGroups(currentSprings, groupInfo)) {
        count += 1;
        return count;
      } else {
        return count;
      }
    }

    // if its a ?, it can be either . or #;
    if (String.valueOf(currentSprings.charAt(currentPosition)).equals("?")) {

      String brokenString = currentSprings.substring(0, currentPosition)
                          + "#"
                          + currentSprings.substring(currentPosition + 1, currentSprings.length());

      String workingString = currentSprings.substring(0, currentPosition)
                          + "."
                          + currentSprings.substring(currentPosition + 1, currentSprings.length());

      int brokenCount = countCombinations(brokenString, groupInfo, currentPosition + 1);
      int workingCount = countCombinations(workingString, groupInfo, currentPosition + 1);

      return brokenCount + workingCount;
    } else {
      return countCombinations(currentSprings, groupInfo, currentPosition + 1);
    }
  }
}
