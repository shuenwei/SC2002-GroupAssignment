package View;

import Entity.Appointment;
import Interface.IAppointments;

public class AppointmentView implements IAppointments{


    public void display(Appointment a){
        System.out.println("Patient Name : " + a.getPatient().getName());
        System.out.println("Doctor Name  : " + a.getDoctor().getName());
        System.out.println("Date         : " + a.getDate());
        System.out.println("Time         : " + a.getTime());
        System.out.println("Status       : " + a.getStatus());
    }

}
