package View;

import Entity.Appointment;
import Interface.IAppointments;
import java.time.format.DateTimeFormatter;

public class AppointmentView implements IAppointments{


    public void display(Appointment a){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Patient Name : " + a.getPatient().getName());
        System.out.println("Doctor Name  : " + a.getDoctor().getName());
        System.out.println("Date         : " + a.getDate().format(formatter));
        System.out.println("Time         : " + a.getTime());
        System.out.println("Status       : " + a.getStatus());
    }

}
