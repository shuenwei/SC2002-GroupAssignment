package Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class AppointmentOutcomeRecord {
    private Appointment appointment;
    private ArrayList<PrescribedMedication> prescribedMedications;
    private String TypeOfService;
    private String ConsulationNotes;

    public AppointmentOutcomeRecord(Appointment appointment) {
        this.appointment = appointment;
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

    public void setConsulationNotes(String consulationNotes){
        this.ConsulationNotes = consulationNotes;
    }

    public String getConsulationNotes(){
        return ConsulationNotes;
    }

    public ArrayList<PrescribedMedication> getPrescribedMedications(){
        return prescribedMedications;
    }

    public void addPrescribedMedication(PrescribedMedication prescribedMedication){
        prescribedMedications.add(prescribedMedication);
    }


}