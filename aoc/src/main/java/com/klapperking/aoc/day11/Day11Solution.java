package com.klapperking.aoc.day11;

import java.io.InputStream;
import java.util.*;

public class Day11Solution {

  public static void main(String[] args) {

    boolean part1 = false;

    InputStream inputStream = Day11Solution.class.getResourceAsStream("/day11/input.txt");
    Day11Parser parser = new Day11Parser(inputStream, part1);
    List<Node> galaxies = parser.galaxies;

    long result = 0;

    // find shortest path for a galaxy to all other galaxies
    for (int i = 0; i < galaxies.size(); i++) {

      for (int j = i + 1; j < galaxies.size(); j++) {

        // find path to another galaxy
        int lengthBetweenGalaxies = Math.abs(galaxies.get(j).x - galaxies.get(i).x) + Math.abs(galaxies.get(j).y - galaxies.get(i).y);
        galaxies.get(i).shortestTo = galaxies.get(j);

        result += lengthBetweenGalaxies;
      }
    }

    if (part1) {
      System.out.println("Part 1: " + result);
    } else {
      System.out.println("Part 2: " + result);
    }
  }
}
