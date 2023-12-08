package com.klapperking.aoc.day08;

import java.util.*;
import java.io.*;

public class Day08Solution {

  public static void main(String[] args) {

    boolean part1 = true;

    // read the file
    InputStream inputStream = Day08Solution.class.getResourceAsStream("/day08/input2.txt");
    Parser parser = Day08ParserFactory.create(inputStream, part1);
    parser.parseFile();

    // perform the task



  }
}
