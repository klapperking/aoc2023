package com.klapperking.aoc.day08;

public class Part01Solution {

  public static int solution(Graph graph, String[] moves) {

    int stepCounter = 0;
    Node exitNode = graph.getNodeByValue("AAA");
    boolean stopFlag = true;

    while (stopFlag) {

      for (String move : moves) {

        exitNode = graph.followEdge(exitNode, move);
        stepCounter++;

        // done when ZZZ is reached
        if (exitNode.value.equals("ZZZ")) {
          stopFlag = false;
          break;
        }

      }
    }
    return stepCounter;
  }
}
