package src.Entity;


import src.UI.PatientUI;
import java.util.*;

/**
 * The Patient class represents a patient in the system, inheriting from the User class.
 * It includes functionality for managing medical records, appointments, and displaying the patient's menu.
 */
public class Patient extends User {

    // List of appointments for the patient
	private ArrayList<Appointment> appointments;

    // The patient's medical record
	private MedicalRecord medicalRecord;

    /**
     * Constructs a Patient object with the specified details and creates a new medical record.
     *
     * @param hospitalId   The hospital ID of the patient.
     * @param password     The password of the patient.
     * @param name         The name of the patient.
     * @param gender       The gender of the patient.
     * @param dateOfBirth  The date of birth of the patient.
     * @param bloodType    The blood type of the patient.
     * @param emailAddress The email address of the patient.
     * @param phoneNumber  The phone number of the patient.
     */
    public Patient(String hospitalId,String password, String name, String gender, String dateOfBirth, String bloodType,String emailAddress, String phoneNumber) {
        super(hospitalId,password,name,gender);
        this.medicalRecord = new MedicalRecord(hospitalId, name, gender, dateOfBirth, bloodType, phoneNumber, emailAddress);
        appointments = new ArrayList<>();
    }

    /**
     * Displays the menu for the patient in the system using the PatientUI class.
     *
     * @param currentUser The current user, which is assumed to be of type Patient.
     */
    public void displayMenu(User currentUser) {
        PatientUI patientUi = new PatientUI((Patient) currentUser);
        patientUi.displayMenu();
    }

    /**
     * Retrieves the medical record of the patient.
     *
     * @return The medical record of the patient.
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    /**
     * Retrieves the list of appointments for the patient.
     *
     * @return The list of appointments.
     */
    public ArrayList<Appointment> getAppointments(){
        return appointments;
    }

    /**
     * Adds a new appointment to the patient's list of appointments.
     *
     * @param appointment The appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Removes an existing appointment from the patient's list of appointments.
     *
     * @param appointment The appointment to be removed.
     */
    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
    }

}