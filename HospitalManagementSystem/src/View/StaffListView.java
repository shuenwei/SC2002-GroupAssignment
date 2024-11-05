package View;

import Entity.Staff;
import Interface.IListDisplayableView;
import java.util.ArrayList;

public class StaffListView implements IListDisplayableView<Staff> {

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
