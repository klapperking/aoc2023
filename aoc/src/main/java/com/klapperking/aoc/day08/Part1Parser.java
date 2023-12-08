package com.klapperking.aoc.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part1Parser implements Parser {

  private InputStream inputStream;

  private String[] moveInstructions;

  private Tree tree;

  public Part1Parser(InputStream inputStream) {

    this.inputStream = inputStream;
  }

  @Override
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

        // attach children to node
        String[] leftRightSplit = lineSplit[1].trim().split(",");
        Node left = new Node(leftRightSplit[0].replace("(", "").trim());
        Node right = new Node(leftRightSplit[1].replace(")", "").trim());
        currentNode.addChildren(left, right);

        // add this node to tree



        lineNumber++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}
