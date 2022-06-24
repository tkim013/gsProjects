package com.genspark.backend.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//@Entity
//@Table(name = "user_accounts", uniqueConstraints = {
//        @UniqueConstraint(name="user_email_unique", columnNames = "user_email")})

public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "Name is required")
    private String userName;

    @Column(name = "user_number", nullable = false)
    @NotBlank(message = "Phone number is required")
//    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
//            message="Mobile number is invalid")
    private String userNumber;

    @Column(name = "user_password", nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Email
    @Column(name = "user_email", nullable = false)
    @NotBlank(message = "Email is required")
    private String email;

    public UserAccount() {
    }

    public UserAccount(String userName, String userNumber, String password, String email) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.password = password;
        this.email = email;
    }

    public UserAccount(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", primaryName='" + userName + '\'' +
                ", phoneNumber='" + userNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
