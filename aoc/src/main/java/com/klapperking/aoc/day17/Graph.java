package com.klapperking.aoc.day17;

import java.util.HashMap;
import java.util.Map;

public class Graph {

  public Map<Node, Map<Node, Integer>> adjacencyList;

  public Graph() {
    this.adjacencyList = new HashMap<>();
  }




  public void addNode(Node node) {
    this.adjacencyList.put(node, new HashMap<>());
  }




  public void addEdge(Node parent, Node child, int distance) {

    if (!this.adjacencyList.containsKey(parent) || !this.adjacencyList.containsKey(child)) {
      throw new IllegalArgumentException("Both nodes need to be present in Graph");
    }

    this.adjacencyList.get(parent).put(child, distance);
  }
}
