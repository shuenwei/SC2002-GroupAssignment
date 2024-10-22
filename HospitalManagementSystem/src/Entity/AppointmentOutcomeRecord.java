package Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class AppointmentOutcomeRecord {
    private Appointment appointment;
    private ArrayList<PrescribedMedication> prescribedMedications;
    private String TypeOfService;
    private String ConsultationNotes;

    public AppointmentOutcomeRecord(String typeOfService, String consulationNotes, ArrayList<PrescribedMedication> prescribedMedications,Appointment appointment    ) {
        this.appointment = appointment;
        this.TypeOfService = typeOfService;
        this.ConsultationNotes = consulationNotes;
        this.prescribedMedications = prescribedMedications;
    }

    public Appointment getappointment() {
        return appointment;
    }

    public LocalDate getAppointmentDate(){
        return appointment.getDate();
    }

    public void setTypeOfService(String typeOfService){
        this.TypeOfService = typeOfService;
    }

    public String getTypeOfService(){
        return TypeOfService;
    }

    public void setConsultationNotes(String consultationNotes){
        this.ConsultationNotes = consultationNotes;
    }

    public String getConsultationNotes(){
        return ConsultationNotes;
    }
    
    public ArrayList<PrescribedMedication> getPrescribedMedications(){
        return prescribedMedications;
    }

    public void addPrescribedMedication(PrescribedMedication prescribedMedication){
        prescribedMedications.add(prescribedMedication);
    }

}