package com.klapperking.aoc.day07;

import java.util.*;
import java.io.*;

public class Day07Parser {

  private List<Hand> hands = new ArrayList<>();

  public Day07Parser(InputStream inputStream) {
    parseFile(inputStream);
  }

  private void parseFile(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      while ((line = br.readLine()) != null) {

        // each line holds a a hand and a bid
        String[] handInfo = line.split(" ");

        int bid = Integer.parseInt(handInfo[1]);
        Hand hand = new Hand(handInfo[0].split(""), bid);

        hands.add(hand);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Hand> getHands() {
    return hands;
  }
}
