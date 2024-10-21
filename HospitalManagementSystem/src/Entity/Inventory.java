package Entity;

import java.util.HashMap;

public class Inventory {
    private static HashMap<String, Medication> medicineRepo = new HashMap<String, Medication>();

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
}