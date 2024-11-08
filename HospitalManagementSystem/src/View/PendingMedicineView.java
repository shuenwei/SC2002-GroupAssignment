package View;

import Entity.Appointment;
import Entity.PrescribedMedication;
import java.util.ArrayList;

/**
 * The PendingMedicineView class is responsible for displaying appointments 
 * that have prescribed medications and their corresponding statuses.
 * This class provides a view for appointments with pending medicine details.
 */
public class PendingMedicineView {

    /**
     * Displays the details of appointments with pending medications.
     * It prints the appointment date, type of service, consultation notes,
     * and the prescribed medications along with their status for each appointment.
     * 
     * @param appt A list of Appointment objects that contain information 
     *             about the appointment outcome record, including prescribed medications.
     */
    public void display(ArrayList<Appointment> appt){
    
        if (appt.isEmpty()) {
            System.out.println("No appointment outcome record(s).");
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