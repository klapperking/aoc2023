package com.klapperking.aoc.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Day10Parser {

  public List<List<String>> grid = new ArrayList<>();
  public List<List<Node>> nodeGrid = new ArrayList<>();
  public List<Integer> startPos = new ArrayList<>();

  public Day10Parser(InputStream inputStream) {
    parseLine(inputStream);
  }

  private void parseLine(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      int lineNumber = 0;

      while ((line = br.readLine()) != null) {

        List<String> pipes = new ArrayList<>(Arrays.asList(line.split("")));
        List<Node> pipeNodes = new ArrayList<>();

        for (int i = 0; i < pipes.size(); i++) {

          Node pipeNode = new Node(lineNumber, i, pipes.get(i));

          if (pipes.get(i).equals("S")) {
            this.startPos.add(lineNumber);
            this.startPos.add(i);
          }

          pipeNodes.add(pipeNode);
        }

        this.nodeGrid.add(pipeNodes);
        lineNumber++;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
