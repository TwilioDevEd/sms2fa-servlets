package com.twilio.sms2fa.infrastructure;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sms2fa.domain.model.User;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class TwilioMessageSenderTest {

    private static final String MY_TWILIO_PHONE_NUMBER = "my-twilio-phone-number";

    private TwilioMessageSender twilioMessageSender;

    @Mock
    private MessageFactory messageFactory;

    @Mock
    private Message message;

    @Before
    public void setUp() throws TwilioRestException {
        initMocks(this);
        this.twilioMessageSender = new TwilioMessageSender(messageFactory, MY_TWILIO_PHONE_NUMBER);
        when(messageFactory.create(anyList())).thenReturn(message);
    }

    @Test
    public void shouldSendSmsToGivenPhoneAndCode() throws TwilioRestException {
        User user = new User("to-phone-number");
        user.generateVerificationCode();

        twilioMessageSender.sendCode(user);

        verify(messageFactory).create(asList(
                new BasicNameValuePair("From", MY_TWILIO_PHONE_NUMBER),
                new BasicNameValuePair("To", user.getPhoneNumber()),
                new BasicNameValuePair("Body", user.getVerificationCode())
        ));
    }

    @Test
    public void shouldReturnTrueWhenStatusIsQueued() throws TwilioRestException {
        User user = new User("to-phone-number");
        user.generateVerificationCode();
        when(message.getStatus()).thenReturn("queued");

        boolean result = twilioMessageSender.sendCode(user);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenStatusIsQueued() throws TwilioRestException {
        User user = new User("to-phone-number");
        user.generateVerificationCode();

        when(message.getStatus()).thenReturn("failed");

        boolean result = twilioMessageSender.sendCode(user);

        assertFalse(result);
    }
}