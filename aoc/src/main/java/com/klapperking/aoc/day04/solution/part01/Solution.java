package com.klapperking.aoc.day04.solution.part01;

import java.util.HashMap;
import java.util.List;

public class Solution {

  public static int solution(String input, boolean part1) {

    int result = 0;

    List<ScratchCard> cards = CardParser.parseCards(input);

    if (part1 == true) {

      for ( ScratchCard card : cards) {
       result += card.score;
      }

    } else {

      result = part02CardCounter(cards);

    }
    return result;
  }

  public static int part02CardCounter(List<ScratchCard> cards) {
    // abuse indeces, since only strictly positive card gets duplicated
    // after a card is handled, it cant be duplicated anymore
    int cardCounter = 0;

    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int i = 0; i < cards.size(); i++) {
      counts.put(i, 1);
    }

    for (int i = 0; i < cards.size(); i++) {

      ScratchCard currentCard = cards.get(i);

      for (int copyOffset = 1; copyOffset <= currentCard.matches; copyOffset++) {

        int indexToCopy = i + copyOffset;

        if (indexToCopy < cards.size()) {

          if (counts.get(indexToCopy) == null) {
            counts.put(indexToCopy, counts.get(i));
          } else {
            counts.put(indexToCopy, counts.get(indexToCopy) + counts.get(i));
          }
        }
      }
    }

    for (int cardCount : counts.values()) {
      cardCounter += cardCount;
    }

    return cardCounter;
  }
}
