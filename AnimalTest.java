package com.farm.animal;

public class AnimalTest {
    public static void main(String[] args) {
        Animal cow = new Cattle("C001", "Holstein", "Female", "Healthy", true);
        Animal sheep = new Sheep("S001", "Merino", "Male", "Sick");
        Animal chicken = new Poultry("P001", "Broiler", "Female", "Sold");

        cow.displayInfo();
        sheep.displayInfo();
        chicken.displayInfo();
    }
}

