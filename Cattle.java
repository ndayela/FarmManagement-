package com.farm.animal;
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
}

