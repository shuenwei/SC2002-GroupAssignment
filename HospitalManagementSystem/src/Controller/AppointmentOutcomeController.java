package Controller;

import Entity.AppointmentOutcomeRecord;
import Entity.Inventory;
import Entity.PrescribedMedication;

import java.util.ArrayList;


public class AppointmentOutcomeController {
    private AppointmentOutcomeRecord appointmentOutcomeRecord;

    public AppointmentOutcomeController(AppointmentOutcomeRecord appointmentOutcomeRecord) {
        this.appointmentOutcomeRecord = appointmentOutcomeRecord;
    }

    public static boolean checkMedicationExist(String medicationName) {
        if(Inventory.get(medicationName) == null) {
            return false;
        }
        return true;
    }

    public static AppointmentOutcomeRecord createAppointmentOutcomeRecord(String serviceType, String consultationNotes, ArrayList<PrescribedMedication> prescribedMedications) {
        return new AppointmentOutcomeRecord(serviceType, consultationNotes, prescribedMedications);
    }
}
