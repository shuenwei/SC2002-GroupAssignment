package View;

import Entity.Staff;
import Interface.IDisplayableView;

public class StaffView implements IDisplayableView<Staff> {

    public void display(Staff staff){
        
        String[] headers = new String[]{"Hospital ID", "Password", "Role", "Name", "Gender", "Age"};
        System.out.println();
        System.out.printf("| %-13s | %-10s | %-15s | %-18s | %-8s | %-5s |%n", headers[0], headers[1], headers[2], headers[3], headers[4], headers[5]);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-13s | %-10s | %-15s | %-18s | %-8s | %-5d |%n", staff.getHospitalId(), staff.getPassword(), staff.getRole(), staff.getName(), staff.getGender(), staff.getAge());
               
    }

}
