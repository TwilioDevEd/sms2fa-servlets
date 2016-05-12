package com.twilio.sms2fa.application.servlets;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.domain.service.CreateUser;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class UsersServletTest {

    private UsersServlet usersServlet;

    @Mock
    private HttpServletRequest request;

    private UserRepository userRepository;

    private CreateUser createUser;

    @Mock
    private MessageSender messageSender;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp(){
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("first_name")).thenReturn("Foo");
        when(request.getParameter("last_name")).thenReturn("Bar");
        when(request.getParameter("email")).thenReturn("foo@bar.com");
        when(request.getParameter("phone_number")).thenReturn("+111321321321321312");
        when(request.getParameter("password")).thenReturn("foo@123");

        this.userRepository = new UserInMemoryRepository();
        this.createUser = new CreateUser(userRepository, messageSender);

        this.usersServlet = new UsersServlet(createUser);

    }

    @Test
    public void itShouldAddNewUserToSessionWhenPost() throws ServletException, IOException {
        //when
        usersServlet.doPost(request, response);

        //then
        verify(session).setAttribute(eq("user"), any(User.class));
    }
}