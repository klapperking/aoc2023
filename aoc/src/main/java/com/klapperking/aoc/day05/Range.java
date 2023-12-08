package com.klapperking.aoc.day05;

import java.util.*;

public class Range {
  public long start;
  public long end;

  public Range(long start, long end) {
    this.start = start;
    this.end = end;
  }

  public List<Range> intersectRange(Range inputRange) {

    List<Range> newRanges = new ArrayList<>();

    Range lowPart = new Range(this.start, Math.min(inputRange.start, this.end));
    Range intersection = new Range(Math.max(this.start, inputRange.start), Math.min(this.end, inputRange.end));
    Range highPart = new Range(Math.max(this.start, inputRange.end), this.end);

    newRanges.add(lowPart);
    newRanges.add(intersection);
    newRanges.add(highPart);

    return newRanges;
  }

}
