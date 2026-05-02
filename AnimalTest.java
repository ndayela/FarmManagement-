package com.farm.animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;

public class AnimalTest {
    public static void main(String[] args) {


        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("✅ Connected to Farm_System successfully!");
            DBConnection.closeConnection(conn);
        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }

        Cattle cow = new Cattle("C001", "Holstein", "Female", "Healthy", true);
        Sheep sheep = new Sheep("S001", "Merino", "Male", "Sick");
        Poultry chicken = new Poultry("P001", "Broiler", "Female", "Sold");

        cow.displayInfo();
        sheep.displayInfo();
        chicken.displayInfo();


        cow.saveToDB();
        sheep.saveToDB();
        chicken.saveToDB();
    }
}

