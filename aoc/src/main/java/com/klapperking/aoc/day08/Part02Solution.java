package com.klapperking.aoc.day08;

import java.util.ArrayList;
import java.util.List;

import java.math.BigInteger;

public class Part02Solution {

  public static BigInteger solution(Graph graph, String[] moves) {

    List<Node> startNodes = new ArrayList<>();

    // get all nodes starting with A
    for (Node node : graph.adjacencyList.keySet()) {
      if (node.value.endsWith("A")) {
        startNodes.add(node);
      }
    }

    List<Long> counters = new ArrayList<>();
    long stepCounter = 0;

    for (Node startNode : startNodes) {

      boolean done = false;

      while (!done) {

        for (int i = 0; i < moves.length; i++) {
          stepCounter++;

          String move = moves[i];

          startNode = graph.followEdge(startNode, move);

          if (startNode.value.endsWith("Z")) {

            counters.add(stepCounter);
            stepCounter = 0;
            done = true;
            break;
          }
        }
      }
    }

    // find lowest common multiple of loop sizes
    return LowestCommonMultiple.lcmOfArray(counters.toArray(new Long[0]));
  }

  // multi-threading seach, will take too long until Z-s are actually reached
  public static int oldSolution(Graph graph, String[] moves) {

    List<Node> startNodes = new ArrayList<>();

    // get all nodes starting with A
    for (Node node : graph.adjacencyList.keySet()) {
      if (node.value.endsWith("A")) {
        startNodes.add(node);
      }
    }

    int stepCounter = 0;
    boolean done = false;

    while (!done) {

      for (String move : moves) {

        stepCounter++;
        startNodes = graph.followEdgeForMultipleNodes(startNodes, move);

        // exit condition: if all nodes end in Z, we can exit
        boolean exitFlag = true;
        for (Node node : startNodes) {
          if (!node.value.endsWith("Z")) {
            exitFlag = false;
          }
        }

        if (exitFlag) {
          done = true;
          break;
        }
      }
    }
    return stepCounter;
  }
}
