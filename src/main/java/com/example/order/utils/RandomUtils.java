package com.example.order.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static String getRandomValue() {
        StringBuilder randomBuilder = new StringBuilder();

        int length = 10;

        boolean useLetters = true;

        boolean useNumbers = false;

        int randomNum = (int) (Math.random() * 10000);

        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        randomBuilder.append(generatedString).append(randomNum);

        return randomBuilder.toString();
    }
}
