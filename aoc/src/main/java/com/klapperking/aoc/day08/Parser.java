package com.klapperking.aoc.day08;

public interface Parser {

  public Graph getGraph();
  public String[] getMoveInstructions();

  public void parseFile();
}
