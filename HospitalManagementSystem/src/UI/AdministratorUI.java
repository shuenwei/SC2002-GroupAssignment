package UI;

import Controller.StaffManagementController;
import Entity.Administrator;
import Entity.Doctor;
import Entity.Pharmacist;
import Entity.Staff;
import Interface.IStaffManagement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministratorUI {

    private Administrator administrator;
    private Staff staff;
    private IStaffManagement staff_management;

    public AdministratorUI(Administrator administrator) {
        this.administrator = administrator;
    }

    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;

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
                    case 1: System.out.println("Enter Hospital ID: ");
                            scanner.nextLine();
                            String hospID = scanner.nextLine();
                            System.out.println("Enter Password: ");
                            String hospPass = scanner.nextLine();
                            System.out.println("Enter Name: ");
                            String hospName = scanner.nextLine();
                            System.out.println("Enter Gender: ");
                            String hospGender = scanner.nextLine();
                            System.out.println("Enter Role: ");
                            String hospRole = scanner.nextLine();
                            System.out.println("Enter Age: ");
                            int hospAge = scanner.nextInt();
                            if(hospID.charAt(0) == 'D'){
                                staff = new Doctor(hospID, hospPass,hospName, hospGender, Enums.Role.DOCTOR, hospAge);
                            }
                            else if(hospID.charAt(0) == 'P'){
                                staff = new Pharmacist(hospID, hospPass, hospName, hospGender, Enums.Role.PHARMACIST, hospAge);
                            }
                            staff_management = new StaffManagementController(staff);
                            staff_management.addStaff(staff);

                            break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("You are now logged out.");
                        LoginUI.loginMenu();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.next();
            }
        } while (option != 5);
    }
}



