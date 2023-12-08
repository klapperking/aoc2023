package com.klapperking.aoc.day08;

import java.io.InputStream;

public class Part2Parser implements Parser {

  public Part2Parser(InputStream inputStream) {
  }

  public Graph getGraph() {
    return new Graph();
  }

  public Node getStartNode() {
    return new Node("test");
  }

  public String[] getMoveInstructions() {
    String[] test = {"asdf"};
    return test;
  }


  @Override
  public void parseFile() {

  }

}
