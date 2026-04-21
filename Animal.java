package com.farm.animal;

public abstract class Animal {
    private String tagNumber;
    private String breed;
    private String gender;
    private String status; // Healthy, Sick, Sold

    // Constructor
    public Animal(String tagNumber, String breed, String gender, String status) {
        this.tagNumber = tagNumber;
        this.breed = breed;
        this.gender = gender;
        this.status = status;
    }

    // Getters and Setters
    public String getTagNumber() { return tagNumber; }
    public void setTagNumber(String tagNumber) { this.tagNumber = tagNumber; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Abstract method (Polymorphism)
    public abstract void displayInfo();
}

