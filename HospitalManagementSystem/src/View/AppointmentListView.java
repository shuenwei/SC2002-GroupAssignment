package View;

import Entity.Appointment;
import Interface.IListAppointments;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class AppointmentListView implements IListAppointments{

    public void display(ArrayList<Appointment> apptList){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int index = 1;
        for(Appointment a : apptList){
            System.out.println();
            System.out.println("("+ index +")");
            System.out.println("Patient Name : " + a.getPatient().getName());
            System.out.println("Doctor Name  : " + a.getDoctor().getName());
            System.out.println("Date         : " + a.getDate().format(formatter));
            System.out.println("Time         : " + a.getTime());
            System.out.println("Status       : " + a.getStatus());
            index++;
        }
    }


}
