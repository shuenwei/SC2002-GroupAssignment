package src.Controller;

import src.Entity.Appointment;
import src.Enums.*;
import src.Entity.Patient;
import java.util.ArrayList;

/**
 * The {@code PatientController} class manages operations related to a specific patient.
 * It provides functionality to filter and retrieve the patient's appointments by status.
 */
public class PatientController extends UserController {
    private Patient patient;

    /**
     * Constructs a {@code PatientController} for the specified patient.
     *
     * @param patient the patient to be managed by this controller
     */
    public PatientController(Patient patient) {
        this.patient = patient;
    }

    /**
     * Retrieves a list of appointments for the patient that match the specified status.
     *
     * @param status the status to filter appointments by
     * @return a list of {@code Appointment} objects that have the specified status
     */
    public ArrayList<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();
        ArrayList<Appointment> appointments = patient.getAppointments();

        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == status) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }
}
