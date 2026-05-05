package com.farm.animal;

public enum AnimalStatus {
    ACTIVE, HEALTHY, SICK, SOLD, DEAD, LOST, STOLEN;

    public static AnimalStatus fromString(String status) {
        if (status == null) return ACTIVE;
        try {
            return AnimalStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ACTIVE; // default if unknown
        }
    }
    
}
