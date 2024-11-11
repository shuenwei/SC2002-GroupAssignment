package View;

import Entity.Medication;
import Interface.IDisplayableView;

/**
 * The ViewInventory class implements IDisplayableView to display detailed information about a specific medication,
 * including stock levels and low stock status.
 */
public class ViewInventory implements IDisplayableView<Medication> {
    
    /**
     * Displays the details of a medication, including its name, stock level, alert level, and
     * whether it is low on stock.
     *
     * @param medicine The Medication object to display. If null, no information is shown.
     */
    public void display(Medication medicine) {
        String[] headers = new String[]{"Medicine Name", "Stock", "AlertLevel", "LowStock"};
        System.out.println();
        System.out.printf("| %-15s | %-7s | %-12s | %-7s |%n", headers[0], headers[1], headers[2], headers[3]);
        System.out.println("-------------------------------------------------------");
        
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
