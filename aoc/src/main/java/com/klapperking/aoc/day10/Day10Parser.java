package com.klapperking.aoc.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Day10Parser {

  public List<List<String>> grid = new ArrayList<>();
  public List<Integer> startPos = new ArrayList<>();

  public Day10Parser(InputStream inputStream) {
    parseLine(inputStream);
  }

  private void parseLine(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      int lineNumber = 0;

      while ((line = br.readLine()) != null) {

        List<String> lineStrings = new ArrayList<>(Arrays.asList(line.split("")));

        // pad top row
        /*
        if (lineNumber == 0) {
          this.grid.add(Collections.nCopies(lineStrings.size(), "X"));
        }
        */

        // pad left with X
        //lineStrings.add(0, "X");

        // add line
        this.grid.add(lineStrings);

        // pad right with X
        //lineStrings.add(lineStrings.size() - 1, "X");

        if (lineStrings.contains("S")) {
          this.startPos.add(lineNumber);
          this.startPos.add(lineStrings.indexOf("S"));
        }

        lineNumber++;
      }

      // pad bottom with X
      //this.grid.add(Collections.nCopies(this.grid.get(0).size(), "X"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
