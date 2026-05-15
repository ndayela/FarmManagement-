package com.farm.animal;

import java.sql.*;
import java.util.List;

public class ReportTest {

    public static Farm loadFarmFromDatabase(String farmName, String owner, String town, String country) throws SQLException {
        Farm farm = new Farm(farmName, owner, town, country);
        
        try (Connection conn = DBConnection.getConnection()) {
            
            // Load CATTLE
            String sqlCattle = "SELECT TAGID, BREED, GENDER, STATE FROM CATTLE";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlCattle)) {
                while (rs.next()) {
                    int tagId = rs.getInt("TAGID");
                    String breed = rs.getString("BREED");
                    String gender = rs.getString("GENDER").trim();
                    String state = rs.getString("STATE");
                    farm.addAnimal(new Cattle(String.valueOf(tagId), breed, gender, state));
                }
            }
            
            // Load SHEEP
            String sqlSheep = "SELECT TAGID, BREED, GENDER, STATE FROM SHEEP";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlSheep)) {
                while (rs.next()) {
                    int tagId = rs.getInt("TAGID");
                    String breed = rs.getString("BREED");
                    String gender = rs.getString("GENDER").trim();
                    String state = rs.getString("STATE");
                    farm.addAnimal(new Sheep(String.valueOf(tagId), breed, gender, state));
                }
            }
            
            // Load POULTRY
            String sqlPoultry = "SELECT TAGID, BREED, GENDER, STATE FROM POULTRY";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlPoultry)) {
                while (rs.next()) {
                    int tagId = rs.getInt("TAGID");
                    String breed = rs.getString("BREED");
                    String gender = rs.getString("GENDER").trim();
                    String state = rs.getString("STATE");
                    farm.addAnimal(new Poultry(String.valueOf(tagId), breed, gender, state));
                }
            }
        }
        return farm;
    }

    //  MAIN METHOD
    public static void main(String[] args) {
        try {
            //  Loads from MySQL
            Farm myFarm = loadFarmFromDatabase("Sunrise Farm", "John Doe", "Otjiwarango", "Namibia");
            
            List<Animal> animals = (List<Animal>) myFarm.getAnimals(); // List not Object
        System.out.println("Count: " + animals.size()); // .size() works on List
        
            
            ReportGenerator generator = new ReportGenerator();
            String filename = "livestock_report_" + java.time.LocalDate.now() + ".txt";
            generator.generateTextReport(myFarm, filename);
            
            System.out.println("Report generated: " + filename);
            System.out.println("Total active: " + myFarm.getTotalActiveCount());
            System.out.println("Dead: " + myFarm.getCountByStatus("DEAD"));
            
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

}


