package com.klapperking.aoc.day08;

public class Tree {

  Node root;

  public void addFromValue(String value) {
    root = addRecursive(root, value);
  }

  public void addNode(Node node) {

  }

  private Node addRecursive(Node current, String value) {

    if (current == null) {
      return new Node(value);
    }

    if (value.compareTo(current.value) < 0) {
      current.left = addRecursive(current.left, value);
    } else if (value.compareTo(current.value) > 0) {
      current.right = addRecursive(current.right, value);
    } else {
      return current;
    }
    return current;
  }
}
