package UI;

import Controller.PharmacistController;
import Entity.Appointment;
import Entity.Medication;
import Entity.Pharmacist;
import Enums.AppointmentStatus;
import Interface.IListDisplayableView;
import Repository.InventoryRepository;
import View.CommonView;
import View.PendingMedicineView;
import View.ViewListInventory;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The PharmacistUI class provides a user interface for a pharmacist to interact with the system.
 * It allows pharmacists to view appointments, update prescription status, view medication inventory,
 * submit replenishment requests, and log out.
 */

public class PharmacistUI {

    /**
     * The pharmacist associated with this UI.
     */
    private Scanner sc;
    private Pharmacist pharmacist;
    
    /**
     * Constructs a PharmacistUI instance for the specified pharmacist.
     *
     * @param pharmacist The pharmacist associated with this UI.
     */
    public PharmacistUI(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        sc = new Scanner(System.in);
    }

    /**
     * Displays the main menu for the pharmacist to choose actions.
     * Options include viewing appointment records, updating prescription status, viewing inventory,
     * submitting replenishment requests, and logging out.
     */    
    public void displayMenu() {
        
        int option = -1;
        int choice =0;

        IListDisplayableView<Medication> inventoryView = new ViewListInventory();
        
        PharmacistController pharmacistController = new PharmacistController(pharmacist);
        ArrayList<Appointment> appt = pharmacistController.getAllAppointmentsByStatus(AppointmentStatus.MEDICINE_PENDING);
        PendingMedicineView viewPending = new PendingMedicineView();

        do {
            try {
                CommonView.newPage();
                System.out.println("Hello, " + pharmacist.getName());
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Appointment Outcome Record");
                System.out.println("(2) Update Prescription Status"); 
                System.out.println("(3) View Medication Inventory"); 
                System.out.println("(4) Submit Replenishment Request"); 
                System.out.println("(5) Logout"); 
                
                option = sc.nextInt();
                switch (option) {
                    case 1: 
                        viewPending.display(appt);
                        break;
                    case 2: 
                        do {
                            if (appt.isEmpty()) {
                                System.out.println("No appointments to update prescription.");
                                break;
                            }
                            
                            viewPending.display(appt);
                            
                            System.out.println("Select which appointment to update prescription: ");
                            choice = sc.nextInt();
                            if (choice < 1 || choice > appt.size()) {
                                System.out.println("Invalid choice. Please try again.");
                            }
                        } while (choice < 1 || choice > appt.size());
                        if (appt.isEmpty()) {
                            break;
                        }
                        sc.nextLine();
                        Appointment a = appt.get(choice - 1);
                        pharmacistController.updatePrescription(a);
                        break;
                    case 3:
                        inventoryView.display(InventoryRepository.getAllMedicines());
                        break;
                    case 4:
                        submitReplenishmentRequest(pharmacistController);
                        break;
                    case 5:
                        System.out.println("You are now logged out.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.next();
            }
        } while (option != 5);
    }
    

    /**
     * Submits a replenishment request for a specified medication.
     * Prompts the user to input the medicine name and forwards the request to the pharmacist controller.
     */
    public void submitReplenishmentRequest(PharmacistController pharmacistController) {
        String requestMedicine;

        System.out.println();
        System.out.println("Input medicine to be replenished:");
        sc.nextLine();
        requestMedicine = sc.nextLine();

        pharmacistController.submitRequest(requestMedicine);
    }
}
