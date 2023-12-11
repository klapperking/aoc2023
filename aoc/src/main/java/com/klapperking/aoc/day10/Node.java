package com.klapperking.aoc.day10;

public class Node {

  public int x;
  public int y;

  public String value;

  public boolean partOfLoop = false;
  public boolean isStart = false;

  public Node(int x, int y, String value) {
    this.x = x;
    this.y = y;
    this.value = value;

    if (value.equals("S")) {
      this.isStart = true;
      this.partOfLoop = true;
    }
  }
}
