package com.twilio.sms2fa.infrastructure;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import com.twilio.sms2fa.infrastructure.service.TwilioMessageSender;
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

    private static final String TWILIO_NUMBER = "my-twilio-phone-number";

    private TwilioMessageSender twilioMessageSender;

    @Mock
    private MessageFactory messageFactory;

    @Mock
    private Message message;

    @Before
    public void setUp() throws TwilioRestException {
        initMocks(this);
        this.twilioMessageSender = new TwilioMessageSender(
                messageFactory, TWILIO_NUMBER);
        when(messageFactory.create(anyList())).thenReturn(message);
    }

    @Test
    public void shouldSendSmsToGivenPhoneAndCode() throws Exception {
        User user = new UserBuilder().build();

        twilioMessageSender.sendCode(user);

        verify(messageFactory).create(asList(
                new BasicNameValuePair("From", TWILIO_NUMBER),
                new BasicNameValuePair("To", user.getPhoneNumber()),
                new BasicNameValuePair("Body", user.getVerificationCode())
        ));
    }

    @Test
    public void shouldReturnTrueWhenStatusIsQueued() throws Exception {
        User user = new UserBuilder().build();
        when(message.getStatus()).thenReturn("queued");

        boolean result = twilioMessageSender.sendCode(user);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenStatusIsQueued() throws Exception {
        User user = new UserBuilder().build();
        when(message.getStatus()).thenReturn("failed");

        boolean result = twilioMessageSender.sendCode(user);

        assertFalse(result);
    }
}
