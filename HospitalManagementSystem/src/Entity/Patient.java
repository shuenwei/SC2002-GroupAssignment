package Entity;


import UI.PatientUI;
import java.util.*;

public class Patient extends User {

    private String dateOfBirth;
	private String bloodType;
    private String contactInformation;


	private ArrayList<Appointment> appointments;
	private MedicalRecord medicalRecord;

    public Patient(String hospitalId,String password, String name, String gender, String dateOfBirth, String bloodType, String contactInformation) {
        super(hospitalId,password,name,gender);
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
        
        this.medicalRecord = new MedicalRecord(hospitalId, name, gender, dateOfBirth, bloodType,null,contactInformation);
        appointments = new ArrayList<>();
    }

    public void displayMenu(User currentUser) {
        PatientUI patientUi = new PatientUI((Patient) currentUser);
        patientUi.displayMenu();
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
  
    public String getName(){
        return super.getName();
    }

    public void setName(String name){
        super.setName(name);
    }//

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