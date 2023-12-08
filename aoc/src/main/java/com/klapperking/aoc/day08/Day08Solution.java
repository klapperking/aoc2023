package com.klapperking.aoc.day08;

import java.io.*;

public class Day08Solution {

  public static void main(String[] args) {

    boolean part1 = true;

    // read the file
    InputStream inputStream = Day08Solution.class.getResourceAsStream("/day08/input.txt");
    Day08Parser parser = new Day08Parser(inputStream);
    parser.parseFile();

    // given a graph, follow the edges based on LR instructions
    Graph graph = parser.getGraph();
    String[] moves = parser.getMoveInstructions();

    int stepCounter = 0;
    Node exitNode = graph.getNodeByValue("AAA");
    boolean stopFlag = true;

    while (stopFlag) {

      for (String move : moves) {

        if (move.equals("L")) {
          exitNode = graph.adjacencyList.get(exitNode).get(0);
        } else {
          exitNode = graph.adjacencyList.get(exitNode).get(1);
        }
        stepCounter++;

        if (exitNode.value.equals("ZZZ")) {
          stopFlag = false;
          break;
        }

      }
    }

    System.out.println("Part 1: " + stepCounter);
  }
}
