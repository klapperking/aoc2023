package com.klapperking.aoc.day07;

import java.util.*;
import java.io.*;

public class Day07Solution {

  public static void main(String[] args) {

    // read file
    InputStream inputStream = Day07Solution.class.getResourceAsStream("/day07/input.txt");
    Day07Parser fileParser = new Day07Parser(inputStream);
    List<Hand> hands = fileParser.getHands();

    // rank hands based on first card value
    // 1 sort by type
    hands.sort(new Hand.HandComparator());

    long totalWinnings = 0;
    for (int i = 0; i < hands.size(); i++) {
      int rank = i + 1;
      totalWinnings += rank * hands.get(i).getBid();
    }

    System.out.println("Part 1: " + totalWinnings);
  }
}

// not right: 241202592
