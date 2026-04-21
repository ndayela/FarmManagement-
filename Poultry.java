package com.farm.animal;

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
}

