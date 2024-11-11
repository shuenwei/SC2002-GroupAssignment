package View;

import Entity.Medication;
import Interface.IListDisplayableView;
import Repository.InventoryRepository;
import java.util.ArrayList;

/**
 * The ViewListInventory class implements IListDisplayableView to display a list of medications
 * in the inventory, including stock levels, alert levels, and low stock status.
 */
public class ViewListInventory implements IListDisplayableView<Medication> {

    /**
     * Displays a list of all medications in the inventory, showing each medication's name, stock level,
     * alert level (stock threshold), and whether it is low on stock.
     *
     * @param medicines An ArrayList of Medication objects to display. If null or empty, no information is shown.
     */
    public void display(ArrayList<Medication> medicines) {
        medicines = InventoryRepository.getAllMedicines();
        String[] headers = new String[]{"Medicine Name", "Stock", "AlertLevel", "LowStock"};
        System.out.println();
        System.out.printf("| %-15s | %-7s | %-12s | %-7s |%n", headers[0], headers[1], headers[2], headers[3]);
        System.out.println("-------------------------------------------------------");
        
        for (Medication medicine : medicines) {
            if (medicine != null) {
                if (medicine.getIsLowStock()) {
                    System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", 
                                      medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), "YES");
                } else {
                    System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", 
                                      medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), "NO");
                }
            }
        }
    }
}
