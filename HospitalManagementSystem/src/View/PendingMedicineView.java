package View;

import Entity.Appointment;
import Enums.AppointmentStatus;
import java.util.ArrayList;


public class PendingMedicineView {


    public void display(ArrayList<Appointment> appt){
    
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
