package com.farm.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Farm {
    private final String farmName;
    private final String ownerName;
    private final String town;
    private final String country;
    private final List<Animal> animals = new ArrayList<>();

    public Farm(String farmName, String ownerName, String town, String country) {
        this.farmName = farmName;
        this.ownerName = ownerName;
        this.town = town;
        this.country = country;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public long getTotalActiveCount() {
        return animals.stream()
                .filter(a -> {
                    AnimalStatus s = AnimalStatus.fromString(a.getStatus());
                    return s == AnimalStatus.ACTIVE || s == AnimalStatus.HEALTHY || s == AnimalStatus.SICK;
                })
                .count();
    }

    public long getCountByStatus(AnimalStatus status) {
        return animals.stream()
                .filter(a -> AnimalStatus.fromString(a.getStatus()) == status)
                .count();
    }

    public Map<String, Long> getActiveCountBySpecies() {
        return animals.stream()
                .filter(a -> {
                    AnimalStatus s = AnimalStatus.fromString(a.getStatus());
                    return s == AnimalStatus.ACTIVE || s == AnimalStatus.HEALTHY || s == AnimalStatus.SICK;
                })
                .collect(Collectors.groupingBy(
                    a -> a.getClass().getSimpleName(), 
                    Collectors.counting()
                ));
    }

    public List<Animal> getAnimalsByStatus(AnimalStatus status) {
        return animals.stream()
                .filter(a -> AnimalStatus.fromString(a.getStatus()) == status)
                .collect(Collectors.toList());
    }

    // Getters for report
    public String getFarmName() { return farmName; }
    public String getOwnerName() { return ownerName; }
    public String getTown() { return town;    }
    public String getCountry(){return country; }
    public List<Animal> getAllAnimals() { return animals; }

    
public long getCountByStatus(String status) {
    return animals.stream()
            .filter(a -> a.getStatus().equalsIgnoreCase(status))
            .count();
}

    Object getAnimals() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
