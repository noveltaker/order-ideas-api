package com.example.order.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomUtilsTest {

    @Test
    @DisplayName("랜덤값 출력")
    void getRandomValue() {

        StringBuilder randomBuilder = new StringBuilder();

        int length = 10;

        boolean useLetters = true;

        boolean useNumbers = false;

        int randomNum = (int) (Math.random() * 10000);

        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        randomBuilder.append(generatedString).append(randomNum);

        System.out.println(randomBuilder);
    }

}
