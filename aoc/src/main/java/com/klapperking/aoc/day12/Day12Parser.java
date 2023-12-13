package com.klapperking.aoc.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Day12Parser {

  public LinkedHashMap<String, String[]> springsGroups = new LinkedHashMap<>();

  public Day12Parser(InputStream inputStream, boolean part1) {
    parseLines(inputStream);
  }

  private void parseLines(InputStream inputStream) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      int lineNumber = 0;
      while ((line = br.readLine()) != null) {

        String[] springslineBrokencountsSplit = line.split(" ");
        String springPositions = springslineBrokencountsSplit[0];
        String[] brokenGroups = springslineBrokencountsSplit[1].split(",");

        this.springsGroups.put(String.valueOf(lineNumber) + springPositions, brokenGroups);
        lineNumber++;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
