package src.View;

import src.Entity.AppointmentOutcomeRecord;
import src.Entity.PrescribedMedication;
import src.Interface.IDisplayableView;

/**
 * The AppointmentOutcomeRecordView class implements the IDisplayableView interface for displaying
 * the details of an Appointment Outcome Record, including information about the appointment,
 * the type of service provided, consultation notes, and prescribed medications.
 */
public class AppointmentOutcomeRecordView implements IDisplayableView<AppointmentOutcomeRecord> {

    /**
     * Displays the details of the given AppointmentOutcomeRecord.
     * If the record is null, a message indicating that no appointment outcome record is available will be displayed.
     * Otherwise, the following details will be printed:
     * - Date of the appointment
     * - Type of service provided
     * - Consultation notes
     * - A list of prescribed medications
     *
     * @param a the AppointmentOutcomeRecord to be displayed
     */
    @Override
    public void display(AppointmentOutcomeRecord a) {
        if(a == null)
            {
                System.out.println();
                System.out.println("No Appointment Outcome Record ");
            }
            else {
                
                System.out.println();
                System.out.println("Date of appointment       : " + a.getappointment().getDate());
                System.out.println("Type of Service Provided  : " + a.getTypeOfService());
                System.out.println("Consultation Notes        : " + a.getConsultationNotes());
                System.out.println("Prescribed Medications: ");
                for (PrescribedMedication j : a.getPrescribedMedications()) {
                    System.out.println("- " + j.getMedicineName());
                }
            }
    }
}
