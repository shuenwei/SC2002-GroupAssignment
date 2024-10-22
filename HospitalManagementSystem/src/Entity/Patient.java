package Entity;


import java.util.*;

import UI.PatientMedicalRecordUI;
import Repository.UserRepository;
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
        PatientMedicalRecordUI patientMedicalRecordUI = new PatientMedicalRecordUI((MedicalRecord) ((Patient) currentUser).medicalRecord);
        patientUi.displayMenu();
    }

     public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
  
    public String getName(){
        return super.getName();
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

    public void showAllPatientAppointment(){

        List <Doctor> doctors = UserRepository.getAllDoctors();
        System.out.println();

        for (Doctor s : doctors) {
        System.out.println(s.getName() + " has the following appointments:");
        System.out.println();
        List<Appointment> appointments = s.getAppointments();

        if (appointments.isEmpty()) {
            System.out.println(" No appointments.");
        } else {
            int count = 1; 
            for (Appointment a : appointments) {
                System.out.println(" " + count + ". " + a);
                count++;
                }
            }
        System.out.println();
        }

    } // change to view 
}