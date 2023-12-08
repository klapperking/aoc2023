package com.klapperking.aoc.day05;

public class RangeMap {

  public Range sourceRange;
  public Range destRange;

  public RangeMap(long sourceStart, long destStart, long length) {

    this.sourceRange = new Range(sourceStart, sourceStart + length);
    this.destRange = new Range(destStart, destStart + length);
  }

}
