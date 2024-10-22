package Controller;

import Entity.*;

public class AdministratorController {
    private Administrator administrator;

    public AdministratorController(Administrator administrator){
        this.administrator = administrator;
    }

    // StaffManagementController staffManagementController = new StaffManagementController

    public static void addStock(String medicineName, int quantity) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() + quantity;
            medicine.setStock(newStock);
    
            if (newStock > medicine.getStockThreshold() && medicine.getIsLowStock() == "YES") {
                medicine.setIsLowStock("NO");
            }

            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
        else {
            System.out.println("Medicine does not exist!");
        }
    }

    public static void removeStock(String medicineName, int quantity) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() - quantity;
            medicine.setStock(newStock);
    
            if (newStock <= medicine.getStockThreshold() && medicine.getIsLowStock() == "NO") {
                medicine.setIsLowStock("YES");
            }

            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
        else {
            System.out.println("Medicine does not exist!");
        }
    }

    public static void addNewMedicine(String medicineName, int stock, int lowStockThreshold) {
        Medication newMedicine = new Medication(medicineName, stock, lowStockThreshold);
        Inventory.add(newMedicine);
    }
}
