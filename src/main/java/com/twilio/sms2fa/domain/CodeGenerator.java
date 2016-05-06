package com.twilio.sms2fa.domain;

import java.util.Random;

public class CodeGenerator {

    private static final int MIN_VALUE = 100000;
    private static final int MAX_VALUE = 999999;

    public String generate(){
        Random rand = new Random();
        Integer code = rand.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
        return code.toString();
    }
}
