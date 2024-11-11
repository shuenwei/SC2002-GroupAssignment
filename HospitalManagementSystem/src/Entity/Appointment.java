package src.Entity;

import src.Enums.AppointmentStatus;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Appointment class represents an appointment between a doctor and a patient.
 * It contains details such as the doctor, patient, date, time, status, and any associated outcome records.
 */
public class Appointment {

    /**
     * The doctor associated with the appointment.
     */
    private Doctor doctor;

    /**
     * The patient associated with the appointment.
     */
    private Patient patient;

    /**
     * The date of the appointment.
     */
    private LocalDate date;

    /**
     * The time of the appointment.
     */
    private LocalTime time;

    /**
     * The status of the appointment (e.g., PENDING, CONFIRMED).
     */
    private AppointmentStatus status;

    /**
     * The appointment outcome record, which contains details such as consultation notes and prescribed medications.
     */
    private AppointmentOutcomeRecord appointmentOutcomeRecord;

    /**
     * Constructs an Appointment object with the provided details.
     *
     * @param doctor                The doctor associated with the appointment.
     * @param patient               The patient associated with the appointment.
     * @param date                  The date of the appointment.
     * @param time                  The time of the appointment.
     * @param status                The status of the appointment (e.g., PENDING, CONFIRMED).
     * @param appointmentOutcomeRecord The outcome record of the appointment, including details such as consultation notes and prescribed medications.
     */
    public Appointment(Doctor doctor,Patient patient, LocalDate date, LocalTime time, AppointmentStatus status, AppointmentOutcomeRecord appointmentOutcomeRecord) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.status = status;
        this.appointmentOutcomeRecord = appointmentOutcomeRecord;
    }

    /**
     * Returns the doctor associated with the appointment.
     *
     * @return The doctor for this appointment.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor associated with the appointment.
     *
     * @param doctor The doctor to set for the appointment.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the patient associated with the appointment.
     *
     * @return The patient for this appointment.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient associated with the appointment.
     *
     * @param patient The patient to set for the appointment.
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date for the appointment.
     *
     * @param date The date to set for the appointment.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the time of the appointment.
     *
     * @return The time of the appointment.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time for the appointment.
     *
     * @param time The time to set for the appointment.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns the status of the appointment.
     *
     * @return The status of the appointment (e.g., PENDING, CONFIRMED).
     */
    public AppointmentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status The status to set for the appointment (e.g., PENDING, CONFIRMED).
     */
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    /**
     * Returns the appointment outcome record associated with the appointment.
     *
     * @return The outcome record for the appointment.
     */
    public AppointmentOutcomeRecord getAppointmentOutcomeRecord() {
        return appointmentOutcomeRecord;
    }

    /**
     * Sets the appointment outcome record for the appointment.
     *
     * @param appointmentOutcomeRecord The outcome record to set for the appointment.
     */
    public void setAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
        this.appointmentOutcomeRecord = appointmentOutcomeRecord;
    }


}
