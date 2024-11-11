package src.Controller;

import src.Entity.Appointment;
import src.Entity.Doctor;
import src.Entity.Patient;
import src.Enums.AppointmentStatus;
import src.Repository.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The {@code AppointmentController} class manages the creation of new appointments
 * between doctors and patients. It handles the addition of appointments to the doctor’s
 * and patient’s schedules and saves the appointment to the repository.
 */
public class AppointmentController {

    /**
     * Creates a new appointment for the specified doctor and patient on a given date and time.
     * The appointment status is initially set to {@code PENDING} and is added to the schedules
     * of both the doctor and the patient, as well as the {@code AppointmentRepository}.
     *
     * @param doctor  the doctor for whom the appointment is scheduled
     * @param patient the patient for whom the appointment is scheduled
     * @param date    the date of the appointment
     * @param time    the time of the appointment
     */
    public void newAppointment(Doctor doctor, Patient patient, LocalDate date, LocalTime time) {
        Appointment appointment = new Appointment(doctor, patient, date, time, AppointmentStatus.PENDING, null);
        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);
        AppointmentRepository.add(appointment);
    }
}
