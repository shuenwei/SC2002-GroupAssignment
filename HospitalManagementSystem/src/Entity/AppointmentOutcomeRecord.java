package src.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The AppointmentOutcomeRecord class stores the details of an appointment's outcome,
 * including the type of service provided, consultation notes, and any prescribed medications.
 */
public class AppointmentOutcomeRecord {

    /**
     * The appointment associated with this outcome record.
     */
    private Appointment appointment;

    /**
     * A list of prescribed medications related to the appointment.
     */
    private ArrayList<PrescribedMedication> prescribedMedications;

    /**
     * The type of service provided during the appointment (e.g., consultation, treatment).
     */
    private String TypeOfService;

    /**
     * Notes from the consultation that occurred during the appointment.
     */
    private String ConsultationNotes;

    /**
     * Constructs an AppointmentOutcomeRecord with the specified details.
     *
     * @param typeOfService        The type of service provided during the appointment.
     * @param consultationNotes    The consultation notes from the appointment.
     * @param prescribedMedications A list of prescribed medications during the appointment.
     * @param appointment          The appointment associated with this outcome record.
     */
    public AppointmentOutcomeRecord(String typeOfService, String consulationNotes, ArrayList<PrescribedMedication> prescribedMedications,Appointment appointment    ) {
        this.appointment = appointment;
        this.TypeOfService = typeOfService;
        this.ConsultationNotes = consulationNotes;
        this.prescribedMedications = prescribedMedications;
    }

    /**
     * Returns the appointment associated with this outcome record.
     *
     * @return The appointment for this outcome record.
     */ 
    public Appointment getappointment() {
        return appointment;
    }

    /**
     * Sets the appointment for this object.
     * 
     * @param appointment the {@link Appointment} object to be associated with this instance.
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Returns the date of the appointment associated with this outcome record.
     *
     * @return The date of the appointment.
     */
    public LocalDate getAppointmentDate(){
        return appointment.getDate();
    }

    /**
     * Sets the type of service provided during the appointment.
     *
     * @param typeOfService The type of service to set.
     */
    public void setTypeOfService(String typeOfService){
        this.TypeOfService = typeOfService;
    }

    /**
     * Returns the type of service provided during the appointment.
     *
     * @return The type of service.
     */
    public String getTypeOfService(){
        return TypeOfService;
    }

    /**
     * Sets the consultation notes from the appointment.
     *
     * @param consultationNotes The consultation notes to set.
     */
    public void setConsultationNotes(String consultationNotes){
        this.ConsultationNotes = consultationNotes;
    }

    /**
     * Returns the consultation notes from the appointment.
     *
     * @return The consultation notes.
     */
    public String getConsultationNotes(){
        return ConsultationNotes;
    }

    /**
     * Returns the list of prescribed medications from the appointment.
     *
     * @return The list of prescribed medications.
     */
    public ArrayList<PrescribedMedication> getPrescribedMedications(){
        return prescribedMedications;
    }

    /**
     * Adds a prescribed medication to the list of prescribed medications.
     *
     * @param prescribedMedication The prescribed medication to add.
     */
    public void addPrescribedMedication(PrescribedMedication prescribedMedication){
        prescribedMedications.add(prescribedMedication);
    }


}