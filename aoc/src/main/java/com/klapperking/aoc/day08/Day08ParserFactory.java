package com.klapperking.aoc.day08;

import java.io.InputStream;

public abstract class Day08ParserFactory {

  public static Parser create(InputStream inputStream, boolean part1) {

    if (part1) {
      return new Part1Parser(inputStream);
    } else {
      return new Part2Parser(inputStream);
    }
  }
}
