package com.automation.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FakerUtils {

  private static final Faker faker = new Faker();

  static int generateNumber(int min, int max) {
    return faker.number().numberBetween(min, max);
  }

  static String generateFirstname() {
    return faker.name().firstName();
  }

  static String generateLastname() {
    return faker.name().lastName();
  }
}
