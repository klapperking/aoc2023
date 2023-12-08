package com.klapperking.aoc.day08;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.concurrent.*;

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

  public Node followEdge(Node node, String direction) throws IllegalArgumentException {
    Node nextNode;

    if (direction.equals("L")) {
      nextNode = this.adjacencyList.get(node).get(0);
    } else if (direction.equals("R")) {
      nextNode = this.adjacencyList.get(node).get(1);
    } else {
      throw new IllegalArgumentException("Direction has to be 'L' or 'R' ");
    }
    return nextNode;
  }

  public List<Node> followEdgeForMultipleNodes(List<Node> startNodes, String direction) {

    List<Node> resultNodeList = new ArrayList<>();

    int numThreads = Runtime.getRuntime().availableProcessors();
    ExecutorService executerService = Executors.newFixedThreadPool(numThreads);

    List<Callable<Node>> tasks = new ArrayList<>();

    for (int i = 0; i < startNodes.size(); i++) {
      final int index = i;

      tasks.add(() -> {
        return followEdge(startNodes.get(index), direction);
      });
    }

    try {

      List<Future<Node>> futures = executerService.invokeAll(tasks);
      for (Future<Node> future : futures) {
        resultNodeList.add(future.get());
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } finally {
      executerService.shutdown();
    }
    return resultNodeList;
  }
}
