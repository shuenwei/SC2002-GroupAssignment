package View;

import Entity.AppointmentOutcomeRecord;
import Entity.PrescribedMedication;
import Interface.IDisplayableView;

public class AppointmentOutcomeRecordView implements IDisplayableView<AppointmentOutcomeRecord> {

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
                for (PrescribedMedication j : a.getPrescribedMedications()) {
                    System.out.println("Prescribed medication : " + j.getMedicineName());
                }
            }
    }
}
