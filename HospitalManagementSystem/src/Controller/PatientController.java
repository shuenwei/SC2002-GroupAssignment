package Controller;

import Entity.Appointment;
import Entity.Patient;
import java.util.ArrayList;

public class PatientController extends UserController {
    private Patient patient;

    public PatientController(Patient patient){
        this.patient = patient;
    }

    public ArrayList<Appointment> getAppointmentsByStatus(Enums.AppointmentStatus status) {
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
