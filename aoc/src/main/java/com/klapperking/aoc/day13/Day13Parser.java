package com.klapperking.aoc.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.ArrayList;


public class Day13Parser {

  public List<List<String>> grids;

  public Day13Parser(InputStream inputStream) {
    this.grids = new ArrayList<>();
    parseLine(inputStream);
  }

  private void parseLine(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;

      List<String> currentGrid = new ArrayList<>();

      while ((line = br.readLine()) != null) {

        if (line.isBlank()) {
          this.grids.add(currentGrid);
          currentGrid = new ArrayList<>();
          continue;
        }

        // non-blank line:
        currentGrid.add(line);
      }

      this.grids.add(currentGrid);


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
