package Entity;

import Enums.AppointmentStatus;
import java.time.LocalDate;
import java.time.LocalTime;


public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private LocalTime time;
    private AppointmentStatus status;
    private AppointmentOutcomeRecord appointmentOutcomeRecord;

    public Appointment(Doctor doctor,Patient patient, LocalDate date, LocalTime time, AppointmentStatus status, AppointmentOutcomeRecord appointmentOutcomeRecord) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.status = status;
        this.appointmentOutcomeRecord = appointmentOutcomeRecord;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }


    public AppointmentOutcomeRecord getAppointmentOutcomeRecord() {
        return appointmentOutcomeRecord;
    }

    public void setAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
        this.appointmentOutcomeRecord = appointmentOutcomeRecord;
    }


}
