package View;

import Entity.AppointmentOutcomeRecord;
import Entity.PrescribedMedication;
import Interface.IDisplayableView;

public class AppointmentOutcomeRecordView implements IDisplayableView<AppointmentOutcomeRecord> {

    public void display(AppointmentOutcomeRecord a) {
        if(a == null)
            {
                System.out.println();
                System.out.println("Your appointment is still pending and has not been accepted by the doctor");
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
