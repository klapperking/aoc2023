package com.klapperking.aoc.day13;

import java.io.InputStream;
import java.util.*;

public class Day13Solution {

  static int SMUDGES = 0;

  public static void main(String[] args) {

    InputStream inputStream = Day13Solution.class.getResourceAsStream("/day13/input.txt");
    Day13Parser parser = new Day13Parser(inputStream);

    List<List<String>> allGrids = parser.grids;
    long result = 0;

    for (List<String> grid : allGrids) {

      SMUDGES = 0;

      long resultDelta = findHorizontalSplit(grid, true);

      if (resultDelta > 0 && SMUDGES == 1) {
        result += resultDelta;
      } else {
        resultDelta = findVerticalSplit(grid);
        result += resultDelta;
      }
    }

    System.out.println(result);

  }





  private static long findVerticalSplit(List<String> grid) {

    long result = 0;

    List<String> transposedGrid = transposeGrid(grid);
    result += findHorizontalSplit(transposedGrid, false);

    return result;
  }

  private static List<String> transposeGrid(List<String> grid) {

    int rows = grid.size();
    int cols = grid.get(0).length();

    List<String> transposed = new ArrayList<>();

    for (int i = 0; i < cols; i++) {

      String column = "";

      for (int j = 0; j < rows; j++) {

        column = column + String.valueOf(grid.get(j).charAt(i));
      }

      transposed.add(column);
    }

    return transposed;
  }





  private static long findHorizontalSplit(List<String> grid, boolean horizontal) {

    long result = 0;
    List<Integer> horizontalSplit = new ArrayList<>();

    for (int splitRow = 1; splitRow < grid.size(); splitRow++) {

      SMUDGES = 0;
      horizontalSplit.add(splitRow);

      for (int offSet = 1; offSet <= splitRow; offSet++) {

        if (splitRow - offSet < 0) {
          break;
        }

        if (splitRow + offSet - 1 >= grid.size()) {
          break;
        }

        if (!isMirrored(grid, splitRow - offSet, splitRow + offSet - 1)) {
          horizontalSplit.remove(horizontalSplit.size() - 1);
          SMUDGES = 0;
          break;
        }
      }

      if (SMUDGES == 1) {
        break;
      }
    }

    if (SMUDGES == 1) {
      if (horizontal) {
        result += 100 * horizontalSplit.get(horizontalSplit.size() - 1);
      } else {
        result += horizontalSplit.get(horizontalSplit.size() - 1);
      }

    }


    return result;
  }



  private static boolean isMirrored(List<String> grid, int i, int middlePlusI) {

    String rowToMirror = grid.get(i);
    String mirrorCandidate = grid.get(middlePlusI);

    int nonMatchingCount = 0;

    for (i = 0; i < rowToMirror.length(); i++) {

      if (!(rowToMirror.charAt(i) == mirrorCandidate.charAt(i))) {
        nonMatchingCount += 1;
      }
    }

    if (nonMatchingCount == 0) {
      return true;
    }

    if (nonMatchingCount == 1) {
      SMUDGES += 1;
      return true;
    }

    return false;
  }
}
