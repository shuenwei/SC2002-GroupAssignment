package Controller;

import Entity.Appointment;
import Entity.Doctor;
import Entity.Patient;
import Enums.AppointmentStatus;
import Repository.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentController {
    public void newAppointment(Doctor doctor, Patient patient, LocalDate date, LocalTime time){
        Appointment appointment = new Appointment(doctor, patient, date, time, AppointmentStatus.PENDING, null);
        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);
        AppointmentRepository.add(appointment);
    }
}
