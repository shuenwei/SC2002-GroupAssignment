package View;

import Entity.Appointment;
import Enums.AppointmentStatus;
import java.util.ArrayList;
import Controller.PharmacistController;

public class ViewPendingMedicine {


    public void displayPendingMenu(ArrayList<Appointment> appt){
    
        if (appt.isEmpty()) {
            System.out.println(" No appointments.");
        } else {
            int count = 1; 
            for (Appointment a : appt) {
                System.out.println(" " + count + ". " + a);
                count++;
                }
            }
        System.out.println();
    }
}
