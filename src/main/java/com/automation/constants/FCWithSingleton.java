package com.automation.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FCWithSingleton {

  // Singleton design pattern - Single instance for a class at a particular point of time
  // It is part of creational design pattern
  private static FCWithSingleton INSTANCE = null;

  private static synchronized FCWithSingleton getInstance() { // To make it Thread-Safe
    if (INSTANCE == null) {
      INSTANCE = new FCWithSingleton();
    }
    return INSTANCE;
  }

}
