package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.service.CodeGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodeGeneratorTest {

    @Test
    public void shouldGenerate6DigitCode(){
        String code = CodeGenerator.generateConfirmationCode();
        String regex = "^\\d{6}$";
        String msg = String.format("code %s should match pattern %s", code, regex);

        assertTrue(msg, code.matches(regex));
    }

}