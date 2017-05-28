/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.utilities;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author 1003
 */
public class CodeGenerator {

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public String getCode() {
        StringBuilder ret = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            ret.append(random.nextInt(10));
        }
        return ret.toString();
    }

    public BigInteger getITS() {
        StringBuilder ret = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            ret.append(random.nextInt(10));
        }
        return new BigInteger(ret.toString());
    }

    public String getITSRandomPassword() {

        StringBuilder ret = new StringBuilder();

        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}
