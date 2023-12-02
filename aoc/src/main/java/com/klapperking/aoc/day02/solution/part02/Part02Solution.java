package com.klapperking.aoc.day02.solution.part02;

import java.util.HashMap;

import com.klapperking.aoc.day02.solution.part01.Part01Solution;

public class Part02Solution {

  public static int powerOfMinimumCubesToGame(String lineContent) {

    // get Game info
    HashMap<String, HashMap<String, HashMap<String, Integer>>> gameInfo = Part01Solution.parseLine(lineContent);

    HashMap<String, Integer> minimumValues = new HashMap<String, Integer>();
    minimumValues.put("red", 0);
    minimumValues.put("green", 0);
    minimumValues.put("blue", 0);

    // find minimum value for each color per draw per game
    for (HashMap.Entry<String, HashMap<String, HashMap<String, Integer>>> game : gameInfo.entrySet()) {

      HashMap<String, HashMap<String, Integer>> draws = game.getValue();

      for (HashMap.Entry<String, HashMap<String, Integer>> drawInfo : draws.entrySet()) {

        for (HashMap.Entry<String, Integer> colorInfo : drawInfo.getValue().entrySet()) {

          String color = colorInfo.getKey();
          int amount = colorInfo.getValue();

          // compare if value of color is smaller than allow
          if (amount > minimumValues.get(color)) {
            minimumValues.put(color, amount);
          }
        }
      }
    }

    int product = 1;

    for (int amount : minimumValues.values()) {
      product *= amount;
    }

    return product;
  }
}
