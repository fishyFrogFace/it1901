package com.it1901.booking.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Artist {

    public static ArrayList<String> getArtistNames(DatabaseHandler dbh) throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        ResultSet rs = getArtists(dbh);
        rs.next();
        while (!rs.isAfterLast()) {
            names.add(rs.getString(1));
            rs.next();
        }
        return names;
    }

    private static ResultSet getArtists(DatabaseHandler dbh) throws SQLException {
        String query = "SELECT name FROM artist";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        return prepStatement.executeQuery();
    }

    public static Integer fetchArtistID(DatabaseHandler dbh, String name) throws SQLException {
        String query = "SELECT artistID FROM artist " +
                "WHERE name = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, name);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
