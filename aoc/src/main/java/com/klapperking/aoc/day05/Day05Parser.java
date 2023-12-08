package com.klapperking.aoc.day05;

import java.util.*;
import java.io.*;

public class Day05Parser {

  public List<Long> seedNumbers;
  public List<Layer> converterLayers;

  public Day05Parser(InputStream inputStream) {
    this.seedNumbers = new ArrayList<>();
    this.converterLayers = new ArrayList<>();
    parseInformation(inputStream);
  }

  public void parseInformation(InputStream inputStream) {

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

      Layer currentConverterLayer = null;
      String line;

      while ((line = reader.readLine()) != null) {

        if (line.isEmpty()) continue;

        // first line is seeds
        if (line.startsWith("seeds:")) {
          for (String num : line.split(":")[1].trim().split(" ")) {
            seedNumbers.add(Long.parseLong(num));
          }
          continue;
        }

        // map description line
        if (!Character.isDigit(line.charAt(0))) {

          String[] parts = line.split("-");

          currentConverterLayer = new Layer(parts[0], parts[2].replace("map:", "").trim());

          converterLayers.add(currentConverterLayer);
          continue;
        }

        // Otherwise Map-Number line: Construct Ranges and attach them as RangeMap to ConverterLayer

        String[] parts = line.split(" ");

        Long sourceIntervalStart = Long.parseLong(parts[1]);
        Long destIntervalStart = Long.parseLong(parts[0]);
        Long intervalSize = Long.parseLong(parts[2]);

        RangeMap map = new RangeMap(sourceIntervalStart, destIntervalStart, intervalSize);

        currentConverterLayer.addRangeMap(map);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
