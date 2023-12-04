package com.klapperking.aoc.day04.solution.part01;

import java.util.List;
import java.util.ArrayList;

public class ScratchCard {

  public int id = 0;

  private List<Integer> winningNumbers = new ArrayList<>();
  private List<Integer> luckyNumbers = new ArrayList<>();

  public int matches = 0;
  public int score = 0;

  // construct with ID
  public ScratchCard(int id) {
    this.id = id;
  }

  // copy this instance
  public static ScratchCard copyMe(ScratchCard copyInstance) {
    return new ScratchCard(copyInstance.id);
  }


  public void addWinningNumber(int winningNumber) {
    this.winningNumbers.add(winningNumber);
  }

  public void addLuckyNumber(int luckyNumber) {

    this.luckyNumbers.add(luckyNumber);

    if (winningNumbers.contains(luckyNumber)) {

      this.matches++;
      updateScore();
    }

  }

  private void updateScore() {
    if (this.score == 0) {
      this.score += 1;
    } else {
      this.score *= 2;
    }
  }
}
