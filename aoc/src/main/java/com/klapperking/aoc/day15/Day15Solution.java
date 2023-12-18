package com.klapperking.aoc.day15;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Day15Solution {

  public static void main(String[] args) {

    boolean part1 = false;

    InputStream inputStream = Day15Solution.class.getResourceAsStream("/day15/input.txt");
    Day15Parser parser = new Day15Parser(inputStream);
    String[] initSequence = parser.initSequence;

    long result = 0;
    if (part1) {
      for (String step : initSequence) {

        result += (long) HASH(step);
      }
    } else {

      HashMap<Integer, LinkedHashMap<String, Integer>> boxes = new HashMap<>();

      for (String step : initSequence) {

        String[] labelAndBox = step.split("[=-]");
        String label = labelAndBox[0];
        int boxNumber = HASH(label);

        // if is =
        if (labelAndBox.length != 1) {
          int focalLength = Integer.parseInt(labelAndBox[1]);

          // if that box exists, replace the lens or add the lens to the next box
          if (boxes.containsKey(boxNumber)) {

            boxes.get(boxNumber).put(label, focalLength);

          // else create the box with this lens
          } else {

            LinkedHashMap<String, Integer> lenses = new LinkedHashMap<>();
            lenses.put(label, focalLength);

            boxes.put(boxNumber, lenses);
          }

        // if is -
        } else {

          if (boxes.containsKey(boxNumber)) {

            if (boxes.get(boxNumber).containsKey(label)) {
              boxes.get(boxNumber).remove(label);
            }
          }
        }
      }

      // After all boxes are done make the result
      for (HashMap.Entry<Integer, LinkedHashMap<String, Integer>> box : boxes.entrySet()) {

        int boxNumber = box.getKey();
        LinkedHashMap<String, Integer> lenses = box.getValue();

        int count = 0;
        for (HashMap.Entry<String, Integer> lens : lenses.entrySet()) {

          count++;

          int focalLength = lens.getValue();

          result += (boxNumber + 1) * count * focalLength;
        }
      }
    }

    System.out.println(result);
  }

  private static int HASH(String step) {

    int hashValue = 0;

    for (int i = 0; i < step.length(); i++) {

      hashValue += (int) step.charAt(i);
      hashValue *= 17;
      hashValue = hashValue % 256;
    }

    return hashValue;
  }
}
