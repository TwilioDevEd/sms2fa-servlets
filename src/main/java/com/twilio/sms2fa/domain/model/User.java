package com.twilio.sms2fa.domain.model;

import com.twilio.sms2fa.domain.exception.WrongVerificationCodeException;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class User {

    public static final int MAX_VERIFICATION_CODE = 100000;
    public static final int MIN_VERIFICATION_CODE = 999999;
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    @NotBlank(message = "First Name may not be blank")
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotBlank(message = "Last Name may not be blank")
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email format does not match")
    private String email;

    @NotBlank(message = "Password may not be blank")
    @Column(name = "PASSWORD")
    private String password;

    @NotBlank(message = "Phone Number may not be blank")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "VERIFICATION_CODE")
    private String verificationCode;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    // required by orm
    public User() {
    }

    public User(final String firstName, final String lastName,
                final String email, final String phoneNumber,
                final String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.confirmed = false;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.verificationCode = generateVerificationCode();
    }

    public static String generateVerificationCode() {
        Random rand = new Random();
        Integer code = rand.nextInt(MIN_VERIFICATION_CODE
                - MAX_VERIFICATION_CODE + 1) + MAX_VERIFICATION_CODE;
        return code.toString();
    }

    public void confirm(final String verificationCode) {
        if (!this.verificationCode.equals(verificationCode)) {
            throw new WrongVerificationCodeException(verificationCode);
        }
        confirmed = true;
    }

    public Long getId() {
        return id;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void generateNewVerificationCode() {
        this.verificationCode = generateVerificationCode();
    }

    public boolean authenticate(final String password) {
        return BCrypt.checkpw(password, this.password);
    }
}
