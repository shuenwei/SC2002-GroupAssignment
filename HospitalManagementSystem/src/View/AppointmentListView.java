package View;

import Entity.Appointment;
import Interface.IListAppointments;
import java.util.ArrayList;


public class AppointmentListView implements IListAppointments{


    public void display(ArrayList<Appointment> apptList){
        int index = 1;
        for(Appointment a : apptList){
            System.out.println("("+ index +")");
            System.out.println();
            System.out.println("Patient Name : " + a.getPatient().getName());
            System.out.println("Doctor Name  : " + a.getDoctor().getName());
            System.out.println("Date         : " + a.getDate());
            System.out.println("Time         : " + a.getTime());
            System.out.println("Status       : " + a.getStatus());
            index++;
        }
    }


}
