package UI;

import Controller.NurseController;
import Entity.Nurse;
import Entity.Patient;
import Repository.UserRepository;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NurseUI {

    private Nurse nurse;
    private Patient patient;
    private NurseController nurseController;

    Scanner scanner = new Scanner(System.in);
    
    public NurseUI(Nurse nurse) {
        this.nurse = nurse;
        this.nurseController = new NurseController(nurse);
    }

     public void displayMenu(){
        int option = -1;
        
        do {
            try {
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Patient");
                System.out.println("(2) Add Patient"); 
                System.out.println("(3) Remove Patient"); 
                System.out.println("(4) Logout"); 
                option = scanner.nextInt();

                switch (option) {
                    case 1: //viewPatient();
                            break;
                    case 2: addPatient();
                            break;
                    case 3: removePatient();
                            break;
                    case 4: System.out.println("You are now logged out.");
                            return;
                    default:
                        System.out.println("Invalid number. Please enter a number between 1 and 4!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4!");
                scanner.next();
            }
        } while (option != 4);
    }

    public void addPatient(){

        try{
            System.out.println("Enter Name: ");
            scanner.nextLine();
            String hospName = scanner.nextLine();
            if (hospName.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty.");
            }
            System.out.println("Enter Gender: ");
            String hospGender = scanner.nextLine();
            if (hospGender.isEmpty()) {
                throw new IllegalArgumentException("Gender cannot be empty.");
            }
            System.out.println("Date Of Birth (DD/MM/YYYY): ");
            String hospDate = scanner.nextLine();
            if (hospDate.isEmpty()) {
                throw new IllegalArgumentException("Date of Birth cannot be empty.");
            }
            System.out.println("Blood Type: ");
            String hospBlood = scanner.nextLine();
            if (hospBlood.isEmpty()) {
                throw new IllegalArgumentException("Blood Type cannot be empty.");
            }
            System.out.println("Email Address: ");
            String hospEmailAddress = scanner.nextLine();
            System.out.println();
            if (hospEmailAddress.isEmpty()) {
                throw new IllegalArgumentException("Contact Information cannot be empty.");
            }
            System.out.println("Phone Number: ");
            String hospPhoneNumber = scanner.nextLine();
            System.out.println();
            if (hospPhoneNumber.isEmpty()) {
                throw new IllegalArgumentException("Contact Information cannot be empty.");
            }
        
            ArrayList<Patient> patients = UserRepository.getAllPatients();
    
            int max_p = 0;
            for(Patient p : patients){
                if(Integer.parseInt(p.getHospitalId().substring(1))>max_p){
                    max_p = Integer.parseInt(p.getHospitalId().substring(1));
                }
            }

            max_p++;
            patient = new Patient("P" + String.format("%04d", max_p), "",hospName, hospGender, hospDate, hospBlood, hospEmailAddress,hospPhoneNumber );

            nurseController.addPatient((Patient) patient);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type. Age must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());

        }

    }

    public void removePatient(){

        try{
            System.out.println("Enter Hospital ID: ");
            scanner.nextLine();
            String hospID = scanner.nextLine();
            if (hospID.isEmpty()) {
                throw new IllegalArgumentException("Hospital ID cannot be empty.");
            }

            boolean result = nurseController.removePatient(hospID);

            if (result) {
                System.out.println("Patient removed successfully.");
            } else {
                System.out.println("Patient not found.");
            }

        } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        }

    // public void viewPatient(String hospitalID){
    //     try{
    //         System.out.println("Enter Hospital ID: ");
    //         scanner.nextLine();
    //         String hospID = scanner.nextLine();
    //         if (hospID.isEmpty()) {
    //             throw new IllegalArgumentException("Hospital ID cannot be empty.");
    //         }

    //         Patient patient = (Patient) nurseController.getPatient(hospID);
    //         if (patient == null) {
    //             System.out.println("Patient not found.");
    //         } else {
    //             System.out.println("Patient Name: " + patient.getName());
    //             System.out.println("Patient


}


