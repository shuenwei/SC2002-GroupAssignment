package Entity;


import UI.PatientUI;
import java.util.*;

public class Patient extends User {

    private String dateOfBirth;
	private String bloodType;
    private String contactInformation;


	private ArrayList<Appointment> appointments;
	private MedicalRecord medicalRecord;

    public Patient(String hospitalId,String password, String name, String gender, String dateOfBirth, String bloodType,String emailAddress, String phoneNumber) {
        super(hospitalId,password,name,gender);
        this.medicalRecord = new MedicalRecord(hospitalId, name, gender, dateOfBirth, bloodType, phoneNumber, emailAddress);
        appointments = new ArrayList<>();
    }

    public void displayMenu(User currentUser) {
        PatientUI patientUi = new PatientUI((Patient) currentUser);
        patientUi.displayMenu();
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodType(){
        return bloodType;
    }

    public void setBloodType(String bloodType){
        this.bloodType = bloodType;
    }

    public String getContactInformation(){
        return contactInformation;
    }

    public void setContactInformation(String contactInformation){
        this.contactInformation = contactInformation;
    }

    public ArrayList<Appointment> getAppointments(){
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
    }

}