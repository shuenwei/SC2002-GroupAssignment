package Entity;

import java.util.HashMap;
import java.util.ArrayList;

public class Inventory {
    private static HashMap<String, Medication> medicineRepo = new HashMap<String, Medication>();
    private static HashMap<String, Request> requestRepo = new HashMap<String, Request>();

    public static void add(Medication medicine) {
        medicineRepo.put(medicine.getMedicineName(), medicine);
    }
    public static Medication get(String medicineName) {
        return medicineRepo.get(medicineName);
    }
    public static void remove(String medicineName) {
        if (medicineRepo.get(medicineName) != null) {
            medicineRepo.remove(medicineName);
        }
    }
    public static ArrayList<Medication> getAllMedicine() {
        ArrayList<Medication> medicines = new ArrayList<Medication>();
            for (Medication medicine : medicineRepo.values()) {
                medicines.add(medicine);
            }
        return medicines;
    }
}