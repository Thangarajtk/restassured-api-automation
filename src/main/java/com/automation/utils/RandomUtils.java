package com.automation.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomUtils {

    // Business layer to handle business level changes

    public static int getId() {
        return FakerUtils.generateNumber(10,100);
    }

    public static String getFirstname() {
        return FakerUtils.generateFirstname().toLowerCase();
    }

    public static String getLastname() {
        return FakerUtils.generateLastname().toLowerCase();
    }
}
