package com.klapperking.aoc.day03.solution.part01;

public class EnginePart {

  public boolean used = false;

  public int colIndexleftBound = -1;
  public int colIndexRightBound;

  public int rowIndex;

  public String numberString = "";

  public void addDigit(int row, int col, String digitString) {


    this.rowIndex = row;

    if (colIndexleftBound == -1) {
      colIndexleftBound = col;
    }

    this.colIndexRightBound = col;

    this.numberString += digitString;
  }
}
