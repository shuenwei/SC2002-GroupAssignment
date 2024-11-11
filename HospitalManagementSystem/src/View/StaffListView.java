package src.View;

import src.Entity.Staff;
import src.Interface.IListDisplayableView;
import java.util.ArrayList;

/**
 * The StaffListView class is responsible for displaying a list of staff members.
 * It presents details such as hospital ID, role, name, gender, and age in a formatted table.
 */
public class StaffListView implements IListDisplayableView<Staff> {

    /**
     * Displays a list of staff members in a formatted table.
     * The table includes columns for hospital ID, password, role, name, gender, and age.
     *
     * @param staffList A list of Staff objects to be displayed.
     *                  Each Staff object contains information like hospital ID, role, name, gender, and age.
     */
    public void display(ArrayList<Staff> staffList) {
        String[] headers = new String[]{"Hospital ID", "Password", "Role", "Name", "Gender", "Age"};
        System.out.println();
        System.out.printf("| %-13s | %-10s | %-15s | %-18s | %-8s | %-5s |%n", headers[0], headers[1], headers[2], headers[3], headers[4], headers[5]);
        System.out.println("----------------------------------------------------------------------------------------");

        for (Staff staff : staffList) {
            System.out.printf("| %-13s | %-10s | %-15s | %-18s | %-8s | %-5d |%n", staff.getHospitalId(), staff.getPassword(), staff.getRole(), staff.getName(), staff.getGender(), staff.getAge());
        }

    }

}
