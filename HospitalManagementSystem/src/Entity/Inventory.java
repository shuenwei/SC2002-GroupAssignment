package Entity;

import java.util.ArrayList;
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

    public static ArrayList<Medication> getAllMedication() {
        ArrayList<Medication> medication = new ArrayList<>();
            for (Medication m : medicineRepo.values()) {
                if (m instanceof Medication) {
                    medication.add((Medication) m);
                }
            }
        return medication;
    }


}