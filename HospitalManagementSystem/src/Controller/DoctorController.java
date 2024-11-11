package Controller;

import Entity.*;
import java.util.ArrayList;

/**
 * The DoctorController class provides methods for a doctor to manage patients, appointments,
 * and medical records, including functions to find patients, access medical histories,
 * and filter appointments by status.
 */
public class DoctorController extends StaffController {

    private Doctor doctor;

    /**
     * Constructs a DoctorController for a specific doctor.
     *
     * @param doctor The doctor associated with this controller.
     */
    public DoctorController(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Finds a patient by their hospital ID from the doctor's list of appointments.
     *
     * @param patientID The hospital ID of the patient to find.
     * @return The Patient associated with the given ID, or null if not found.
     */
    public Patient findPatientByID(String patientID) {
        for (Appointment appointment : doctor.getAppointments()) {
            if (appointment.getPatient().getHospitalId().equals(patientID)) {
                return appointment.getPatient();
            }
        }
        return null;
    }

    /**
     * Retrieves the medical record of a patient by their hospital ID.
     *
     * @param patientID The hospital ID of the patient whose medical record is requested.
     * @return The MedicalRecord associated with the given patient ID, or null if not found.
     */
    public MedicalRecord findMedicalRecordByID(String patientID) {
        Patient patient = findPatientByID(patientID);
        if (patient != null) {
            return patient.getMedicalRecord();
        }
        return null;
    }

    /**
     * Finds a specific medical history entry by diagnosis within a patient's medical record.
     *
     * @param diagnosis The diagnosis to look for in the medical history.
     * @param patientID The hospital ID of the patient whose medical history is requested.
     * @return The MedicalHistory entry with the specified diagnosis, or null if not found.
     */
    public MedicalHistory findmedicalHistory(String diagnosis, String patientID) {
        for (MedicalHistory medicalHistory : findMedicalRecordByID(patientID).getMedicalHistory()) {
            if (medicalHistory.getDiagnosisandType().equals(diagnosis)) {
                return medicalHistory;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of the doctor's appointments filtered by a specified status.
     *
     * @param status The status of the appointments to filter by.
     * @return A list of Appointments with the specified status.
     */
    public ArrayList<Appointment> getAppointmentsByStatus(Enums.AppointmentStatus status) {
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();
        ArrayList<Appointment> appointments = doctor.getAppointments();
        
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == status) {
                filteredAppointments.add(appointment);
            }
        }
        
        return filteredAppointments;
    }
}
