package com.it1901.booking.Application;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class EmailHandler {

    private final  String from = "samfundet@fireman.net";
    private String to;
    private String subject;
    private String body;

    private final String host = "smtp.mail.com";
    private final String password = "12345678";
    private final String port = "587"; //587, 465, 25

    public EmailHandler(String artistEmail, String subject, String body) {
        this.to = artistEmail;
        this.subject = subject;
        this.body = body;
    }

    public void sendEmail() {
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
            System.out.println("Message sent.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    //inserts a new email
    private void saveEmail(String subject, String body, int offerID, DatabaseHandler dbh) throws SQLException {
        String query = "INSERT INTO email VALUES " +
                "(DEFAULT, ?, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, subject);
        prepStatement.setString(2, body);
        prepStatement.setInt(3, offerID);
        prepStatement.executeUpdate();
    }

    public static void main(String[] args) {
        EmailHandler em = new EmailHandler("prosjektarbeidartist@mail.com", "Hei", "Jeg kan sende email. Wow!");
        em.sendEmail();
    }
}
