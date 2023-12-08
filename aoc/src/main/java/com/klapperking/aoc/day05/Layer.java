package com.klapperking.aoc.day05;

import java.util.*;

public class Layer {

  public String sourceName;
  public String destName;

  private List<RangeMap> rangeMaps = new ArrayList<>();;

  public Layer(String sourceName, String destName) {
    this.sourceName = sourceName;
    this.destName = destName;
  }

  public void addRangeMap(RangeMap rangeMap) {
    this.rangeMaps.add(rangeMap);
  }

  // apply this layers RangeMaps to an every input Range given
  public List<Range> feedThrough(List<Range> inputRanges) {

    // if a range has been consumed by a map, it doesnt need to be processed by the other maps
    List<Range> passThrough = new ArrayList<>();

    for (RangeMap rangeMap : rangeMaps) {

      List<Range> toProcess = new ArrayList<>();

      while (!inputRanges.isEmpty()) {

        // get last input Range
        Range inputRange = inputRanges.get(inputRanges.size() - 1);
        inputRanges.remove(inputRanges.size() - 1);

        // apply the current map to the range
        List<Range> newRanges = inputRange.intersectRange(rangeMap.sourceRange);

        Range below = newRanges.get(0);
        Range intersection = newRanges.get(1);
        Range above = newRanges.get(2);

        // non-altered ranges go to next map in layer
        if (below.end > below.start) {
          toProcess.add(below);
        }

        // intersection ranges to next layer directly
        if (intersection.end > intersection.start) {

          Range mappedRange = new Range(
            intersection.start - rangeMap.sourceRange.start + rangeMap.destRange.start,
            intersection.end - rangeMap.sourceRange.start + rangeMap.destRange.start
            );

          passThrough.add(mappedRange);
        }

        // non-altered go to next map in layer
        if (above.end > above.start) {
          toProcess.add(above);
        }
      }

      inputRanges = toProcess;
    }

    // whatever made it through the layer (changed or unchanged) will be returned
    for (Range leftover : inputRanges) {
      passThrough.add(leftover);
    }

    return passThrough;
  }
}
