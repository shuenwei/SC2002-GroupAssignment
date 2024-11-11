package src.UI;

import src.Controller.NurseController;
import src.Entity.Nurse;
import src.Entity.Patient;
import src.Interface.IDisplayableView;
import src.Repository.UserRepository;
import src.View.CommonView;
import src.View.PatientView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NurseUI {

    private Nurse nurse;
    private Patient patient;
    private Scanner scanner;
     
    /**
     * Constructs a NurseUI with the specified nurse and initializes a scanner for input.
     *
     * @param nurse The nurse associated with this user interface.
     */
    public NurseUI(Nurse nurse) {
        this.nurse = nurse;
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu for the nurse, providing options to view, add, and remove patients,
     * as well as to log out of the system.
     */
    public void displayMenu(){
        int option = -1;
        String hospID;

        NurseController nurseController = new NurseController(nurse);
        IDisplayableView<Patient> patientView = new PatientView();

        do {
            try {
                CommonView.newPage();
                System.out.println("Hello, " + nurse.getName());
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Patient");
                System.out.println("(2) Add Patient"); 
                System.out.println("(3) Remove Patient"); 
                System.out.println("(4) Logout"); 
                option = scanner.nextInt();

                switch (option) {
                    case 1: scanner.nextLine();
                            boolean validID = false;
                            do{   
                                System.out.println("Enter Hospital ID: ");
                                hospID = scanner.nextLine();
                                if (hospID.isEmpty()) {
                                    System.out.println("Hospital ID cannot be empty.");
                                }
                                else if(!(UserRepository.get(hospID) instanceof Patient) || UserRepository.get(hospID) == null){
                                    System.out.println("Patient not found.");
                                }
                                else{
                                    validID = true;
                                }
                            }while(!validID);
                    
                            patient = (Patient) nurseController.getPatient(hospID);
                            patientView.display(patient);
                            
                            break;
                    case 2: addPatient(nurseController, patientView);
                            break;
                    case 3: removePatient(nurseController);
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

    /**
     * Adds a new patient to the system by prompting the nurse to enter the patient's information.
     * Validates input fields and assigns a unique hospital ID to the new patient.
     *
     * @param nurseController The controller responsible for managing patient operations.
     */
    public void addPatient(NurseController nurseController, IDisplayableView<Patient> patientView) {
        String hospName;
        String hospEmailAddress;
        String hospBlood;
        String hospPhoneNumber;
        String hospDate;
        Enums.Gender gender = null;
        try{
            boolean validName = false;
            scanner.nextLine();
            do{
            System.out.println("Enter Name: ");
            
            hospName = scanner.nextLine();
            if (hospName.isEmpty()) {
                System.out.println("Name cannot be empty.");
            } else {
                validName = true;
            }
            }while(!validName);
            boolean validGender = false;
            do{
            System.out.println("Enter Gender: ");
            String hospGender = scanner.nextLine();
            if (hospGender.isEmpty()) {
                System.out.println("Gender cannot be empty.");
            }
            else {
                try {
                    gender = Gender.valueOf(hospGender);
                    validGender = true;  
                } catch (IllegalArgumentException e) {
                    
                    System.out.println("Invalid gender. Please enter either MALE or FEMALE or OTHERS.");
                }
            }
            }while(!validGender);
            boolean dateOfBirth = false;
            do{
            System.out.println("Date Of Birth (DD/MM/YYYY): ");
            hospDate = scanner.nextLine();
            if (hospDate.isEmpty()) {
                System.out.println("Date of Birth cannot be empty.");
            }
            else if (!hospDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println("Date of Birth must be in the format DD/MM/YYYY.");
            }
            else{
                dateOfBirth = true;
            }
            }while(!dateOfBirth);
            boolean validBloodType = false;
            do{
            System.out.println("Blood Type: ");
            hospBlood = scanner.nextLine();
            if (hospBlood.isEmpty()) {
                System.out.print("Blood Type cannot be empty.");
            }
            else{
                validBloodType = true;
            }
            }while(!validBloodType);
            boolean validEmailAddress = false;
            do{
            System.out.println("Email Address: ");
            hospEmailAddress = scanner.nextLine();
            if (hospEmailAddress.isEmpty()) {
                System.out.println("Contact information cannot be empty.");
            } 
            else if (!hospEmailAddress.contains("@")) {
                System.out.println("Email must contain '@' symbol.");
            } 
            else if (!hospEmailAddress.contains(".")) {
                System.out.println("Email must contain '.' symbol.");
            }
            else{
                validEmailAddress = true;
            }
            }while(!validEmailAddress);
            boolean validPhoneNumber = false;
            do{
            System.out.println("Phone Number: ");
            hospPhoneNumber = scanner.nextLine();
            if (hospPhoneNumber.length() != 8 || !hospPhoneNumber.matches("\\d{8}")) {
                System.out.println("Phone number must be exactly 8 digits long and contain only numbers.");
            } else {
                validPhoneNumber = true;
            }
            }while(!validPhoneNumber);
            System.out.println();
        
            ArrayList<Patient> patients = UserRepository.getAllPatients();
    
            int max_p = 0;
            for(Patient p : patients){
                if(Integer.parseInt(p.getHospitalId().substring(1))>max_p){
                    max_p = Integer.parseInt(p.getHospitalId().substring(1));
                }
            }

            max_p++;
            patient = new Patient("P" + String.format("%04d", max_p), "",hospName, gender, hospDate, hospBlood, hospEmailAddress,hospPhoneNumber );

            nurseController.addPatient((Patient) patient);
            System.out.println("The patient has been added successfully.");
            System.out.println();
            patientView.display(patient);


        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type. Age must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());

        }

    }

    /**
     * Removes a patient from the system based on their hospital ID.
     * Prompts the nurse for the patient's ID and confirms removal if the patient is found.
     *
     * @param nurseController The controller responsible for managing patient operations.
     */
    public void removePatient(NurseController nurseController) {

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

}
