package View;

import Entity.AppointmentOutcomeRecord;
import Entity.PrescribedMedication;
import Interface.IAppointmentOutcomeRecord;
import java.util.ArrayList;

public class AppointmentOutcomeRecordView implements IAppointmentOutcomeRecord {

    public void display(ArrayList<AppointmentOutcomeRecord> a) {

        int index = 1;
        for(AppointmentOutcomeRecord i : a){
            if(i == null)
            {
                System.out.println("Your appointment is still pending and has not been accepted by the doctor");
            }
            else {
                System.out.println();
                System.out.println("(" + index + ")");
                System.out.println("Date of appointment       : " + i.getappointment().getDate());
                System.out.println("Type of Service Provided  : " + i.getTypeOfService());
                System.out.println("Consultation Notes        : " + i.getConsultationNotes());
                for (PrescribedMedication j : i.getPrescribedMedications()) {
                    System.out.println("Prescribed medication : " + j.getMedicineName());
                }
            }

            index++;
        }
    }
}
