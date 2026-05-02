package com.farm.animal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String SERVER = "localhost\\SQLEXPRESS";
    private static final String PORT = "60962";
    private static final String DATABASE = "Farm_System";
;
    private static final String URL =
            "jdbc:sqlserver://" + SERVER + ":" + PORT + ";" +
                    "databaseName=" + DATABASE + ";" +
                    "integratedSecurity=true;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
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