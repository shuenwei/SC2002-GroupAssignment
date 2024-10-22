package Controller;

import Entity.*;
import java.util.ArrayList;


public class DoctorController {

    private Doctor doctor;

    public DoctorController(Doctor doctor){
        this.doctor = doctor;
    }

    public Patient findPatientByID(String patientID){
        for(Appointment appointment : doctor.getAppointments()){
            if(appointment.getPatient().getHospitalId().equals(patientID)){
                return appointment.getPatient();
            }
        }
        return null;
    }

    public MedicalRecord findMedicalRecordByID(String patientID) {
        Patient patient = findPatientByID(patientID);
        if (patient != null) {
            return patient.getMedicalRecord();
        }
        return null;
    }

    public Diagnoses findDiagnosis(String diagnosis,String patientID) {
        for(Diagnoses diagnoses : findMedicalRecordByID(patientID).getDiagnoses()){
            if(diagnoses.getDiagnosisName().equals(diagnosis)){
                return diagnoses;
            }
        }
        return null;
    }

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
