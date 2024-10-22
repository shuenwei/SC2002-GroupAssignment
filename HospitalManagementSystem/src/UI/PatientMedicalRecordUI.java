package UI;

import Entity.MedicalRecord;
import java.util.Scanner;

public class PatientMedicalRecordUI {

    private MedicalRecord medicalRecord;

    public PatientMedicalRecordUI(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void displayMedicalRecord() {
        if(medicalRecord.getMedicalHistory() != null) {
            MedicalHistoryUI medicalHistoryUI = new MedicalHistoryUI(medicalRecord.getMedicalHistory());
            System.out.println("PatientID: " + medicalRecord.getPatientID());
            System.out.println("Name: " + medicalRecord.getName());
            System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
            System.out.println("Gender: " + medicalRecord.getGender());
            System.out.println("Email Address: " + medicalRecord.getEmailAddress());
            System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
            System.out.println("BloodType: " + medicalRecord.getBloodType());
            medicalHistoryUI.printMedicalHistory();
        }
        else{
            System.out.println("PatientID: " + medicalRecord.getPatientID());
            System.out.println("Name: " + medicalRecord.getName());
            System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
            System.out.println("Gender: " + medicalRecord.getGender());
            System.out.println("Email Address: " + medicalRecord.getEmailAddress());
            System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
            System.out.println("BloodType: " + medicalRecord.getBloodType());
            System.out.println("MedicalHistory: There are no diagnoses ");
        }

    }

    public void editMedicalRecord() {
        System.out.println("1) Change Email Address");
        System.out.println("2) Change Phone Number");
        System.out.println("Please enter your choice:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("Enter new Email Address:");
            medicalRecord.setEmailAddress(input.next());
            System.out.println("Updated Email Address: " + medicalRecord.getEmailAddress());
        }
        else if (choice == 2) {
            System.out.println("Enter new Phone Number:");
            medicalRecord.setPhoneNumber(input.next());
            System.out.println("Updated Phone Number: " + medicalRecord.getPhoneNumber());
        }

    }

}
