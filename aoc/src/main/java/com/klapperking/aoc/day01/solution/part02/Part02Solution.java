package com.klapperking.aoc.day01.solution.part02;

public class Part02Solution {

  public static String extractOriginal(String lineContent) {

    String digits = filterNonDigits(lineContent);
    int lastIndex = digits.length() - 1 ;

    String original = "" + digits.charAt(0) + digits.charAt(lastIndex);
    return original;
  }

  private static String filterNonDigits(String input) {

    String[] digit_representations = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    StringBuilder result = new StringBuilder();
    StringBuilder considerStack = new StringBuilder();

    int index = 0;
    while (index < input.length()) {
      char currentChar = input.charAt(index);

      if (Character.isDigit(currentChar)) {
        result.append(currentChar);
      } else {
        considerStack.append(currentChar);
      }

      // if stack contains a word-representation of a digit, convert to digit and remove from stack
      if (considerStack.toString().length() >= 3) {

        for (int i = 0; i < digit_representations.length; i++) {

          if (considerStack.toString().contains(digit_representations[i])) {
            result.append(Integer.toString(i));

            // keep the last character of a found digit word-repr, since it could be used as 0th char in next word-repr
            considerStack.delete(0, considerStack.length() - 1);
          }
        }
      }

      index += 1;
    }
    return result.toString();
  }

}
