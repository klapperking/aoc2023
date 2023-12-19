package com.klapperking.aoc.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day16Parser {

  public List<String[]> grid;
  public Day16Parser(InputStream inputStream) {

    this.grid = new ArrayList<>();
    parseLines(inputStream);
  }

  private void parseLines(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      while ((line = br.readLine()) != null) {
        this.grid.add(line.split(""));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
