package Controller;

import Entity.Pharmacist;
import Entity.Inventory;
import Entity.Request;
import Entity.Medication;

public class PharmacistController {
    private Pharmacist pharmacist;

    public PharmacistController(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
    }

    public void submitRequest(String requestMedicine) {
        Medication medicine = Inventory.get(requestMedicine);
        if (medicine != null) {
            if (medicine.getIsLowStock() != "YES") {
                System.out.printf("%s is not low on stock%n", requestMedicine);
            }
            else {
                Request newRequest = new Request(requestMedicine);
                Inventory.addRequest(newRequest);
                System.out.printf("Successfully submitted replenishment request for %s%n", requestMedicine);
            }
        }
        else {
            System.out.println("Medicine does not exist");
        }
    }
}
