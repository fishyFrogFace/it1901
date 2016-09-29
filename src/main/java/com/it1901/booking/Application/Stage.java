package com.it1901.booking.Application;

import java.sql.ResultSet;

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

}
