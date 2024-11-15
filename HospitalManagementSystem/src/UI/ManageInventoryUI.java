package src.UI;

import src.Controller.AdministratorController;
import src.Entity.Medication;
import src.Interface.IDisplayableView;
import src.Interface.IListDisplayableView;
import src.Repository.InventoryRepository;
import src.View.CommonView;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageInventoryUI {

    private Scanner scanner;

    /**
     * Constructs a ManageInventoryUI instance with an initialized scanner for user input.
     */
    public ManageInventoryUI(){
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu for managing inventory, allowing the user to view inventory,
     * manage inventory, or return to the previous menu.
     *
     * @param administratorController The controller responsible for managing inventory operations.
     * @param inventoryView The view interface for displaying the list of medications in inventory.
     * @param medicationView The view interface for displaying individual medication details.
     */
    public void manageInventoryMenu(AdministratorController administratorController, IListDisplayableView<Medication> inventoryView, IDisplayableView<Medication> medicationView) { 

        int choice = -1;
        do {
            try {
                CommonView.newPage();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Inventory");
                System.out.println("(2) Manage Inventory"); 
                System.out.println("(3) Back"); 

                choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        inventoryView.display(InventoryRepository.getAllMedicines());
                        break;
                    case 2:
                        manageInventory(administratorController, medicationView);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid input. Please enter an integer between 1 and 3!.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 1 and 3!.");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while(choice != 3);
    }

    /**
     * Manages inventory operations including adding stock, removing stock, adding new medicines,
     * and setting stock thresholds.
     *
     * @param administratorController The controller responsible for managing inventory operations.
     * @param medicationView The view interface for displaying individual medication details.
     */
    public void manageInventory(AdministratorController administratorController, IDisplayableView<Medication> medicationView) {

        int choice = -1;
        String medicineName;
        int quantity;
        int threshold;

        do {
            try {
                CommonView.newPage();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) Add Stock");
                System.out.println("(2) Remove Stock");
                System.out.println("(3) Add New Medicine");
                System.out.println("(4) Set new stock threshold");
                System.out.println("(5) Back");
                System.out.println();

                choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        try {
                            System.out.println("Input medicine name:");
                            scanner.nextLine();
                            medicineName = scanner.nextLine();
                            if(InventoryRepository.get(medicineName) == null){
                                System.out.println("Please add a new medicine.");
                                continue;
                            }
                            System.out.println("Input quantity to add:");
                            quantity = scanner.nextInt();

                            administratorController.addStock(medicineName, quantity);
                            System.out.println();
                            medicationView.display(InventoryRepository.get(medicineName));
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input for quantity. Please enter a number.");
                            scanner.next();  
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Input medicine name:");
                            scanner.nextLine();
                            medicineName = scanner.nextLine();
                            if(InventoryRepository.get(medicineName) == null){
                                System.out.println("Please add a new medicine.");
                                continue;
                            }
                            System.out.println("Input quantity to remove:");
                            quantity = scanner.nextInt();
                            medicationView.display(InventoryRepository.get(medicineName));
                            administratorController.removeStock(medicineName, quantity);
                            medicationView.display(InventoryRepository.get(medicineName));
                            System.out.println();
                        } catch(InputMismatchException e) {
                            System.out.println("Invalid input for quantity. Please enter a number.");
                            scanner.next();  
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Input medicine name:");
                            scanner.nextLine();
                            medicineName = scanner.nextLine();

                            System.out.println("Input quantity of medicine:");
                            quantity = scanner.nextInt();

                            System.out.println("Input stock threshold:");
                            threshold = scanner.nextInt();

                            administratorController.addNewMedicine(medicineName, quantity, threshold);
                            System.out.println();
                            medicationView.display(InventoryRepository.get(medicineName));
                        } catch(InputMismatchException e) {
                            System.out.println("Invalid input for quantity. Please enter a number.");
                            scanner.next();  
                        }
                        break;
                    case 4: 
                        System.out.println("Input medicine name:");
                        scanner.nextLine();
                        medicineName = scanner.nextLine();
                        if(InventoryRepository.get(medicineName) == null){
                            System.out.println("Please add a new medicine.");
                            continue;
                        }
                        System.out.println("Input new stock threshold:");
                        threshold = scanner.nextInt();

                        administratorController.setThresholdStock(medicineName, threshold);
                        System.out.println();
                        medicationView.display(InventoryRepository.get(medicineName));
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid input. Please enter an integer between 1 and 5!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 1 and 5!.");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while(choice != 5);
    }
}
