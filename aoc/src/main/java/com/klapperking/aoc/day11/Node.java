package com.klapperking.aoc.day11;

public class Node {

  // for offsetting purposes;
  public int originalY;

  public int x;
  public int y;
  public Node shortestTo;

  public Node(int x, int y) {

    this.x = x;

    this.y = y;
    this.originalY = y;
  }
}
