package com.farm.animal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ReportGenerator {
   public void generateTextReport(Farm farm, String filepath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            
            writer.println("=================================================");
            writer.println("           LIVESTOCK SUMMARY REPORT");
            writer.println("=================================================");
            writer.println("Farm Name:      " + farm.getFarmName());
            writer.println("Owner:          " + farm.getOwnerName());
            writer.println("Location:       " + farm.getLocation());
            writer.println("Report Date:    " + now.format(formatter));
            writer.println("=================================================");
            writer.println();
            
            writer.println("SUMMARY OF ANIMAL COUNTS");
            writer.println("-------------------------------------------------");
            writer.println("Total Active Animals:   " + farm.getTotalActiveCount());
            writer.println();
            
            writer.println("Breakdown by Species:");
            Map<String, Long> speciesCount = farm.getActiveCountBySpecies();
            for (Map.Entry<String, Long> entry : speciesCount.entrySet()) {
                writer.printf("  %-15s : %d%n", entry.getKey(), entry.getValue());
            }
            writer.println();
            
            writer.println("INCIDENTS SUMMARY");
            writer.println("-------------------------------------------------");
            writer.printf("Animals Died:         %d%n", farm.getCountByStatus(AnimalStatus.DEAD));
            writer.printf("Animals Lost:         %d%n", farm.getCountByStatus(AnimalStatus.LOST));
            writer.printf("Animals Stolen:       %d%n", farm.getCountByStatus(AnimalStatus.STOLEN));
            writer.printf("Animals Sold:         %d%n", farm.getCountByStatus(AnimalStatus.SOLD));
            writer.println();
            
            writer.println("DETAILED LISTING");
            writer.println("-------------------------------------------------");
            printAnimalList(writer, "DEAD ANIMALS", farm.getAnimalsByStatus(AnimalStatus.DEAD));
            printAnimalList(writer, "LOST ANIMALS", farm.getAnimalsByStatus(AnimalStatus.LOST));
            printAnimalList(writer, "STOLEN ANIMALS", farm.getAnimalsByStatus(AnimalStatus.STOLEN));
            
            writer.println("=================================================");
            writer.println("End of Report - This document can be submitted to");
            writer.println("veterinary services or financial institutions.");
            writer.println("=================================================");
            
            System.out.println("Report generated successfully: " + filepath);
            
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
    
    private void printAnimalList(PrintWriter writer, String title, java.util.List<Animal> animals) {
        if (animals.isEmpty()) return;
        
        writer.println(title + ":");
        for (Animal a : animals) {
            writer.printf("  Tag: %-10s Breed: %-15s Type: %s%n", 
                a.getTagNumber(), a.getBreed(), a.getClass().getSimpleName());
        }
        writer.println();
    } 
}
