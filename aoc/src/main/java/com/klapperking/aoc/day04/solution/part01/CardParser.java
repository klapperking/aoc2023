package com.klapperking.aoc.day04.solution.part01;

import java.util.ArrayList;

import java.util.List;

public class CardParser {

  public static List<ScratchCard> parseCards(String input) {

    List<ScratchCard> cards = new ArrayList<>();

    // for each line, create the punch card
    for (String line : input.split("\n")) {

      String[] idSplit = line.split(":");

      int cardId = Integer.parseInt(idSplit[0].replaceAll("[^0-9]", ""));

      String[] winningNums = idSplit[1].split("\\|")[0].trim().split(" ");
      String[] luckyNums = idSplit[1].split("\\|")[1].trim().split(" ");

      ScratchCard lineCard = new ScratchCard(cardId);

      for (String num : winningNums) {
        if (!num.equals("")) {
          lineCard.addWinningNumber(Integer.parseInt(num));
        }
      }

      for (String num : luckyNums) {
        if (!num.equals("")) {
          lineCard.addLuckyNumber(Integer.parseInt(num));
        }
      }

      cards.add(lineCard);
    }
    return cards;
  }
}
