package com.klapperking.aoc.day08;

public class Node {

  String value;
  Node left;
  Node right;

  public Node(String value) {

    this.value = value;
    right = null;
    left = null;
  }

  public void addChildren(Node left, Node right) {
    this.left = left;
    this.right = right;
  }

}
