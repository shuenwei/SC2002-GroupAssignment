package Entity;


import UI.PatientUI;
import java.util.*;

public class Patient extends User {


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