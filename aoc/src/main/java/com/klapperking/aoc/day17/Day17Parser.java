package com.klapperking.aoc.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day17Parser {

  public Integer[][] grid;
  public Graph graph;




  public Day17Parser(InputStream inputStream) {
    parseLines(inputStream);
    buildGraph();
  }




  private void parseLines(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      List<Integer[]> mutGrid = new ArrayList<>();

      String line;
      while ((line = br.readLine()) != null) {

        List<Integer> lineContent = new ArrayList<>();

        for (String c : line.split("")) {
          lineContent.add(Integer.parseInt(c));
        }

        mutGrid.add(lineContent.toArray(new Integer[0]));
      }

      this.grid = mutGrid.toArray(new Integer[0][0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }




  private void buildGraph() {

    Graph graph = new Graph();

    // go through gird and create nodes and edges (edge to vert & horizontal adjacent nodes)

    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[0].length; j++) {

        Node node = new Node(i, j);

        // if the node is known, dont add it again
        if (!graph.adjacencyList.containsKey(node)) {
          graph.addNode(node);
        }

        // add edges to this node
        for (int k = -1; k <= 1; k++) {

          for (int l = -1; l <= 1; l++) {

            // skip diagonally adjacent
            if (Math.abs(k) - Math.abs(l) == 0) {
              continue;
            }

            // skip out of bounds
            if (i + k < 0 || i + k >= grid.length || j + l < 0 || j + l >= grid.length) {
              continue;
            }

            // again: if the node is known, dont add it again

            Node adjNode = new Node(i+k, j+l);
            if (!graph.adjacencyList.containsKey(adjNode)) {
              graph.addNode(adjNode);
            }

            // add edge
            graph.addEdge(node, adjNode, grid[i+k][j+l]);
          }
        }
      }
    }

    this.graph = graph;
  }
}
