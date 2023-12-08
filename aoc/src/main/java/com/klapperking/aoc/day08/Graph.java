package com.klapperking.aoc.day08;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

  public Map<Node, List<Node>> adjacencyList;

  public Graph() {
    this.adjacencyList = new HashMap<>();
  }

  public void addNode(Node node) {

    adjacencyList.put(node, new ArrayList<>());
  }

  public void addEdge(Node parent, Node child) {

    if (!adjacencyList.containsKey(parent) || !adjacencyList.containsKey(child)) {
      throw new IllegalArgumentException("Both nodes must exist in Graph");
    }

    adjacencyList.get(parent).add(child);

  }

  public List<Node> getChildren(Node node) {
    return adjacencyList.get(node);
  }

  // bottleneck function
  public Node getNodeByValue(String nodeValue) {

    for (Node existingNode : adjacencyList.keySet()) {

      if (existingNode.value.equals(nodeValue)) {
        return existingNode;
      }

    }
    return null;
  }




}
