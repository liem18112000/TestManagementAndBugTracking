package com.pm.app.utils.number;

import com.pm.app.utils.CustomUtility;

import java.util.Random;

public class NumericHelper implements CustomUtility {
    public int getRandomNumber(int min, int max) {
        assert  max >= min;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
