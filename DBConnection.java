package com.farm.animal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE = "Farm_System3";

    private static final String URL =
            "jdbc:sqlserver://" + SERVER + ":" + PORT + ";" +
                    "databaseName=" + DATABASE + ";" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";

    private static final String USER = "farmuser";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}


