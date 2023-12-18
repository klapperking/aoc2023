package com.klapperking.aoc.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day16Parser {

  public List<Position[]> grid;
  public Day16Parser(InputStream inputStream) {

    this.grid = new ArrayList<>();
    parseLines(inputStream);
  }

  private void parseLines(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      while ((line = br.readLine()) != null) {

        List<Position> lineContents = new ArrayList<>();

        for (String tile : line.split("")) {

          lineContents.add(new Position(tile));
        }

        this.grid.add(lineContents.toArray(new Position[0]));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
