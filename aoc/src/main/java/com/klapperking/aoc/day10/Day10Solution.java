package com.klapperking.aoc.day10;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day10Solution {

  public static void main(String[] args) {

    InputStream inputStream = Day10Solution.class.getResourceAsStream("/day10/input.txt");
    Day10Parser parser = new Day10Parser(inputStream);
    List<Integer> startPos = parser.startPos;
    List<List<Node>> grid = parser.nodeGrid;

    int[][] posToCheck = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    HashMap<int[], List<String>> valids = new HashMap<>();
    valids.put(posToCheck[0], new ArrayList<>(Arrays.asList("-", "L", "F"))); // left
    valids.put(posToCheck[1], new ArrayList<>(Arrays.asList("-", "7", "J"))); // right
    valids.put(posToCheck[2], new ArrayList<>(Arrays.asList("|", "7", "F"))); // above
    valids.put(posToCheck[3], new ArrayList<>(Arrays.asList("|", "L", "J"))); // below

    HashMap<String, List<String>> validFromCurrent = new HashMap<>();
    validFromCurrent.put("|", new ArrayList<>(Arrays.asList("above", "below")));
    validFromCurrent.put("-", new ArrayList<>(Arrays.asList("left", "right")));
    validFromCurrent.put("L", new ArrayList<>(Arrays.asList("above", "right")));
    validFromCurrent.put("J", new ArrayList<>(Arrays.asList("above", "left")));
    validFromCurrent.put("7", new ArrayList<>(Arrays.asList("left", "below")));
    validFromCurrent.put("F", new ArrayList<>(Arrays.asList("below", "right")));

    List<Integer> nextPos = new ArrayList<>(Arrays.asList(0, 0));
    String cameFrom = "start";
    long counter = 0;

    while (!grid.get(nextPos.get(0)).get(nextPos.get(1)).value.equals("S")) {

      nextPos = new ArrayList<>();

      String above = "X";
      String below = "X";
      String left = "X";
      String right = "X";

      try {
        above = grid.get(startPos.get(0) - 1).get(startPos.get(1)).value;
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        below = grid.get(startPos.get(0) + 1).get(startPos.get(1)).value;
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        left = grid.get(startPos.get(0)).get(startPos.get(1) - 1).value;
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        right = grid.get(startPos.get(0)).get(startPos.get(1) + 1).value;
      } catch (IndexOutOfBoundsException e) {
      }

      if (grid.get(startPos.get(0)).get(startPos.get(1)).value.equals("S")) {

        // above
        if (valids.get(posToCheck[2]).contains(above) && !cameFrom.equals("above")) {
          nextPos.add(startPos.get(0) - 1);
          nextPos.add(startPos.get(1));
          cameFrom = "below";
        // below
        } else if (valids.get(posToCheck[3]).contains(below) && !cameFrom.equals("below")) {
          nextPos.add(startPos.get(0) + 1);
          nextPos.add(startPos.get(1));
          cameFrom = "above";
        // left
        } else if (valids.get(posToCheck[0]).contains(left) && !cameFrom.equals("left")) {
          nextPos.add(startPos.get(0));
          nextPos.add(startPos.get(1) - 1);
          cameFrom = "right";
        } else if (valids.get(posToCheck[1]).contains(right) && !cameFrom.equals("right")) {
          nextPos.add(startPos.get(0));
          nextPos.add(startPos.get(1) + 1);
          cameFrom = "left";
        }

      // if not on start, there are only 2 directions
      } else {

        Node current = grid.get(startPos.get(0)).get(startPos.get(1));
        String currentValue = current.value;

        // go in the leftoever direction you didnt come from
        HashMap<String, List<String>> clone = new HashMap<>();
        for (HashMap.Entry<String, List<String>> entry : validFromCurrent.entrySet()) {
          clone.put(new String(entry.getKey()), new ArrayList<String>(entry.getValue()));
        }

        List<String> possibilities = clone.get(currentValue);
        possibilities.remove(cameFrom);

        if (possibilities.get(0).equals("left")) {
          nextPos.add(startPos.get(0));
          nextPos.add(startPos.get(1) - 1);
          cameFrom = "right";
        } else if (possibilities.get(0).equals("right")) {
          nextPos.add(startPos.get(0));
          nextPos.add(startPos.get(1) + 1);
          cameFrom = "left";
        } else if (possibilities.get(0).equals("above")) {
          nextPos.add(startPos.get(0) - 1);
          nextPos.add(startPos.get(1));
          cameFrom = "below";
        } else if (possibilities.get(0).equals("below")){
          nextPos.add(startPos.get(0) + 1);
          nextPos.add(startPos.get(1));
          cameFrom = "above";
        }
        current.partOfLoop = true;
      }

      startPos = nextPos;
      counter++;
    }
    System.out.println("Part 1: " + counter / 2);

    int result = 0;

    for (int x = 0; x < grid.size(); x++) {

      for (int y = 0; y < grid.get(0).size(); y++) {

        Node current = grid.get(x).get(y);

        if (current.partOfLoop) {
          continue;
        }

        int intersectionCount = 0;
        List<String> edges = new ArrayList<>(Arrays.asList("|", "L", "J")); // S is "7"
        for (int z = 0; z < y; z++) {

          if (edges.contains(grid.get(x).get(z).value) && grid.get(x).get(z).partOfLoop) {
            intersectionCount++;
          }
        }

        if ((intersectionCount != 0) && (intersectionCount % 2 != 0)) {
          result++;
        }
      }
    }

    System.out.println("Part 2: " + result);

  }
}
