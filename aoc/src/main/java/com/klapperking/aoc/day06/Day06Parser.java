package com.klapperking.aoc.day06;

import java.io.*;
import java.util.*;

public class Day06Parser {

  private Map<String, Long[]> raceInfo;
  private boolean part1 = true;

  public Day06Parser(InputStream inputStream, boolean part1) {

    this.part1 = part1;
    this.raceInfo = new HashMap<>();
    parseFile(inputStream);
  }

  private void parseFile(InputStream inputStream) {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      List<String> times = new ArrayList<>();
      List<String> distances = new ArrayList<>();

      while ((line = br.readLine()) != null) {

        if (!part1) {
          line = line.replaceAll(" ", "");
        }

        if (line.startsWith("Time:")) {
          String[] timeInfo = line.replaceAll("Time:", "").trim().split(" ");
          for (String timeCandidate : timeInfo) {
            if (!timeCandidate.isEmpty()) {
              times.add(timeCandidate);
            }
          }
        } else {
          String[] distanceInfo = line.replaceAll("Distance:", "").trim().split(" ");
          for (String distanceCandidate : distanceInfo) {
            if (!distanceCandidate.isEmpty()) {
              distances.add(distanceCandidate);
            }
          }
        }
      }

      for (int i = 0; i < times.size(); i++) {

        Long time = Long.parseLong(times.get(i));
        Long distance = Long.parseLong(distances.get(i));

        Long[] pair = {time, distance};

        this.raceInfo.put("race " + i, pair);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Map<String, Long[]> getRaceInfo() {
    return this.raceInfo;
  }

}
