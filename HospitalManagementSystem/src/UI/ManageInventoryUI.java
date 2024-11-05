package UI;

import Controller.AdministratorController;
import Entity.Inventory;
import View.CommonView;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageInventoryUI {

    private AdministratorController administratorController;
    private Scanner scanner;

    public ManageInventoryUI(AdministratorController administratorController){
        this.administratorController = administratorController;
        scanner = new Scanner(System.in);
    }

    public void manageInventoryMenu() {
        int choice = -1;
        do{
            try{
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
                        Inventory.viewInventory();
                        break;
                    case 2:
                        manageInventory();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid input. Please enter an integer between 1 and 3!.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 1 and 3!.");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }while(choice != 3);
    }

    public void manageInventory() {
        int choice = -1;
        String medicineName;
        int quantity;
        int threshold;

        do{
            try{
            CommonView.newPage();
            System.out.println();
            System.out.println("Select an option:");
            System.out.println();
            System.out.println("(1) Add Stock");
            System.out.println("(2) Remove Stock");
            System.out.println("(3) Add New Medicine");
            System.out.println("(4) Back");
            System.out.println();

            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    try {
                        System.out.println("Input medicine name:");
                        scanner.nextLine();
                        medicineName = scanner.nextLine();
                        
                        System.out.println("Input quantity to add:");
                        quantity = scanner.nextInt();

                        administratorController.addStock(medicineName, quantity);
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

                        System.out.println("Input quantity to remove:");
                        quantity = scanner.nextInt();

                        administratorController.removeStock(medicineName, quantity);
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
                    } catch(InputMismatchException e) {
                        System.out.println("Invalid input for quantity. Please enter a number.");
                        scanner.next();  
                    }
                    break;
                case 4: return;
                default:
                    System.out.println("Invalid input. Please enter an integer between 1 and 4!");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 1 and 4!.");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }while(choice != 4);
    }
}
