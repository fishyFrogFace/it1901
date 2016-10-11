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
    public void saveEmail(DatabaseHandler dbh) throws SQLException {
        String query =
                "INSERT INTO email VALUES " +
                "(DEFAULT, ?, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, this.emailSubject);
        prepStatement.setString(2, this.emailBody);
        prepStatement.setInt(3, offerID);
        prepStatement.executeUpdate();
    }

    //fetches an email stored in the database
    public static Email fetchEmail(Integer offerID, DatabaseHandler dbh) throws SQLException {
        String query =
                "SELECT * FROM email " +
                "WHERE offerID = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, offerID);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return email()
                .withEmailID(rs.getInt(1))
                .withEmailSubject(rs.getString(2))
                .withEmailBody(rs.getString(3))
                .withOfferID(rs.getInt(4))
                .build();
    }

    public Boolean sendThisEmail(DatabaseHandler dbh) throws SQLException {
        String managerEmail = getManagerEmail(dbh);
        if (!managerEmail.equals("noEmail")) {
            EmailSender.sendEmail(this, managerEmail);
            return true;
        } else {
            return false;
        }
    }

    private String getManagerEmail(DatabaseHandler dbh) throws SQLException {
        String query = "SELECT artist.email " +
                "FROM artist, offer, concert, email " +
                "WHERE artist.artistID = concert.artistID " +
                "AND offer.offerID = ? " +
                "AND offer.offerID = email.offerID";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, offerID);
        ResultSet rs = prepStatement.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return "noEmail";
        }
    }
}
