package com.klapperking.aoc.day05;

import java.util.*;
import java.io.*;

public class Day05Solution {
  public static void main(String[] args) {

    // Read file
    InputStream inputStream = Day05Solution.class.getResourceAsStream("/day05/input.txt");

    Day05Parser fileParser = new Day05Parser(inputStream);
    List<Long> seedNumbers = fileParser.seedNumbers;
    List<Layer> converterLayers = fileParser.converterLayers;

    List<Range> locationRanges = new ArrayList<>();
    List<Range> inputRanges = new ArrayList<>();

    for (int i = 0; i < seedNumbers.size(); i += 2) {

      Range inputRange = new Range(seedNumbers.get(i), seedNumbers.get(i) + seedNumbers.get(i + 1));
      inputRanges.add(inputRange);

      // feed each input range through each layer
      for (Layer layer : converterLayers) {

        // feed a range through a layer and use as input for next layer
        inputRanges = layer.feedThrough(inputRanges);
      }

      // add to locationRanges and go to next input
      for (Range resultRange : inputRanges) {
        locationRanges.add(resultRange);
      }

      inputRanges = new ArrayList<>();
    }

    long result = Long.MAX_VALUE;
    for (Range locRange : locationRanges) {
      result = Math.min(result, locRange.start);
    }

    System.out.println(result);

  }
}
