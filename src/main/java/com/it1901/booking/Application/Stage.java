package com.it1901.booking.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stage {

    public enum stages {
        Storsalen, Klubben, Knaus, Selskapssiden, Strossa
    }

    private final int stageID;
    private final String name;
    private final int maxAudience;

    public Stage(int stageID, String name, int maxAudience) {
        this.stageID = stageID;
        this.name = name;
        this.maxAudience = maxAudience;
    }

    public static Integer fetchStageID(DatabaseHandler dbh, String name) throws SQLException {
        String query = "SELECT stageID FROM stage " +
                "WHERE name = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, name);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
