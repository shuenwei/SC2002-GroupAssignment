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
    public static void viewInventory() {
        ArrayList<Medication> medicines = Inventory.getAllMedicine();
        String[] headers = new String[]{"Medicine Name", "Stock", "AlertLevel", "LowStock"};
        System.out.println();
        System.out.printf("| %-15s | %-7s | %-12s | %-7s |%n", headers[0], headers[1], headers[2], headers[3]);
        System.out.println("-------------------------------------------------------");
        for (Medication medicine : medicines) {
            if (medicine != null) {
                System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), medicine.getIsLowStock());
            }
        }
    }
}