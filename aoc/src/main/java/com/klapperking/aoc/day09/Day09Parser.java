package com.klapperking.aoc.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Day09Parser {

  public List<List<Long>> allReadings = new ArrayList<>();

  public Day09Parser(InputStream inputStream) {
    parseFile(inputStream);
  }

  private void parseFile(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      while ((line = br.readLine()) != null) {

        String[] strings = line.split(" ");
        List<Long> nums = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
          nums.add(Long.parseLong(strings[i]));
        }

        allReadings.add(nums);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
