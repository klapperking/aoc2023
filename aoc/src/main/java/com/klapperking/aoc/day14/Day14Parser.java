package com.klapperking.aoc.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Day14Parser {

  public List<List<String>> grid;




  public Day14Parser(InputStream inputStream) {
    this.grid = new ArrayList<>();
    parseLine(inputStream);
  }



  private void parseLine(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      while ((line = br.readLine()) != null) {

        List<String> lineGrid = new ArrayList<>(Arrays.asList(line.split("")));

        grid.add(lineGrid);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
