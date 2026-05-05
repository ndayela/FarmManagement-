package com.farm.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Farm {
    private final String farmName;
    private final String ownerName;
    private final String location;
    private final List<Animal> animals = new ArrayList<>();

    public Farm(String farmName, String ownerName, String location) {
        this.farmName = farmName;
        this.ownerName = ownerName;
        this.location = location;
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
    public String getLocation() { return location; }
    public List<Animal> getAllAnimals() { return animals; }

    
public long getCountByStatus(String status) {
    return animals.stream()
            .filter(a -> a.getStatus().equalsIgnoreCase(status))
            .count();
}

}