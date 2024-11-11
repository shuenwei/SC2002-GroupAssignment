package src.Controller;

import src.Entity.Appointment;
import src.Entity.AppointmentOutcomeRecord;
import src.Entity.PrescribedMedication;
import src.Repository.InventoryRepository;

import java.util.ArrayList;

/**
 * The {@code AppointmentOutcomeController} class manages the creation and handling
 * of appointment outcome records. It provides functionality to check for medication
 * availability and to create and set outcome records for appointments.
 */
public class AppointmentOutcomeController {
    private AppointmentOutcomeRecord appointmentOutcomeRecord;

    /**
     * Constructs an {@code AppointmentOutcomeController} with the specified
     * {@code AppointmentOutcomeRecord}.
     *
     * @param appointmentOutcomeRecord the appointment outcome record to be managed
     */
    public AppointmentOutcomeController(AppointmentOutcomeRecord appointmentOutcomeRecord) {
        this.appointmentOutcomeRecord = appointmentOutcomeRecord;
    }

    /**
     * Checks if a medication exists in the inventory based on the given medication name.
     *
     * @param medicationName the name of the medication to check for availability
     * @return {@code true} if the medication exists in the inventory, {@code false} otherwise
     */
    public static boolean checkMedicationExist(String medicationName) {
        if(InventoryRepository.get(medicationName) == null) {
            return false;
        }
        return true;
    }

    /**
     * Creates a new {@code AppointmentOutcomeRecord} with the specified details.
     *
     * @param serviceType           the type of service administered during the appointment
     * @param consultationNotes     notes taken during the consultation
     * @param prescribedMedications a list of prescribed medications for the patient
     * @param appointment           the appointment for which the outcome record is created
     * @return a new {@code AppointmentOutcomeRecord} with the provided details
     */
    public static AppointmentOutcomeRecord createAppointmentOutcomeRecord(String serviceType,
                                                                          String consultationNotes,
                                                                          ArrayList<PrescribedMedication> prescribedMedications,
                                                                          Appointment appointment) {
        return new AppointmentOutcomeRecord(serviceType, consultationNotes, prescribedMedications, appointment);
    }

    /**
     * Sets the {@code AppointmentOutcomeRecord} for the specified appointment.
     *
     * @param appointment the appointment to set the outcome record for
     */
    public void setAppointmentOutcomeRecord(Appointment appointment) {
        appointment.setAppointmentOutcomeRecord(appointmentOutcomeRecord);
    }
}
