package com.klapperking.aoc.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Day12Part02 {

  private HashMap<List<Integer>, Long> possibilityCounts;

  public Day12Part02() {
    this.possibilityCounts = new HashMap<>();
  }

  public long part2(String currentSprings, List<String> groupInfo, int currentSpringPosition, int currentGroupIndex, int currentGroupSize) {

    // Unique Key from the 3 values listed below (Objects.hash did not work)
    List<Integer> uniqueKey = new ArrayList<>(Arrays.asList(currentSpringPosition, currentGroupIndex, currentGroupSize));

    // if we know what the combo of
      //the position we are at
      //the group we are trying to fill
      // its current size
    // we can re-use our result

    if (this.possibilityCounts.containsKey(uniqueKey)) {
      return this.possibilityCounts.get(uniqueKey);
    }


    // string is filled completely
    if (currentSpringPosition == currentSprings.length()) {

      // if all groups are fulfilled and nothing is left, this is a valid combination
      if (currentGroupIndex == groupInfo.size() && currentGroupSize == 0) {
        long result = 1;
        return result;
      }

      // if all groups except the last are filled and the leftover has the right size, this is a valid combination
      if ((currentGroupIndex == groupInfo.size() - 1) && currentGroupSize == Integer.parseInt(groupInfo.get(currentGroupIndex))) {
        long result= 1;
        return result;
      }

      // if none of that, its not valid
      long result = 0;
      return result;
    }

    long result = 0;
    // if the string is not filled, check if there is need to look at the next group, make group bigger and try again
    String currentValue = String.valueOf(currentSprings.charAt(currentSpringPosition));

    List<String> questionMarkPos = new ArrayList<>(Arrays.asList(".", "#"));

    // for both possibilities of either . or #; Treat the case when they occur normally or fill them if there is a ?
    for (String possibility : questionMarkPos) {

      // consider both . and # at this position (either natural or via ?)
      if (currentValue.equals(possibility) || currentValue.equals("?")) {

        // if there was no # before this dot, just go to next character
        if (possibility.equals(".") && currentGroupSize == 0) {
          result += part2(currentSprings, groupInfo, currentSpringPosition + 1, currentGroupIndex, 0);

        // if there is an active group before this dot, there are groups left to close and the current group is as big as it should be, go to next character and next group
        } else if (possibility.equals(".") && currentGroupSize > 0 && currentGroupIndex < groupInfo.size() && Integer.parseInt(groupInfo.get(currentGroupIndex)) == currentGroupSize) {
          result += part2(currentSprings, groupInfo, currentSpringPosition + 1, currentGroupIndex + 1, 0);

        // if a hashtag is hit, go to next character and increase the group size
        } else if (possibility.equals("#")) {
          result += part2(currentSprings, groupInfo, currentSpringPosition + 1, currentGroupIndex, currentGroupSize + 1);
        }
      }
    }

    // remember the combination and return the accumulated result
    this.possibilityCounts.put(uniqueKey, result);
    return result;
  }
}
