package com.klapperking.aoc.day10;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day10Solution {

  public static void main(String[] args) {

    boolean part1 = true;

    InputStream inputStream = Day10Solution.class.getResourceAsStream("/day10/input2.txt");
    Day10Parser parser = new Day10Parser(inputStream);
    List<List<String>> grid = parser.grid;
    List<Integer> startPos = parser.startPos;

    int[][] posToCheck = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    HashMap<int[], List<String>> valids = new HashMap<>();
    valids.put(posToCheck[0], new ArrayList<>(Arrays.asList("-", "L", "F"))); // left
    valids.put(posToCheck[1], new ArrayList<>(Arrays.asList("-", "7", "J"))); // right
    valids.put(posToCheck[2], new ArrayList<>(Arrays.asList("|", "7", "F"))); // above
    valids.put(posToCheck[3], new ArrayList<>(Arrays.asList("|", "L", "J"))); // below

    // determine which direction(s) are movable then move there and continue; Exit-Condition: nextPos=startPos
    // keep track where we came from, in order to ignore that direction
    List<Integer> nextPos = new ArrayList<>(Arrays.asList(0, 0));
    List<String> cantGo = new ArrayList<>();
    String cameFrom = "start";
    long counter = 0;

    while (!grid.get(nextPos.get(0)).get(1).equals("S")) {

      nextPos = new ArrayList<>();

      String above = "X";
      String below = "X";
      String left = "X";
      String right = "X";

      try {
        above = grid.get(startPos.get(0) - 1).get(startPos.get(1));
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        below = grid.get(startPos.get(0) + 1).get(startPos.get(1));
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        left = grid.get(startPos.get(0)).get(startPos.get(1) - 1);
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        right = grid.get(startPos.get(0)).get(startPos.get(1) + 1);
      } catch (IndexOutOfBoundsException e) {
      }

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

      System.out.println(grid.get(nextPos.get(0)).get(nextPos.get(1)));
      startPos = nextPos;
      counter++;
    }
    System.out.println(counter);
  }
}
