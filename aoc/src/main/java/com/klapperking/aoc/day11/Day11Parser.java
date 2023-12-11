package com.klapperking.aoc.day11;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day11Parser {

  public List<Node> galaxies = new ArrayList<>();

  public Day11Parser(InputStream inputStream, boolean part1) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      int lineNumber = 0;
      int verticalOffset = 0;
      List<Integer> emptyColumns = new ArrayList<>();

      while ((line = br.readLine()) != null) {

        // if first line fill empty column positions (this assumes symmetrical input)
        if (lineNumber == 0) {
          for (int i = 0; i < line.length(); i++) {
            emptyColumns.add(i);
          }
        }

        // if the line is empty, increase the vertical offset for all future nodes
        if (line.matches("\\.+")) {
          if (part1) {
            verticalOffset += 1;
          } else {
            verticalOffset += 999999;
          }
          lineNumber++;
          continue;
        }

        String[] lineContents = line.split("");

        for (int i = 0; i < lineContents.length; i++) {

          // if is galaxy, create node and remove position from empty column list
          if (lineContents[i].equals("#")) {
            Node galaxy = new Node(lineNumber + verticalOffset, i);
            galaxies.add(galaxy);

            // remove this position from empty col possibilites
            if (emptyColumns.contains(i)) {
              emptyColumns.remove(Integer.valueOf(i));
            }
          }
        }
        lineNumber++;
      }

      // empty columns need to apply their offset to every node on the right of them
      for (int emptyColIndex : emptyColumns) {
        for (Node galaxy : galaxies) {
          if (galaxy.originalY > emptyColIndex) {
            if (part1) {
              galaxy.y += 1;
            } else {
              galaxy.y += 999999;
            }
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
