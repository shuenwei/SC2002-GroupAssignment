package UI;

import Entity.Diagnoses;
import Entity.PrescribedMedication;
import Entity.MedicalRecord;
import java.util.Scanner;
import Controller.DoctorController;


public class DoctorMedicalRecordUI {
    private DoctorController doctorController;
    private MedicalRecord medicalRecord;

    public DoctorMedicalRecordUI(DoctorController doctorController) {
        this.doctorController = doctorController;

    }

    public void displayMedicalRecord() {
        System.out.println("Please enter the PatientID: ");
        Scanner scanner = new Scanner(System.in);
        String patientID = scanner.nextLine();
        MedicalRecord medicalRecord = doctorController.findMedicalRecordByID(patientID);
        if(medicalRecord != null) {
            System.out.println("PatientID: " + medicalRecord.getPatientID());
            System.out.println("Name: " + medicalRecord.getName());
            System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
            System.out.println("Gender: " + medicalRecord.getGender());
            System.out.println("Email Address: " + medicalRecord.getEmailAddress());
            System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
            System.out.println("BloodType: " + medicalRecord.getBloodType());
        }
        else {
            System.out.println("Sorry Patient with ID: " + patientID + " does not exist");
        }
    }

    public void editMedicalRecord() {
        System.out.println("Please enter the PatientID: ");
        Scanner scanner = new Scanner(System.in);
        String patientID = scanner.nextLine();
        MedicalRecord medicalRecord = doctorController.findMedicalRecordByID(patientID);
        if(medicalRecord == null) {
            System.out.println("Sorry Patient with ID: " + patientID + " does not exist");
        }
        else {
            System.out.println("1) Create new diagnosis ");
            System.out.println("2) Edit diagnosis ");
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter the name of the new diagnosis: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter the Treatment Plan of the diagnosis: ");
                    String treatmentPlan = scanner.nextLine();
                    System.out.println("Please enter the prescribed medication: ");
                    String medication = scanner.nextLine();
                    //Diagnoses newdiagnosis = new Diagnoses();
                    break;
                case 2:
                    System.out.println("Please enter the diagnosis you want to edit: ");
                    String diagnosis = scanner.nextLine();
                    Diagnoses diagnoses = doctorController.findDiagnosis(diagnosis, patientID);
                    System.out.println("Please enter updated name of the diagnosis: ");
                    String updatedName = scanner.nextLine();
                    diagnoses.setDiagnosis(updatedName);
                    System.out.println("Please enter updated Treatment Plan of the diagnosis: ");
                    String updatedTreatmentPlan = scanner.nextLine();
                    diagnoses.setTreatmentPlan(updatedTreatmentPlan);
                    break;
                default:
                    break;

            }
        }



    }


}
