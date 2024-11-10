package UI;

import Controller.AdministratorController;
import Entity.Administrator;
import Entity.Appointment;
import Entity.Medication;
import Entity.Staff;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;
import Repository.InventoryRepository;
import View.AppointmentView;
import View.CommonView;
import View.StaffListView;
import View.StaffView;
import View.ViewInventory;
import View.ViewListInventory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministratorUI {

    private Administrator administrator;
    private ManageStaffUI manageStaffUI;
    private ManageInventoryUI manageInventoryUI;
    private Scanner scanner;


    public AdministratorUI(Administrator administrator) {
        this.administrator = administrator;
        scanner = new Scanner(System.in);
        
    }

    public void displayMenu(){
        int option = -1;


        AdministratorController administratorController = new AdministratorController(administrator);
        manageInventoryUI = new ManageInventoryUI();
        manageStaffUI = new ManageStaffUI();
        IListDisplayableView<Staff> staffListView = new StaffListView();
        IDisplayableView<Staff> staffView = new StaffView();
        IDisplayableView<Appointment> appointmentView = new AppointmentView();
        IListDisplayableView<Medication> inventoryView = new ViewListInventory();
        IDisplayableView<Medication> medicationView = new ViewInventory();


        do {
            try {
                CommonView.newPage();
                System.out.println("Hello, " + administrator.getName());
                System.out.println();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View and Manage Hospital Staff");
                System.out.println("(2) View Appointment Details"); 
                System.out.println("(3) View and Manage Medication Inventory"); 
                System.out.println("(4) Approve Replenishment Requests"); 
                System.out.println("(5) Logout"); 
                option = scanner.nextInt();

                switch (option) {
                    case 1: manageStaffUI.manageUserMenu(administratorController,staffView,staffListView);
                            break;
                    case 2: administratorController.showAllAppointments(appointmentView); 
                            break;
                    case 3: manageInventoryUI.manageInventoryMenu(administratorController,inventoryView, medicationView);
                        break;
                    case 4:
                        requestMenu(administratorController, medicationView);
                        break;
                    case 5:
                        System.out.println("You are now logged out.");
                        return;
                    default:
                        System.out.println("Invalid number. Please enter a number between 1 and 5!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5!");
                scanner.next();
            }
        } while (option != 5);
    }


    public void requestMenu(AdministratorController administratorController, IDisplayableView<Medication> medicationView){ 
        int choice = -1;
        String requestMedicine;
        
        do{
            try{
                CommonView.newPage();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Requests");
                System.out.println("(2) Approve Request");
                System.out.println("(3) Back");


                choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        InventoryRepository.viewRequests();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Input medicine replenishment request to be approved:");
                        scanner.nextLine();
                        requestMedicine = scanner.nextLine();
                        administratorController.approveRequest(requestMedicine);
                        medicationView.display(InventoryRepository.get(requestMedicine));
                        break;
                    case 3: return;
                    default:
                        System.out.println("Invalid input. Please enter an integer between 1 and 3!");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer 1 and 3!.");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }while(choice != 3);
    }

}




