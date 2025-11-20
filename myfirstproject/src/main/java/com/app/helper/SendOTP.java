package com.app.helper;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SendOTP {

    public static void main(String[] args) {

        // recipient and sender
        String to = "admin@rdec.in";
        String from = "vadh0923@gmail.com";

        // Mailtrap credentials
        final String username = "api";
        final String password = "9c8aee72e7e4ebdd64b57bf2df163e1e";

        // Mailtrap SMTP settings
        String host = "live.smtp.mailtrap.io";

        // SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Hello from the Mailtrap team");
            message.setText("Enjoy sending emails from Jakarta Mail!");

            // Send message
            Transport.send(message);

            System.out.println("✅ Email Message Sent Successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

