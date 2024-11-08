package View;

import Entity.Inventory;
import java.util.ArrayList;

import Entity.Medication;
import Interface.IListDisplayableView;

public class ViewListInventory implements IListDisplayableView<Medication> {

    public void display(ArrayList<Medication> medicines) {
        medicines = Inventory.getAllMedicines();
        String[] headers = new String[]{"Medicine Name", "Stock", "AlertLevel", "LowStock"};
        System.out.println();
        System.out.printf("| %-15s | %-7s | %-12s | %-7s |%n", headers[0], headers[1], headers[2], headers[3]);
        System.out.println("-------------------------------------------------------");
        for (Medication medicine : medicines) {
            if (medicine != null) {
                if(medicine.getIsLowStock()){
                    System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), "YES");
                }
                else{
                    System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), "NO");
                }
            }
        }
    }
}
