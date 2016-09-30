package com.it1901.booking.Application.Event.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private final String from = "samfundet@fireman.net";

    private final String host = "smtp.mail.com";
    private final String password = "12345678";
    private final String port = "587"; //587, 465, 25

    public void sendEmail(Email email, String managerEmail) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.socketFactory.port", port);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(managerEmail));
            message.setSubject(email.emailSubject);
            message.setContent(email.emailBody, "text/html");
            Transport.send(message);
            System.out.println("Message sent.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
