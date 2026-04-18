import java.util.ArrayList;

public class HealthManager {

    private ArrayList<HealthRecord> healthRecords;

    // Constructor
    public HealthManager() {
        this.healthRecords = new ArrayList<>();
    }

    public void addRecord(HealthRecord record) {
        for (HealthRecord r : healthRecords) {
            if (r.getRecordId() == record.getRecordId()) {
                System.out.println("Error: Record with ID '" + record.getRecordId() + "' already exists.");
                return;
            }
        }
        healthRecords.add(record);
        System.out.println("Health record added for animal: " + record.getAnimalTagNumber());
    }

    public void removeRecord(int recordId) {
        HealthRecord found = findById(recordId);
        if (found != null) {
            healthRecords.remove(found);
            System.out.println("Record #" + recordId + " removed.");
        } else {
            System.out.println("Error: Record ID '" + recordId + "' not found.");
        }
    }

    public HealthRecord findById(int recordId) {
        for (HealthRecord r : healthRecords) {
            if (r.getRecordId() == recordId) {
                return r;
            }
        }
        return null;
    }


    public ArrayList<HealthRecord> findByAnimalTag(String animalTagNumber) {
        ArrayList<HealthRecord> results = new ArrayList<>();
        for (HealthRecord r : healthRecords) {
            if (r.getAnimalTagNumber().equalsIgnoreCase(animalTagNumber)) {
                results.add(r);
            }
        }
        return results;
    }


    public ArrayList<HealthRecord> findByStaff(String staffMember) {
        ArrayList<HealthRecord> results = new ArrayList<>();
        for (HealthRecord r : healthRecords) {
            if (r.getStaffMember().equalsIgnoreCase(staffMember)) {
                results.add(r);
            }
        }
        return results;
    }


    public ArrayList<HealthRecord> findByDiagnosis(String keyword) {
        ArrayList<HealthRecord> results = new ArrayList<>();
        for (HealthRecord r : healthRecords) {
            if (r.getDiagnosis().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(r);
            }
        }
        return results;
    }


    public ArrayList<HealthRecord> findByDate(String date) {
        ArrayList<HealthRecord> results = new ArrayList<>();
        for (HealthRecord r : healthRecords) {
            if (r.getTreatmentDate().equalsIgnoreCase(date)) {
                results.add(r);
            }
        }
        return results;
    }

    public void updateRecord(int recordId, String diagnosis, String medication,
                             double dosage, String staffMember, String notes) {
        HealthRecord record = findById(recordId);
        if (record != null) {
            record.setDiagnosis(diagnosis);
            record.setMedication(medication);
            record.setMedicationDosage(dosage);
            record.setStaffMember(staffMember);
            record.setNotes(notes);
            System.out.println("Record #" + recordId + " updated successfully.");
        } else {
            System.out.println("Error: Record ID '" + recordId + "' not found.");
        }
    }

    // ─────────────────────────────────────────
    // DISPLAY all health records
    // ─────────────────────────────────────────
    public void displayAll() {
        if (healthRecords.isEmpty()) {
            System.out.println("No health records found.");
            return;
        }
        System.out.println("\n===== HEALTH RECORDS =====");
        for (HealthRecord r : healthRecords) {
            System.out.println(r.toString());
        }
        System.out.println("==========================\n");
    }

    // ─────────────────────────────────────────
    // DISPLAY all records for one animal
    // ─────────────────────────────────────────
    public void displayByAnimal(String animalTagNumber) {
        ArrayList<HealthRecord> records = findByAnimalTag(animalTagNumber);
        if (records.isEmpty()) {
            System.out.println("No health records found for animal: " + animalTagNumber);
            return;
        }
        System.out.println("\n===== RECORDS FOR ANIMAL: " + animalTagNumber + " =====");
        for (HealthRecord r : records) {
            System.out.println(r.toString());
        }
        System.out.println("===========================================\n");
    }

    public ArrayList<HealthRecord> getHealthRecords() {
        return healthRecords;
    }


    public static void main(String[] args) {
        HealthManager manager = new HealthManager();


        HealthRecord r1 = new HealthRecord(1, "C-001", "2026-04-10",
                "Foot rot", "Penicillin", 5.0, "John Shilongo", "Isolate for 3 days");
        HealthRecord r2 = new HealthRecord(2, "S-003", "2026-04-12",
                "Pneumonia", "Oxytetracycline", 10.0, "Anna Hamutenya", "Monitor breathing");
        HealthRecord r3 = new HealthRecord(3, "C-001", "2026-04-15",
                "Follow-up check", "None", 0.0, "John Shilongo", "Recovering well");

        manager.addRecord(r1);
        manager.addRecord(r2);
        manager.addRecord(r3);

        manager.displayAll();

        System.out.println("--- Records for C-001 ---");
        manager.displayByAnimal("C-001");

        System.out.println("--- Records by John Shilongo ---");
        for (HealthRecord r : manager.findByStaff("John Shilongo")) {
            System.out.println(r.toString());
        }

        manager.updateRecord(2, "Severe Pneumonia", "Oxytetracycline", 15.0, "Anna Hamutenya", "Worsened - vet called");
        manager.displayAll();
    }
}
