package com.klapperking.aoc.day08;

import java.io.*;
import java.math.BigInteger;

public class Day08Solution {

  public static void main(String[] args) {

    boolean part1 = false;

    // read the file
    InputStream inputStream = Day08Solution.class.getResourceAsStream("/day08/input.txt");
    Day08Parser parser = new Day08Parser(inputStream);
    parser.parseFile();

    // given a graph, follow the edges based on LR instructions
    Graph graph = parser.getGraph();
    String[] moves = parser.getMoveInstructions();

    if (part1) {
      int stepCounter = Part01Solution.solution(graph, moves);
      System.out.println("Part 1: " + stepCounter);
    } else {
      BigInteger stepCounter = Part02Solution.solution(graph, moves);
      System.out.println("Part 2: " + stepCounter);
    }

  }
}
