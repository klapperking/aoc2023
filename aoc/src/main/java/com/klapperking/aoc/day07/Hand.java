package com.klapperking.aoc.day07;

import java.util.*;

public class Hand {

  private int type = 0;
  private int bid;
  private int jValue = 11;

  private List<Integer> cards;

  public Hand(String[] cardStrings, Integer bid, boolean part1) {

    this.bid = bid;
    this.cards = new ArrayList<>();

    if (!part1) {
      jValue = 1;
    }

    for (String card : cardStrings) {

      if (Character.isDigit(card.charAt(0))) {
        cards.add(Integer.parseInt(card));
        continue;
      }

      switch (card) {
        case "T":
          this.cards.add(10);
          break;
        case "J":
          this.cards.add(this.jValue);
          break;
        case "Q":
          this.cards.add(12);
          break;
        case "K":
          this.cards.add(13);
          break;
        case "A":
          this.cards.add(14);
          break;
      }
    }
    calcType(this.cards);
  }

  public Integer getType() {
    return this.type;
  }

  public Integer getBid() {
    return this.bid;
  }


  private void calcType(List<Integer> cards) {

    if (this.jValue == 1) {

      if (Collections.frequency(cards, this.jValue) > 0) {

        int jIndex = cards.indexOf(this.jValue);
        for (int jVal = 2; jVal < 15; jVal++) {

          if (jVal == this.jValue) continue;

          cards.set(jIndex, jVal);

          calcType(cards);
        }
        cards.set(jIndex, this.jValue);
      }

    }

    HashMap<Integer, Integer> counts = new HashMap<>();

    for (int card : cards) {

      if (counts.get(card) == null) {
        counts.put(card, 1);
        continue;
      }

      counts.put(card, counts.get(card) + 1);
    }

    int type = 0;
    switch (counts.size()) {
      // high card
      case 5:
        type = 0;
        break;
      // pair
      case 4:
        type = 1;
        break;
      // three-pair or 2 two-pairs
      case 3:
        if (counts.values().contains(3)) {
          type = 3;
        } else {
          type = 2;
        }
        break;
      // four-pair or fullhouse
      case 2:
        if (counts.values().contains(3)) {
          type = 4;
        } else {
          type = 5;
        }
        break;
      // five-pair
      case 1:
        type = 6;
        break;
    }

    this.type = Math.max(type, this.type);
  }

  private static int compareCardLists(List<Integer> cards1, List<Integer> cards2) {

    for (int i = 0; i < cards1.size(); i++) {

      int cardComparison = Integer.compare(cards1.get(i), cards2.get(i));

      if (cardComparison != 0 ) {
        return cardComparison;
      }
    }

    return 0;
  }

  static class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand hand1, Hand hand2) {

      int typeComparison = Integer.compare(hand1.getType(), hand2.getType());

      if (typeComparison == 0) {

        // if same type, compare card amounts
        return compareCardLists(hand1.cards, hand2.cards);
      }

      return typeComparison;
    }
  }

}
