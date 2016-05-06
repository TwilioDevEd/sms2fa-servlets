package com.twilio.sms2fa.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodeGeneratorTest {

    private CodeGenerator codeGenerator;

    @Before
    public void setUp() throws Exception {
        this.codeGenerator = new CodeGenerator();
    }

    @Test
    public void shouldGenerate6DigitCode(){
        String code = codeGenerator.generate();
        String regex = "^\\d{6}$";
        String msg = String.format("code %s should match pattern %s", code, regex);

        assertTrue(msg, code.matches(regex));
    }

}