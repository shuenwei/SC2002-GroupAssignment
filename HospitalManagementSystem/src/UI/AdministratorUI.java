package UI;

import Controller.AdministratorController;
import Entity.Administrator;
import Entity.Appointment;
import Entity.Inventory;
import Entity.Staff;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;
import Repository.UserRepository;
import View.AppointmentListView;
import View.AppointmentView;
import View.StaffListView;
import View.StaffView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministratorUI {

    private Administrator administrator;
    private AdministratorController administratorController;
    private AppointmentView appointmentView;
    private ManageStaffUI manageStaffUI;
    private ManageInventoryUI manageInventoryUI;
    private Scanner scanner;


    public AdministratorUI(Administrator administrator) {
        this.administrator = administrator;
        this.administratorController = new AdministratorController(administrator);
        this.appointmentView = new AppointmentView();
        this.scanner = new Scanner(System.in);
        this.manageInventoryUI = new ManageInventoryUI(administratorController);
        this.manageStaffUI = new ManageStaffUI(administratorController);
    }

    public void displayMenu(){
        int option = -1;

        IListDisplayableView<Staff> staffListView = new StaffListView();
        IDisplayableView<Staff> staffView = new StaffView();
        IListDisplayableView<Appointment> appointmentListView = new AppointmentListView();
        IDisplayableView<Appointment> appointmentView = new AppointmentView();
        
        
        do {
            try {
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
                    case 1: manageStaffUI.manageUserMenu();
                            break;
                    case 2: administratorController.showAllDoctorsAppointment(appointmentView); 
                            break;
                    case 3: manageInventoryUI.manageInventoryMenu();
                        break;
                    case 4:
                        requestMenu();
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

    public void showStaffs(){

        ArrayList <Staff> staffs = UserRepository.getAllStaff();
        IListDisplayableView<Staff> staffListView = new StaffListView();
        staffListView.display(staffs);

    }


    public void requestMenu() {
        int choice = -1;
        String requestMedicine;
        
        do{
            try{
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Requests");
                System.out.println("(2) Approve Request");
                System.out.println("(3) Back");


                choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        Inventory.viewRequests();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Input medicine replenishment request to be approved:");
                        scanner.nextLine();
                        requestMedicine = scanner.nextLine();
                        administratorController.approveRequest(requestMedicine);
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



