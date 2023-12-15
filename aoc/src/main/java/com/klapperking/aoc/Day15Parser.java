package com.klapperking.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day15Parser {

  public String[] initSequence;

  public Day15Parser(InputStream inputStream) {

    this.initSequence = parseInput(inputStream);
  }


  private String[] parseInput(InputStream inputStream) {

    String[] initSequence = {};
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      while ((line = br.readLine()) != null) {

        initSequence = line.replaceAll("\\n", "").split(",");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return initSequence;

  }
}
