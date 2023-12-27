package com.klapperking.aoc.day17;

import java.util.LinkedList;

public class Node {

  private int x;
  private int y;

  public int distance = Integer.MAX_VALUE;

  public LinkedList<Node> shortestPath = new LinkedList<>();


  public Node(int x, int y) {
    this.x = x;
    this.y = y;
  }



  @Override
  public boolean equals(Object o) {

    if (o == this) {
      return true;
    }

    if (!(o instanceof Node)) {
      return false;
    }

    Node other = (Node) o;
    if (this.x == other.x && this.y == other.y) {
      return true;
    }

    return false;
  }




  @Override
  public final int hashCode() {

    return 31 * (x +  7 * y);
  }
}
