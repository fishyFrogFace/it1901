package com.it1901.booking.Application.Event.Email;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.Event.Offer.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.it1901.booking.Application.Event.Email.EmailBuilder.email;

public class Email {
    private final Integer emailID;
    protected final String emailSubject;
    protected final String emailBody;
    private final Integer offerID;

    public Email(EmailBuilder builder) {
        this.emailID = builder.emailID;
        this.emailSubject = builder.emailSubject;
        this.emailBody = builder.emailBody;
        this.offerID = builder.offerID;
    }

    //inserts a new email into the database
    private void saveEmail(String subject, String body, int offerID, DatabaseHandler dbh) throws SQLException {
        String query =
                "INSERT INTO email VALUES " +
                "(DEFAULT, ?, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, subject);
        prepStatement.setString(2, body);
        prepStatement.setInt(3, offerID);
        prepStatement.executeUpdate();
    }

    //fetches an email stored in the database
    public Email fetchEmail(int emailID, DatabaseHandler dbh) throws SQLException {
        String query =
                "SELECT * FROM email " +
                "WHERE emailID = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, emailID);
        ResultSet rs = prepStatement.executeQuery();
        return email()
                .withEmailID(rs.getInt(1))
                .withEmailSubject(rs.getString(2))
                .withEmailBody(rs.getString(3))
                .withOfferID(rs.getInt(4))
                .build();
    }
}
