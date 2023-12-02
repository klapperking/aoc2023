package com.klapperking.aoc.day02.solution.part01;

import java.util.HashMap;

public class Part01Solution {

  public static int getGameId(String lineContent) {

    // gain info from the line
    HashMap<String, HashMap<String, HashMap<String, Integer>>> gameInfo = parseLine(lineContent);
    int gameId = 0;

    // define possibles
    HashMap<String, Integer> possible = new HashMap<String, Integer>();
    possible.put("red", 12);
    possible.put("green", 13);
    possible.put("blue", 14);

    // if the game is possible, return its id, otherwise 0
    for (HashMap.Entry<String, HashMap<String, HashMap<String, Integer>>> game : gameInfo.entrySet()) {

      gameId = Integer.parseInt(game.getKey());

      HashMap<String, HashMap<String, Integer>> draws = game.getValue();

      for (HashMap.Entry<String, HashMap<String, Integer>> drawInfo : draws.entrySet()) {

        for (HashMap.Entry<String, Integer> colorInfo : drawInfo.getValue().entrySet()) {

          String color = colorInfo.getKey();
          int amount = colorInfo.getValue();

          // compare if value of color is smaller than allow
          if (amount > possible.get(color)) {
            return 0;
          }
        }
      }
    }
    return gameId;
  }

  private static HashMap<String, HashMap<String, HashMap<String, Integer>>> parseLine(String lineContent) {

    HashMap<String, HashMap<String, HashMap<String, Integer>>> gameInfo = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();

    // Get GameId and gameInfo
    String[] idInfoSplit = lineContent.split(":");
    String gameId = idInfoSplit[0].replaceAll("[^0-9]", "");
    String[] gameResults = idInfoSplit[1].split(";");
    HashMap<String, HashMap<String, Integer>> drawInfo = new HashMap<String, HashMap<String, Integer>>();

    int drawCount = 0;
    for (String gameDraw : gameResults) {

      HashMap<String, Integer> draws = new HashMap<String, Integer>();

      // get color and amount info
      String[] colorDraws = gameDraw.split(",");

      for (String colorInfo : colorDraws) {

        int amount = Integer.parseInt(colorInfo.trim().split("\\s+")[0]);
        String color = colorInfo.trim().split("\\s+")[1];

        draws.put(color, amount);
      }

      drawInfo.put("draw" + drawCount, draws);
      drawCount += 1;
    }

    gameInfo.put(gameId, drawInfo);
    return gameInfo;
  }
}
