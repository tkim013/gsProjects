package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;


public interface EmailService {

    void sendReservationReminder(Reservation reservation);
    void sendNewUserWelcomeMessage(UserAccount userAccount);
    boolean sendEmail(String to, String subject, String body, boolean sendAsync);
}
