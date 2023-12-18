package com.klapperking.aoc.day16;

import java.io.InputStream;
import java.util.List;


public class Day16Solution {

  public static void main(String[] args) {

    boolean part1 = false;

    InputStream inputStream = Day16Solution.class.getResourceAsStream("/day16/input2.txt");
    Day16Parser parser = new Day16Parser(inputStream);
    List<Position[]> grid = parser.grid;

    int debug = 0;
    // starting from 0,0 use moveLeft, moveRight, moveUp, moveDown functions to go to next place
    // keep track which direction we are moving in and where we are
    // at junction add to queue and spawn new thread
    // DP: keep track of direction-position combination; dont follow if already followed



  }
}
