package UI;

import Controller.PharmacistController;
import Entity.Appointment;
import Entity.Inventory;
import Entity.Pharmacist;
import Enums.AppointmentStatus;
import View.PendingMedicineView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacistUI {
    private Pharmacist pharmacist;
    private PharmacistController pharmacistController;
    private PendingMedicineView viewPending;
    Scanner sc = new Scanner(System.in);

    public PharmacistUI(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        this.pharmacistController= new PharmacistController(pharmacist);
        viewPending = new PendingMedicineView();
    }
    
    public void displayMenu() {
        ArrayList<Appointment> appt = pharmacistController.getAllAppointmentsByStatus(AppointmentStatus.MEDICINE_PENDING);
        int option = -1;

        do {
            try {
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
                        viewPending.display(appt);
                        System.out.println("Select which appointment to update prescription: ");
                        int choice = sc.nextInt();
                        sc.nextLine();
                        Appointment a = appt.get(choice - 1);
                        pharmacistController.updatePrescription(a);
                        break;
                    case 3:
                        Inventory.viewInventory();
                        break;
                    case 4:
                        submitReplenishmentRequest();
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
    
    public void submitReplenishmentRequest() {
        String requestMedicine;

        System.out.println();
        System.out.println("Input medicine to be replenished:");
        sc.nextLine();
        requestMedicine = sc.nextLine();

        pharmacistController.submitRequest(requestMedicine);
    }
}
