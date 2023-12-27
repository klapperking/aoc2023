package com.klapperking.aoc.day17;

import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;

public class Day17Solution {
  public static void main(String[] args) {

    Boolean[] parts = {true, false};

    InputStream inputStream = Day17Solution.class.getResourceAsStream("/day17/input2.txt");
    Day17Parser parser = new Day17Parser(inputStream);
    Integer[][] grid = parser.grid;
    Graph graph = parser.graph;

    long result = 0;
    for (boolean part1 : parts) {

      if (part1) {

        // Bad implementation of starting and ending on the right node
        Node start = new Node(0, 0);

        Graph resultGraph = dijkstra(graph, start);

        Node end = new Node(grid.length - 1, grid[0].length - 1);
        for (Node node : resultGraph.adjacencyList.keySet()) {

          if (node.equals(end)) {

            for (Node pathNode : node.shortestPath){

              if (!pathNode.equals(start)) {
                result += pathNode.distance;
              }
            }
          }
        }
      } else {

      }
    }
    System.out.println(result);
  }


  private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {

    Node lowestDistanceNode = null;
    int lowestDistance = Integer.MAX_VALUE;

    for (Node node : unsettledNodes) {

      if (lowestDistanceNode == null) {
        lowestDistanceNode = node;
      }

      int nodeDistance = node.distance;
      if (nodeDistance < lowestDistance) {
        lowestDistance = nodeDistance;
        lowestDistanceNode = node;
      }
    }

    return lowestDistanceNode;
  }




  private static void calculateMinDistance(Node node, int edgeWeight, Node source) {

    if (source.distance + edgeWeight < node.distance) {

      if (node.distance == Integer.MAX_VALUE) {
        node.distance = edgeWeight;
      } else {
        node.distance += edgeWeight;

      }

      LinkedList<Node> shortestPath = new LinkedList<>(source.shortestPath);
      shortestPath.add(source);
      node.shortestPath = shortestPath;
    }
  }



  public static Graph dijkstra(Graph graph, Node startNode) {

    Set<Node> settled = new HashSet<>();
    Set<Node> unsettled = new HashSet<>();
    unsettled.add(startNode);

    while (unsettled.size() > 0) {

      Node current = getLowestDistanceNode(unsettled);
      unsettled.remove(current);

      for (Map.Entry<Node, Integer> pair : graph.adjacencyList.get(current).entrySet()) {

        Node adjacentNode = pair.getKey();
        int edgeWeight = pair.getValue();

        if (!settled.contains(adjacentNode)) {
          calculateMinDistance(adjacentNode, edgeWeight, current);
          unsettled.add(adjacentNode);
        }

      }
      settled.add(current);
    }

    return graph;
  }
}
