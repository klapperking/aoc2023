package com.klapperking.aoc.day03.solution.part01;

import java.util.List;
import java.util.ArrayList;

public class Part01Solution {
  public static long solution(String input, boolean part1) {

    long result = 0;

    int lineLength = input.split("\n")[0].length();

    int rowIndex = 0;
    int colIndex = 0;

    List<EnginePart> parts = new ArrayList<>();
    EnginePart currentEnginePart = null;

    List<SpecialChar> specials = new ArrayList<>();

    for (String line : input.split("\n")) {

      for (String character : line.split("")) {

        // Account for Wrap-Around , If at last position of column, the part is complete as well
        if ((colIndex == 0) && (currentEnginePart != null)) {
          parts.add(currentEnginePart);
          currentEnginePart = null;
        }

        // if its a digit, try to make an engine part
        if (Character.isDigit(character.charAt(0))) {

          if (currentEnginePart == null) {
            currentEnginePart = new EnginePart();
          }

          currentEnginePart.addDigit(rowIndex, colIndex, character);

        // if not a digit and part exists, part is complete
        } else {

          if (currentEnginePart != null) {
            parts.add(currentEnginePart);
            currentEnginePart = null;
          }
        }

        // if character is not . it must be a special, so save it
        if ((!character.equals(".")) & (!Character.isDigit(character.charAt(0)))) {

          SpecialChar special = new SpecialChar(rowIndex, colIndex, character);
          specials.add(special);

        }

        colIndex += 1;
      }

      colIndex = 0;
      rowIndex += 1;
    }

    if (part1 == true) {
      for (EnginePart part : parts) {

        for (int rowPos = part.rowIndex - 1; rowPos <= part.rowIndex + 1; rowPos++) {

          if ((rowPos < 0) || (rowPos > lineLength)) {
            continue;
          }

          for (int colPos = part.colIndexleftBound - 1; colPos <= part.colIndexRightBound + 1; colPos++) {

            if ((colPos < 0) || (colPos > lineLength)) {
              continue;
            }

            // look for a symbol
            for (SpecialChar special : specials) {

              if ((special.rowPos == rowPos) & (special.colPos == colPos)) {
                part.used = true;
                break;
              }
            }
          }
        }
      }
      for (EnginePart part : parts) {

        if (part.used == true) {
          result += Integer.parseInt(part.numberString);
        }
      }
    } else {

      List<EnginePart> gear = new ArrayList<>();

      for (SpecialChar special : specials) {

        // ignore everything that cant be a gear
        if (!special.valueString.equals("*")) {
          continue;
        }

        // if it can be a gear, check if there are exactly 2 part around it.
        for (int rowPos = special.rowPos - 1; rowPos <= special.rowPos + 1; rowPos++) {

          if ((rowPos < 0) || (rowPos > lineLength)) {
            continue;
          }

          for (int colPos = special.colPos - 1; colPos <= special.colPos + 1; colPos++) {

            if ((colPos < 0) || (colPos > lineLength)) {
              continue;
            }

            for (EnginePart part : parts) {

              if (rowPos == part.rowIndex) {

                if ((colPos >= part.colIndexleftBound) & (colPos <= part.colIndexRightBound)) {

                  if (!gear.contains(part)) {
                    gear.add(part);
                    break;
                  }

                }
              }
            }
          }
        }

        if (gear.size() == 2) {
          result += (Integer.parseInt(gear.get(0).numberString) * Integer.parseInt(gear.get(1).numberString));
        }
        gear = new ArrayList<>();
      }
    }
    return result;
  }
}
