package Entity;

import java.util.ArrayList;

public class MedicalRecord {
    private String PatientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String phoneNumber;
    private String emailAddress;
    private ArrayList<Diagnoses> diagnoses;

    public MedicalRecord(String PatientID, String name, String dateOfBirth, String gender, String bloodType, String phoneNumber, String emailAddress) {
        this.PatientID = PatientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.diagnoses = new ArrayList<>();
    }

    public String getPatientID() {
        return PatientID;
    }

    public String getName(){
        return name;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public String getGender(){
        return gender;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getBloodType(){
        return bloodType;
    }

    public void addDiagnoses(Diagnoses diagnosis){
        this.diagnoses.add(diagnosis);
    }

    public ArrayList<Diagnoses> getDiagnoses(){
        return diagnoses;
    }

}