package com.farm.animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Poultry extends Animal {
    public Poultry(String tagNumber, String breed, String gender, String status) {
        super(tagNumber, breed, gender, status);
    }

    @Override
    public void displayInfo() {
        System.out.println("Poultry [Tag: " + getTagNumber() +
                ", Breed: " + getBreed() +
                ", Gender: " + getGender() +
                ", Status: " + getStatus() + "]");
    }
    public void saveToDB() {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO Poultry (tagNumber, breed, gender, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getTagNumber());
            stmt.setString(2, getBreed());
            stmt.setString(3, getGender());
            stmt.setString(4, getStatus());
            stmt.executeUpdate();
            System.out.println("Poultry saved to DB!");
        } catch (SQLException e) {
            System.out.println("Error saving Poultry: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}

