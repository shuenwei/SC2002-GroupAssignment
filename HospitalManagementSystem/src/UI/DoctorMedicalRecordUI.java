package UI;

import Entity.MedicalHistory;
import Entity.MedicalRecord;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Controller.DoctorController;
import View.MedicalHistoryListView;


public class DoctorMedicalRecordUI {
    private DoctorController doctorController;
    private MedicalHistoryListView medicalHistoryView;

    public DoctorMedicalRecordUI(DoctorController doctorController) {
        this.doctorController = doctorController;
    }

    public void displayMedicalRecord() {
        System.out.println("Please enter the PatientID: ");
        Scanner scanner = new Scanner(System.in);
        String patientID = scanner.nextLine();
        System.out.println();
        MedicalRecord medicalRecord = doctorController.findMedicalRecordByID(patientID);
        if(medicalRecord != null) {
            medicalHistoryView = new MedicalHistoryListView();
            System.out.println("MedicalRecord: ");
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

            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice >= 1 && choice <= 2) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.println("Please enter the name of the new diagnosis: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter the Treatment Plan of the diagnosis: ");
                    String treatmentPlan = scanner.nextLine();
                    MedicalHistory newmedicalHistory = new MedicalHistory(name,treatmentPlan);
                    while(true) {
                        System.out.print("Enter names of medication to be prescribed (If no more prescriptions type 'Exit') : ");
                        String addedPrescribedMedication = scanner.nextLine();
                        if(addedPrescribedMedication.equals("Exit")) {
                            break;
                        }
                        else {
                            newmedicalHistory.addPrescribedMedications(addedPrescribedMedication);
                        }
                    }
                    medicalRecord.addMedicalHistory(newmedicalHistory);
                    break;
                case 2:
                    ArrayList<MedicalHistory> medicalHistoryArrayList = medicalRecord.getMedicalHistory();
                    if(medicalHistoryArrayList.isEmpty()) {
                        System.out.println("There is no medical history associated with the patientID: " + patientID);
                        System.out.println("Please create a medical history first. ");
                        return;
                    }

                    MedicalHistoryListView medicalHistoryView = new MedicalHistoryListView();
                    medicalHistoryView.display(medicalHistoryArrayList);

                    int medicalHistoryChoice = -1;

                    while (medicalHistoryChoice == -1) {
                        System.out.print("Please select the diagnosis you wish to edit by entering the corresponding number: ");
                        try {
                            medicalHistoryChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (medicalHistoryChoice >= 1 && medicalHistoryChoice <= medicalHistoryArrayList.size()) {
                                break;
                            } else {
                                medicalHistoryChoice = -1;
                                System.out.println("Invalid selection. Please enter a number between 1 and " + medicalHistoryArrayList.size() + ".");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    MedicalHistory selectedMedicalHistory = medicalHistoryArrayList.get(medicalHistoryChoice - 1);
                    selectedMedicalHistory.getPrescribedMedications().clear();
                    System.out.println("Please enter updated name of the diagnosis: ");
                    String updatedName = scanner.nextLine();
                    selectedMedicalHistory.setDiagnosisandType(updatedName);
                    System.out.println("Please enter updated Treatment Plan of the diagnosis: ");
                    String updatedTreatmentPlan = scanner.nextLine();
                    selectedMedicalHistory.setTreatmentPlan(updatedTreatmentPlan);
                    while(true) {
                        System.out.print("Enter names of medication to be prescribed (If no more prescriptions type 'Exit') : ");
                        String updatedPrescribedMedication = scanner.nextLine();
                        if(updatedPrescribedMedication.equals("Exit")) {
                            break;
                        }
                        else {
                            selectedMedicalHistory.addPrescribedMedications(updatedPrescribedMedication);
                        }
                    }
                    System.out.println();
                    System.out.println("Updated Medical History: ");
                    System.out.println("Name of Diagnosis: " + selectedMedicalHistory.getDiagnosisandType());
                    System.out.println("Treatment Plan: " + selectedMedicalHistory.getTreatmentPlan());
                    System.out.println("Prescribed Medications:");
                    for(String i : selectedMedicalHistory.getPrescribedMedications()) {
                        System.out.println("-  " + i);
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
