package com.it1901.booking.Application;

import java.sql.*;

public class DatabaseHandler {

        private Connection con;

        public DatabaseHandler(String dbclass, String host, String user, String pass) throws SQLException, ClassNotFoundException {
            Class.forName(dbclass);
            this.con = DriverManager.getConnection(host, user, pass);
            if (con != null) {
                System.out.println("Connected to database.\n");
            }
            else {
                System.out.println("Could not establish a connection to the database.\n");
            }
        }

        public PreparedStatement prepareQuery(String query) throws SQLException {
            return con.prepareStatement(query);
        }

        //writes a ResultSet to console
        public int displayResult(ResultSet result) throws SQLException {
            ResultSetMetaData resultMeta = result.getMetaData();
            int columnsNumber = resultMeta.getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(result.getString(i) + " ");
                }
                System.out.println();
            }
            return 0;
        }
}
