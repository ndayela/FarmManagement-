package com.farm.animal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportTest {
    public static void main(String[] args) {
        
        // 1. Create the farm object
        Farm myFarm = new Farm("Sunrise Farm", "Van Booysen", "Otjiwarongo, Namibia");
        
        // 2. Option A: Add animals manually for testing
        Cattle cow1 = new Cattle("C001", "Holstein", "Female", "HEALTHY", true);
        Cattle cow2 = new Cattle("C002", "Brahman", "Male", "DEAD", false);
        Sheep sheep1 = new Sheep("S001", "Dorper", "Male", "LOST");
        Poultry chicken1 = new Poultry("P001", "Broiler", "Female", "STOLEN");
        
        myFarm.addAnimal(cow1);
        myFarm.addAnimal(cow2);
        myFarm.addAnimal(sheep1);
        myFarm.addAnimal(chicken1);
        
        // 2. Option B: Load animals from DB instead
        // loadAnimalsFromDB(myFarm);
        
        // 3. Generate the report - this is where Farm + ReportGenerator connect
        ReportGenerator generator = new ReportGenerator();
        generator.generateTextReport(myFarm, "livestock_report_2026-05-05.txt");
        
        // 4. Optional: Print to console too
        System.out.println("\nTotal active animals: " + myFarm.getTotalActiveCount());
        System.out.println("Dead animals: " + myFarm.getCountByStatus("DEAD"));
    }
    
    // Helper method to load from your DB tables
    public static void loadAnimalsFromDB(Farm farm) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            
            // Load Cattle
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cattle");
            while (rs.next()) {
                Cattle c = new Cattle(
                    rs.getString("tagNumber"),
                    rs.getString("breed"),
                    rs.getString("gender"),
                    rs.getString("status"),
                    rs.getBoolean("isForMilk")
                );
                farm.addAnimal(c);
            }
            
            // Load Sheep
            rs = stmt.executeQuery("SELECT * FROM Sheep");
            while (rs.next()) {
                Sheep s = new Sheep(
                    rs.getString("tagNumber"),
                    rs.getString("breed"),
                    rs.getString("gender"),
                    rs.getString("status")
                );
                farm.addAnimal(s);
            }
            
            // Load Poultry
            rs = stmt.executeQuery("SELECT * FROM Poultry");
            while (rs.next()) {
                Poultry p = new Poultry(
                    rs.getString("tagNumber"),
                    rs.getString("breed"),
                    rs.getString("gender"),
                    rs.getString("status")
                );
                farm.addAnimal(p);
            }
            
            DBConnection.closeConnection(conn);
            System.out.println("Animals loaded from DB successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error loading from DB: " + e.getMessage());
        }
    }
}

    


