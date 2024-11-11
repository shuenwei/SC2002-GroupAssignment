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
                    case 1: try{
                                System.out.println("Enter Hospital ID: ");
                                scanner.nextLine();
                                String hospID = scanner.nextLine();
                                if (hospID.isEmpty()) {
                                    throw new IllegalArgumentException("Hospital ID cannot be empty.");
                                }
                    
                                patient = (Patient) nurseController.getPatient(hospID);
                                patientView.display(patient);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("An unexpected error occurred: " + e.getMessage());
                            }
                            break;
                    case 2: addPatient(nurseController);
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
    public void addPatient(NurseController nurseController) {

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
