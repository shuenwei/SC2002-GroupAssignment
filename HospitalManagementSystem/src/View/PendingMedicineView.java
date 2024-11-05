package View;

import Entity.Appointment;
import Entity.PrescribedMedication;
import java.util.ArrayList;


public class PendingMedicineView {


    public void display(ArrayList<Appointment> appt){
    
        if (appt.isEmpty()) {
            System.out.println(" No appointments.");
        } else {
            int count = 1; 
            for (Appointment a : appt) {
                System.out.println();
                System.out.println("("+ count +")");
                System.out.println("Date of appointment       : " + a.getAppointmentOutcomeRecord().getAppointmentDate());
                System.out.println("Type of Service Provided  : " + a.getAppointmentOutcomeRecord().getTypeOfService());
                System.out.println("Consultation Notes        : " + a.getAppointmentOutcomeRecord().getConsultationNotes());
                for( PrescribedMedication j:a.getAppointmentOutcomeRecord().getPrescribedMedications()){
                    System.out.println("Prescribed medication : " + j.getMedicineName() + " " + j.getStatus());
                }
                count++;
                System.out.println();
                }
            }
        
    }
}