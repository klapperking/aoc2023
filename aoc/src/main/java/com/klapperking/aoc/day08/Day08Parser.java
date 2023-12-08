package com.klapperking.aoc.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day08Parser {

  private InputStream inputStream;

  private String[] moveInstructions;

  private Graph graph;

  public Day08Parser(InputStream inputStream) {
    this.inputStream = inputStream;
    this.graph = new Graph();
  }

  public String[] getMoveInstructions() {
      return moveInstructions;
    }

  public Graph getGraph() {
    return graph;
  }

  public void parseFile() {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream))) {

      String line;
      int lineNumber = 0;

      while ((line = br.readLine()) != null) {

        if (line.isBlank()) continue;

        if (lineNumber == 0) {
          this.moveInstructions = line.split("");
          lineNumber++;
          continue;
        }

        // make new node
        String[] lineSplit = line.split("=");
        Node currentNode = new Node(lineSplit[0].trim());

        // if node doesnt exist, create, else use the existin from here one
        if (graph.getNodeByValue(currentNode.value) == null) {
          graph.addNode(currentNode);
        } else {
          currentNode = graph.getNodeByValue(currentNode.value);
        }

        String[] leftRightSplit = lineSplit[1].trim().split(",");
        Node left = new Node(leftRightSplit[0].replace("(", "").trim());
        Node right = new Node(leftRightSplit[1].replace(")", "").trim());

        if (graph.getNodeByValue(left.value) == null) {
          graph.addNode(left);
        } else {
          left = graph.getNodeByValue(left.value);
        }

        if (graph.getNodeByValue(right.value) == null) {
          graph.addNode(right);
        } else {
          right = graph.getNodeByValue(right.value);
        }

        // add the required edges
        graph.addEdge(currentNode, left);
        graph.addEdge(currentNode, right);

        lineNumber++;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
