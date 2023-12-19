package com.klapperking.aoc.day16;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;


public class Day16Solution {

  public static void main(String[] args) {

    Boolean[] part1 = {true, false};

    InputStream inputStream = Day16Solution.class.getResourceAsStream("/day16/input.txt");
    Day16Parser parser = new Day16Parser(inputStream);
    List<String[]> grid = parser.grid;
    Integer[] initialStart = {0, 0, 0};

    int result = 0;

    for (boolean part : part1) {

      if (part) {
        result = energizeGrid(grid, initialStart);
      } else {

        // for each row:
        for (int i = 0; i < grid.size(); i++) {

          // from the left
          initialStart[0] = i;
          initialStart[1] = 0;
          initialStart[2] = 0;
          result = Math.max(result, energizeGrid(grid, initialStart));

          // from the right
          initialStart[1] = grid.get(0).length - 1;
          initialStart[2] = 1;
          result = Math.max(result, energizeGrid(grid, initialStart));
        }

        for (int i = 0; i < grid.get(0).length; i++) {

          // from the top
          initialStart[0] = 0;
          initialStart[1] = i;
          initialStart[2] = 3;
          result = Math.max(result, energizeGrid(grid, initialStart));

          // from the bootom
          initialStart[0] = grid.get(0).length - 1;
          initialStart[2] = 2;
          result = Math.max(result, energizeGrid(grid, initialStart));
        }
      }

      System.out.println(result);
    }
  }

  private static int energizeGrid(List<String[]> grid, Integer[] startPos) {

    // keep track of x, y, d combo; If seen ignore;
    HashMap<List<Integer>, List<Integer>> seen = new HashMap<>();
    List<Integer[]> toVisit = new ArrayList<>();
    toVisit.add(startPos);

    // move right (left), left (right), up (below), down (above)
    Integer[] directionX = {0, 0, -1, 1};
    Integer[] directionY = {1, -1, 0, 0};

    while (toVisit.size() > 0) {

      Integer[] currentPos = toVisit.get(toVisit.size() - 1);
      toVisit.remove(toVisit.size() - 1);

      if (currentPos[0] < 0 || currentPos[0] >= grid.size() || currentPos[1] < 0 || currentPos[1] >= grid.get(0).length) {
        continue;
      }

      List<Integer> keyToCheck = new ArrayList<>(Arrays.asList(currentPos[0], currentPos[1]));

      // stop when reaching a previously seen position
      if (seen.containsKey(keyToCheck)) {

        if (seen.get(keyToCheck).contains(currentPos[2])) {
          continue;
        } else {
          seen.get(keyToCheck).add(currentPos[2]);
        }
      } else {
        seen.put(keyToCheck, new ArrayList<>(Arrays.asList(currentPos[2])));
      }

      String current = grid.get(currentPos[0])[currentPos[1]];

      if (current.equals("/")) {

        switch (currentPos[2]) {
          case 0 :
            currentPos[0] -= 1;
            currentPos[2] = 2;
            break;
          case 1:
            currentPos[0] += 1;
            currentPos[2] = 3;
            break;
          case 2:
            currentPos[1] += 1;
            currentPos[2] = 0;
            break;
          case 3:
            currentPos[1] -= 1;
            currentPos[2] = 1;
            break;
        }

        toVisit.add(currentPos);

      } else if (current.equals("\\")) {

        switch (currentPos[2]) {
          case 0 :
            currentPos[0] += 1;
            currentPos[2] = 3;
            break;
          case 1:
            currentPos[0] -= 1;
            currentPos[2] = 2;
            break;
          case 2:
            currentPos[1] -= 1;
            currentPos[2] = 1;
            break;
          case 3:
            currentPos[1] += 1;
            currentPos[2] = 0;
            break;
        }

        toVisit.add(currentPos);

      // only do something if hitting the splitter, otherwise "." behaviour
      } else if (current.equals("|") && (currentPos[2] == 0 || currentPos[2] == 1)) {

        Integer[] option1 = currentPos.clone();
        Integer[] option2 = currentPos.clone();

        option1[0] -= 1;
        option1[2] = 2;

        option2[0] += 1;
        option2[2] = 3;

        toVisit.add(option1);
        toVisit.add(option2);

      } else if (current.equals("-") && (currentPos[2] == 2 || currentPos[2] == 3)) {

        Integer[] option1 = currentPos.clone();
        Integer[] option2 = currentPos.clone();

        option1[1] -= 1;
        option1[2] = 1;

        option2[1] += 1;
        option2[2] = 0;

        toVisit.add(option1);
        toVisit.add(option2);
      } else {

        currentPos[0] += directionX[currentPos[2]];
        currentPos[1] += directionY[currentPos[2]];

        toVisit.add(currentPos);
      }
    }

    return seen.keySet().size();
  }
}
