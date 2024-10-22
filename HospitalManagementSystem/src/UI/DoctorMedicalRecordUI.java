package UI;

import Entity.MedicalHistory;
import Entity.MedicalRecord;

import java.util.InputMismatchException;
import java.util.Scanner;
import Controller.DoctorController;
import View.MedicalHistoryView;


public class DoctorMedicalRecordUI {
    private DoctorController doctorController;
    private MedicalHistoryView medicalHistoryView;

    public DoctorMedicalRecordUI(DoctorController doctorController) {
        this.doctorController = doctorController;

    }

    public void displayMedicalRecord() {
        System.out.println("Please enter the PatientID: ");
        Scanner scanner = new Scanner(System.in);
        String patientID = scanner.nextLine();
        MedicalRecord medicalRecord = doctorController.findMedicalRecordByID(patientID);
        if(medicalRecord != null) {
            medicalHistoryView = new MedicalHistoryView();
            System.out.println("PatientID: " + medicalRecord.getPatientID());
            System.out.println("Name: " + medicalRecord.getName());
            System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
            System.out.println("Gender: " + medicalRecord.getGender());
            System.out.println("Email Address: " + medicalRecord.getEmailAddress());
            System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
            System.out.println("BloodType: " + medicalRecord.getBloodType());
            medicalHistoryView.display(medicalRecord.getMedicalHistory());
        }
        else {
            System.out.println("Sorry Patient with ID: " + patientID + " is not under your care, MedicalRecord not accessible");
        }
    }

    public void editMedicalRecord() {
        int choice = -1;
        System.out.println("Please enter the PatientID: ");
        Scanner scanner = new Scanner(System.in);
        String patientID = scanner.nextLine();
        MedicalRecord medicalRecord = doctorController.findMedicalRecordByID(patientID);
        if(medicalRecord == null) {
            System.out.println("Sorry Patient with ID: " + patientID + " is not under your care, MedicalRecord not accessible");
        }
        else {
            System.out.println("1) Create new diagnosis ");
            System.out.println("2) Edit diagnosis ");
            System.out.println("Please enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
            switch (choice) {
                case 1:
                    System.out.println("Please enter the name of the new diagnosis: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter the Treatment Plan of the diagnosis: ");
                    String treatmentPlan = scanner.nextLine();
                    System.out.println("Please enter the prescribed medication: ");
                    String medication = scanner.nextLine();
                    MedicalHistory newmedicalHistory = new MedicalHistory(name,treatmentPlan);
                    newmedicalHistory.addPrescribedMedications(medication);
                    medicalRecord.addMedicalHistory(newmedicalHistory);
                    break;
                case 2:
                    System.out.println("Please enter the diagnosis you want to edit: ");
                    String diagnosisName = scanner.nextLine();
                    MedicalHistory existingMedicalHistory = doctorController.findmedicalHistory(diagnosisName, patientID);
                    System.out.println("Please enter updated name of the diagnosis: ");
                    String updatedName = scanner.nextLine();
                    existingMedicalHistory.setDiagnosis(updatedName);
                    System.out.println("Please enter updated Treatment Plan of the diagnosis: ");
                    String updatedTreatmentPlan = scanner.nextLine();
                    existingMedicalHistory.setTreatmentPlan(updatedTreatmentPlan);
                    System.out.println("Please enter updated Prescribed Medication: ");
                    String updatedPrescribedMedication = scanner.nextLine();
                    existingMedicalHistory.addPrescribedMedications(updatedPrescribedMedication);
                    break;
                default:
                    break;
            }
        }
    }
}
