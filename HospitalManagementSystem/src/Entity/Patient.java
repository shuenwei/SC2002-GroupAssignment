package Entity;

import java.util.*;

import UI.PatientUI;

public class Patient extends User {

    private String dateOfBirth;
	private String bloodType;
    private String contactInformation;

	private List<Appointment> Appointments;
	private MedicalRecord MedicalRecord;

    public Patient(String hospitalId,String password, String name, String gender, String dateOfBirth, String bloodType, String contactInformation) {
        super(hospitalId,password,name,gender);
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
    }

    public void displayMenu(User currentUser) {
        PatientUI patientUi = new PatientUI((Patient) currentUser);
        patientUi.displayMenu();
    }

}