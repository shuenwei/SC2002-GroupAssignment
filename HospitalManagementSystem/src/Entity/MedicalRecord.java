package src.Entity;

import java.util.ArrayList;

/**
 * The MedicalRecord class represents the medical record of a patient, including personal details and a list of medical history entries.
 */
public class MedicalRecord {

    /**
     * The unique ID of the patient.
     */
    private String PatientID;

    /**
     * The name of the patient.
     */
    private String name;

    /**
     * The date of birth of the patient.
     */
    private String dateOfBirth;


    /**
     * The gender of the patient.
     */
    private String gender;

    /**
     * The blood type of the patient.
     */
    private String bloodType;

    /**
     * The email address of the patient.
     */
    private String phoneNumber;

    /**
     * The email address of the patient.
     */
    private String emailAddress;

    /**
     * The list of medical history entries associated with the patient.
     */
    private ArrayList<MedicalHistory> medicalHistory;

    /**
     * Constructs a MedicalRecord object with the specified patient details.
     * Initializes the medical history list as an empty ArrayList.
     *
     * @param PatientID    The unique ID of the patient.
     * @param name         The name of the patient.
     * @param gender       The gender of the patient.
     * @param dateOfBirth  The date of birth of the patient.
     * @param bloodType    The blood type of the patient.
     * @param phoneNumber  The phone number of the patient.
     * @param emailAddress The email address of the patient.
     */
    public MedicalRecord(String PatientID, String name, String gender, String dateOfBirth, String bloodType, String phoneNumber, String emailAddress) {
        this.PatientID = PatientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.medicalHistory = new ArrayList<>();
    }

    /**
     * Returns the unique ID of the patient.
     *
     * @return The patient's unique ID.
     */
    public String getPatientID() {
        return PatientID;
    }

    /**
     * Returns the name of the patient.
     *
     * @return The patient's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return The patient's date of birth.
     */
    public String getDateOfBirth(){
        return dateOfBirth;
    }

    /**
     * Returns the gender of the patient.
     *
     * @return The patient's gender.
     */
    public String getGender(){
        return gender;
    }

    /**
     * Returns the phone number of the patient.
     *
     * @return The patient's phone number.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Sets the phone number for the patient.
     *
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email address of the patient.
     *
     * @return The patient's email address.
     */
    public String getEmailAddress(){
        return emailAddress;
    }

    /**
     * Sets the email address for the patient.
     *
     * @param emailAddress The email address to set.
     */
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the blood type of the patient.
     *
     * @return The patient's blood type.
     */
    public String getBloodType(){
        return bloodType;
    }

    /**
     * Adds a medical history entry to the patient's record.
     *
     * @param diagnosis The medical history entry to add.
     */
    public void addMedicalHistory(MedicalHistory diagnosis){
        this.medicalHistory.add(diagnosis);
    }

    /**
     * Returns the list of medical history entries associated with the patient.
     *
     * @return The list of medical history entries.
     */
    public ArrayList<MedicalHistory> getMedicalHistory(){
        return medicalHistory;
    }

}