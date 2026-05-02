package com.farm.animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
    public class HealthRecord {

        private int recordId;
        private String animalTagNumber;
        private String treatmentDate;
        private String diagnosis;
        private String medication;
        private double medicationDosage;
        private String staffMember;
        private String notes;

        public HealthRecord(int recordId, String animalTagNumber, String treatmentDate,
                            String diagnosis, String medication, double medicationDosage,
                            String staffMember, String notes) {
            this.recordId = recordId;
            this.animalTagNumber = animalTagNumber;
            this.treatmentDate = treatmentDate;
            this.diagnosis = diagnosis;
            this.medication = medication;
            this.medicationDosage = medicationDosage;
            this.staffMember = staffMember;
            this.notes = notes;
        }


        public int getRecordId()              {
            return recordId; 
        }
        public String getAnimalTagNumber()    { 
            return animalTagNumber; 
        }
        public String getTreatmentDate()      { 
            return treatmentDate;
        }
        public String getDiagnosis()          { 
            return diagnosis;
        }
        public String getMedication()         {
            return medication; 
        }
        public double getMedicationDosage()   {
            return medicationDosage; 
        }
        public String getStaffMember()        {
            return staffMember; 
        }
        public String getNotes()              {
            return notes;
        }


        public void setDiagnosis(String diagnosis)             {
            this.diagnosis = diagnosis; 
        }
        public void setMedication(String medication)           {
            this.medication = medication; 
        }
        public void setMedicationDosage(double dosage)         { 
            this.medicationDosage = dosage;
        }
        public void setStaffMember(String staffMember)         {
            this.staffMember = staffMember; 
        }
        public void setNotes(String notes)                     {
            this.notes = notes;
        }


        @Override
        public String toString() {
            return String.format(
                    "Record #%d | Animal: %s | Date: %s | Diagnosis: %s | Medication: %s (%.2f mg) | Staff: %s | Notes: %s",
                    recordId, animalTagNumber, treatmentDate, diagnosis,
                    medication, medicationDosage, staffMember, notes);
        }
        public void saveToDB() {
            Connection conn = null;
            try {
                conn = DBConnection.getConnection();
                String sql = "INSERT INTO HealthRecords (recordId, animalTagNumber, treatmentDate, diagnosis, medication, medicationDosage, staffMember, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, recordId);
                stmt.setString(2, animalTagNumber);
                stmt.setString(3, treatmentDate);
                stmt.setString(4, diagnosis);
                stmt.setString(5, medication);
                stmt.setDouble(6, medicationDosage);
                stmt.setString(7, staffMember);
                stmt.setString(8, notes);
                stmt.executeUpdate();
                System.out.println("Health record saved to DB!");
            } catch (SQLException e) {
                System.out.println("Error saving health record: " + e.getMessage());
            } finally {
                DBConnection.closeConnection(conn);
            }
        }
    }
