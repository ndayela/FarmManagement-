package com.farm.animal;
public class Sheep extends Animal {
    public Sheep(String tagNumber, String breed, String gender, String status) {
        super(tagNumber, breed, gender, status);
    }

    @Override
    public void displayInfo() {
        System.out.println("Sheep [Tag: " + getTagNumber() +
                ", Breed: " + getBreed() +
                ", Gender: " + getGender() +
                ", Status: " + getStatus() + "]");
    }
}

