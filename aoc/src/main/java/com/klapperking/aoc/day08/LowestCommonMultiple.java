package com.klapperking.aoc.day08;

import java.math.BigInteger;

public class LowestCommonMultiple {

  // lowest common multiple
  public static BigInteger lcm(BigInteger bigA, BigInteger bigB) {
    BigInteger result = (bigA.multiply(bigB)).divide(bigA.gcd(bigB));
    return result;
  }

  // lcm of array
  public static BigInteger lcmOfArray(Long[] longArray) {
    BigInteger result = BigInteger.valueOf(1);
    for (Long num : longArray) {

      result = lcm(result, BigInteger.valueOf(num));
    }
    return result;
  }
}
