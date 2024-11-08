package UI;

import Controller.PharmacistController;
import Entity.Appointment;
import Entity.Inventory;
import Entity.Medication;
import Entity.Pharmacist;
import Enums.AppointmentStatus;
import Interface.IListDisplayableView;
import View.CommonView;
import View.PendingMedicineView;
import View.ViewListInventory;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacistUI {
    private Pharmacist pharmacist;
    private PharmacistController pharmacistController;
    Scanner sc = new Scanner(System.in);

    public PharmacistUI(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        this.pharmacistController= new PharmacistController(pharmacist);
    }
    
    public void displayMenu() {
        
        int option = -1;
        int choice =0;

        IListDisplayableView<Medication> inventoryView = new ViewListInventory();
        
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
                        viewPending.display(appt);
                        do {
                            System.out.println("Select which appointment to update prescription: ");
                            choice = sc.nextInt();
                            if (choice < 1 || choice > appt.size()) {
                                System.out.println("Invalid choice. Please try again.");
                            }
                        } while (choice < 1 || choice > appt.size());
                        sc.nextLine();
                        Appointment a = appt.get(choice - 1);
                        pharmacistController.updatePrescription(a);
                        break;
                    case 3:
                        inventoryView.display(Inventory.getAllMedicines());
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
