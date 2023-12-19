package com.klapperking.aoc.day16;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day16Solution {

  public static void main(String[] args) {

    boolean part1 = false;

    InputStream inputStream = Day16Solution.class.getResourceAsStream("/day16/input2.txt");
    Day16Parser parser = new Day16Parser(inputStream);
    List<String[]> grid = parser.grid;

    // keep track of x, y, d combo; If seen ignore;
    List<List<Integer>> seen = new ArrayList<>();
    List<List<Integer>> counter = new ArrayList<>();
    List<Integer[]> toVisit = new ArrayList<>();
    Integer[] start = {0, 0, 0}; // come from the right
    toVisit.add(start);

    // move right (left), left (right), up (below), down (above)
    Integer[] directionX = {0, 0, -1, 1};
    Integer[] directionY = {1, -1, 0, 0};

    while (toVisit.size() > 0) {

      Integer[] currentPos = toVisit.get(toVisit.size() - 1);
      toVisit.remove(toVisit.size() - 1);

      if (currentPos[0] < 0 || currentPos[0] >= grid.size() || currentPos[1] < 0 || currentPos[1] >= grid.get(0).length) {
        continue;
      }

      // stop when reaching a previously seen position
      if (seen.contains(Arrays.asList(currentPos))) {
        continue;
      }

      seen.add(Arrays.asList(currentPos.clone()));

      String current = grid.get(currentPos[0])[currentPos[1]];

      // if dot, maintain direction
      if (current.equals("/")) {

        currentPos[0] += directionX[(currentPos[2] + 2) % 4];
        currentPos[1] += directionY[(currentPos[2] + 2) % 4];
        currentPos[2] = (currentPos[2] + 3) % 4;

        toVisit.add(currentPos);

      } else if (current.equals("\\")) {

        currentPos[0] += directionX[(currentPos[2] + 3) % 4];
        currentPos[1] += directionY[(currentPos[2] + 3) % 4];
        currentPos[2] = (currentPos[2] + 2) % 4;

        toVisit.add(currentPos);

      // only do something if hitting the splitter, otherwise "." behaviour
      } else if (current.equals("|") && (currentPos[2] == 0 || currentPos[2] == 1)) {

        Integer[] option2 = currentPos.clone();

        option2[0] += directionX[(currentPos[2] + 2) % 4];
        option2[1] += directionY[(currentPos[2] + 2) % 4];
        option2[2] = (currentPos[2] + 2) % 4;

        toVisit.add(option2);

        currentPos[0] += directionX[(currentPos[2] + 3) % 4];
        currentPos[1] += directionY[(currentPos[2] + 3) % 4];
        currentPos[2] = (currentPos[2] + 3) % 4;

        toVisit.add(currentPos);

      } else if (current.equals("-") && (currentPos[2] == 2 || currentPos[2] == 3)) {

        Integer[] option2 = currentPos.clone();

        option2[0] += directionX[(currentPos[2] + 1) % 4];
        option2[1] += directionY[(currentPos[2] + 1) % 4];
        option2[2] = (currentPos[2] + 1) % 4;

        toVisit.add(option2);

        currentPos[0] += directionX[(currentPos[2] + 2) % 4];
        currentPos[1] += directionY[(currentPos[2] + 2) % 4];
        currentPos[2] = (currentPos[2] + 2) % 4;

        toVisit.add(currentPos);
      } else {

        currentPos[0] += directionX[currentPos[2]];
        currentPos[1] += directionY[currentPos[2]];

        toVisit.add(currentPos);

      }
    }

    for (List<Integer> position : seen) {

      grid.get(position.get(0))[position.get(1)] = "#";
    }

    for (String[] row : grid) {
      System.out.println(String.join("", row));
    }

  }
}
