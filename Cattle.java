package com.farm.animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Cattle extends Animal {
    private boolean isForMilk;

    public Cattle(String tagNumber, String breed, String gender, String status, boolean isForMilk) {
        super(tagNumber, breed, gender, status);
        this.isForMilk = isForMilk;
    }

    @Override
    public void displayInfo() {
        System.out.println("Cattle [Tag: " + getTagNumber() +
                ", Breed: " + getBreed() +
                ", Gender: " + getGender() +
                ", Status: " + getStatus() +
                ", Milk Production: " + isForMilk + "]");
    }

    public void saveToDB() {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO Cattle (tagNumber, breed, gender, status, isForMilk) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getTagNumber());
            stmt.setString(2, getBreed());
            stmt.setString(3, getGender());
            stmt.setString(4, getStatus());
            stmt.setBoolean(5, isForMilk);
            stmt.executeUpdate();
            System.out.println("Cattle saved to DB!");
        } catch (SQLException e) {
            System.out.println("Error saving Cattle: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}

