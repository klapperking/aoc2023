package com.klapperking.aoc.day14;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day14Solution {

  public static List<Boolean> part1 = new ArrayList<>(Arrays.asList(true, false));

  public static void main(String[] args) {

    InputStream inputStream = Day14Solution.class.getResourceAsStream("/day14/input2.txt");
    Day14Parser parser = new Day14Parser(inputStream);
    List<List<String>> grid = parser.grid;

    for (boolean isFirstPart : part1) {

      if (isFirstPart) {

        grid = rollRocksUp(grid);
      } else {
        HashMap<List<List<String>>, Long> seenGrids = new HashMap<>();

        for (long i = 0; i < 3; i++) {

          // System.out.println(i);
          System.out.println("");

          // if we know that grid already, no more cycles will happen
          if (seenGrids.containsKey(grid)) {
            break;
          }

          // if not add this grid into our grid lookup
          seenGrids.put(grid, i);
          grid = nwseCycle(grid);

          for (List<String> row : grid) {
            System.out.println(String.join("", row));
          }
        }
      }
    }

    long result = 0;

    for (int i = 0; i < grid.size(); i++) {

      result += String.join("", grid.get(i)).chars().filter(ch -> ch == 'O').count() * (grid.size() - i);
    }

    System.out.println(result);
  }




  private static List<List<String>> nwseCycle(List<List<String>> grid) {

    // roll north
    grid = rollRocksUp(grid);

    // rotate and roll three time (west, south, east)
    for (int i = 0; i < 3; i++) {
      grid = rotateRight(grid);
      grid = rollRocksUp(grid);
      System.out.println(i);
    }

    // rotate back to north
    grid = rotateLeft(grid);

    return grid;
  }



  private static List<List<String>> rotateRight(List<List<String>> grid) {

    int rows = grid.size();
    int cols = grid.get(0).size();

    // transpose grid
    List<List<String>> transposedGrid = new ArrayList<>();
    for (int j = 0; j < cols; j++) {

      List<String> column  = new ArrayList<>();

      for (int i= 0; i < rows; i++) {

        column.add(String.valueOf(grid.get(i).get(j)));
      }
      transposedGrid.add(column);
    }

    // reverse rows
    for (int i = 0; i < cols / 2; i++) {
      for (int j = 0; j < rows; j++) {

        String track = transposedGrid.get(i).get(j);
        transposedGrid.get(i).set(j, transposedGrid.get(cols - 1 - i).get(j));
        transposedGrid.get(cols - 1 -i).set(j, track);
      }
    }

    return transposedGrid;
  }



  private static List<List<String>> rollRocksUp(List<List<String>> grid) {

    // {index: rowNumbers} for each index that is empty, empty list if #; otherwise move rock and remove and add
    HashMap<Integer, List<Integer>> indexEmptyRows = new HashMap<>();

    for (int j = 0; j < grid.size(); j++) {

      List<String> row = grid.get(j);

      for (int i = 0; i < row.size(); i++) {

        // if row has space for a rock, save that information
        if (row.get(i).equals(".")) {

          if (indexEmptyRows.containsKey(i)) {
            indexEmptyRows.get(i).add(j);
          } else {
            indexEmptyRows.put(i, new ArrayList<>(Arrays.asList(j)));
          }

        // if a # is hit, nothing can move to a smaller row than the next one
        } else if (row.get(i).equals("#")) {

          if (indexEmptyRows.containsKey(i)) {
            indexEmptyRows.put(i, new ArrayList<>());
          }

        // if a rollable rock is hit, move it to the smallest position, occupy this one, then mark its prev as clear
        } else {

          if (indexEmptyRows.containsKey(i)) {

            // skip if the rock need to stay
            if (indexEmptyRows.get(i).isEmpty()) {
              continue;
            }

            int smallestRow  = indexEmptyRows.get(i).get(0);

            // set to 0
            grid.get(smallestRow).set(i, "O");
            grid.get(j).set(i, ".");

            // remove this from possibles, and add the now empty one
            indexEmptyRows.get(i).remove(0);
            indexEmptyRows.get(i).add(j);
          }

        }
      }
    }
    return grid;
  }
}
