package Entity;

import java.util.*;

import UI.PatientMedicalRecordUI;
import UI.PatientUI;

public class Patient extends User {

    private String dateOfBirth;
	private String bloodType;
    private String contactInformation;

	private List<Appointment> Appointments;
	private MedicalRecord medicalRecord;

    public Patient(String hospitalId,String password, String name, String gender, String dateOfBirth, String bloodType, String contactInformation) {
        super(hospitalId,password,name,gender);
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
        this.medicalRecord = new MedicalRecord(hospitalId, name, gender, dateOfBirth, bloodType,null,contactInformation);
    }

    public void displayMenu(User currentUser) {
        PatientUI patientUi = new PatientUI((Patient) currentUser);
        PatientMedicalRecordUI patientMedicalRecordUI = new PatientMedicalRecordUI((MedicalRecord) ((Patient) currentUser).medicalRecord);
        patientUi.displayMenu();
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

}