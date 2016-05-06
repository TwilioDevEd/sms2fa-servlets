package com.twilio.sms2fa.domain.service;

import java.util.Random;

public class CodeGenerator {

    private static final int MIN_VALUE = 100000;
    private static final int MAX_VALUE = 999999;

    public static String generateConfirmationCode(){
        Random rand = new Random();
        Integer code = rand.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
        return code.toString();
    }
}
