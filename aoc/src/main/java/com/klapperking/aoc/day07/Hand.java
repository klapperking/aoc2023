package com.klapperking.aoc.day07;

import java.util.*;

public class Hand {

  private int type;
  private int bid;

  private List<Integer> cards;

  public Hand(String[] cardStrings, Integer bid) {

    this.bid = bid;
    this.cards = new ArrayList<>();

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
          this.cards.add(11);
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
    calcType();
  }

  public Integer getType() {
    return this.type;
  }

  public Integer getBid() {
    return this.bid;
  }


  private void calcType() {

    HashMap<Integer, Integer> counts = new HashMap<>();

    for (int card : this.cards) {

      if (counts.get(card) == null) {
        counts.put(card, 1);
        continue;
      }

      counts.put(card, counts.get(card) + 1);
    }

    switch (counts.size()) {
      // high card
      case 5:
        this.type = 0;
        break;
      // pair
      case 4:
        this.type = 1;
        break;
      // three-pair or 2 two-pairs
      case 3:
        if (counts.values().contains(3)) {
          this.type = 3;
        } else {
          this.type = 2;
        }
        break;
      // four-pair or fullhouse
      case 2:
        if (counts.values().contains(3)) {
          this.type = 4;
        } else {
          this.type = 5;
        }
        break;
      // five-pair
      case 1:
        this.type = 6;
        break;
    }
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
