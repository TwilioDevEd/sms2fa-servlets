package com.twilio.sms2fa.application.servlets;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.domain.service.CreateUser;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.io.IOException;
import java.util.HashSet;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class UsersServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private RequestDispatcher requestDispatcher;

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
        when(request.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);

        this.userRepository = new UserInMemoryRepository();
        this.createUser = new CreateUser(userRepository, messageSender);
    }

    @Test
    public void itShouldAddNewUserToSessionWhenPost() throws ServletException, IOException {
        UsersServlet usersServlet = new UsersServlet(createUser);

        when(request.getParameter("first_name")).thenReturn("Foo");
        when(request.getParameter("last_name")).thenReturn("Bar");
        when(request.getParameter("email")).thenReturn("foo@bar.com");
        when(request.getParameter("phone_number")).thenReturn("+111321321321321312");
        when(request.getParameter("password")).thenReturn("foo@123");

        //when
        usersServlet.doPost(request, response);

        //then
        verify(session).setAttribute(eq("user"), any(User.class));
    }

    @Test
    public void shouldHandleValidationErrors() throws ServletException, IOException {
        CreateUser mock = mock(CreateUser.class);
        when(mock.create(any(User.class))).thenThrow(new ConstraintViolationException(
                new HashSet<ConstraintViolation<?>>(){{
                    add(stubbedConstraintViolation());
                }}
        ));
        UsersServlet usersServlet = new UsersServlet(mock);

        //when
        usersServlet.doPost(request, response);

        //then
        verify(request).setAttribute(eq("errorMessage"), any(String.class));
    }

    private ConstraintViolation<User> stubbedConstraintViolation() {
        return new ConstraintViolation<User>() {
            @Override
            public String getMessage() {
                return "First Name may not be empty";
            }

            @Override
            public String getMessageTemplate() {
                return null;
            }

            @Override
            public User getRootBean() {
                return null;
            }

            @Override
            public Class<User> getRootBeanClass() {
                return null;
            }

            @Override
            public Object getLeafBean() {
                return null;
            }

            @Override
            public Object[] getExecutableParameters() {
                return new Object[0];
            }

            @Override
            public Object getExecutableReturnValue() {
                return null;
            }

            @Override
            public Path getPropertyPath() {
                return null;
            }

            @Override
            public Object getInvalidValue() {
                return null;
            }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() {
                return null;
            }

            @Override
            public <U> U unwrap(Class<U> type) {
                return null;
            }
        };
    }
}