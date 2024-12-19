package com.mycompany.tubesakajava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GameSet;integratedSecurity=true;encrypt=false;trustServerCertificate=true";
    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static ResultSet getGameData() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL);

            if (connection != null) {
                String query = "SELECT * FROM Game";
                Statement statement = connection.createStatement();
                return statement.executeQuery(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
