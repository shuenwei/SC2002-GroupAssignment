package Entity;

import java.util.*;

public class Patient extends User {

    private String dateOfBirth;
	private String bloodType;
    private String contactInformation;

	private List<Appointment> Appointment;
	private MedicalRecord MedicalRecord;

    public Patient(String hospitalId,String password, String name, String gender, String dateOfBirth, String bloodType, String contactInformation) {
        super(hospitalId,password,name,gender);
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
    }

}