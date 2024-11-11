package src.UI;

import src.Controller.AdministratorController;
import src.Entity.Administrator;
import src.Entity.Staff;
import src.Enums.*;
import src.Interface.IDisplayableView;
import src.Interface.IListDisplayableView;
import src.View.CommonView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageStaffUI {
    private Scanner scanner;
    private Staff staff;
    private Administrator administrator;

    /**
     * Constructs a ManageStaffUI with an initialized Scanner for handling user input.
     */
    public ManageStaffUI(){
        scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the main menu for managing hospital staff.
     * Allows the administrator to view, add, update, and remove staff, or go back to the previous menu.
     *
     * @param administratorController The controller used to manage staff-related operations.
     * @param staffView The view interface for displaying a single staff member's information.
     * @param staffListView The view interface for displaying a list of staff members.
     */
    public void manageUserMenu(AdministratorController administratorController, IDisplayableView<Staff> staffView, IListDisplayableView<Staff> staffListView){
        int options = 0;

        do {
            try {
                CommonView.newPage();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Hospital Staff");
                System.out.println("(2) Add Staff"); 
                System.out.println("(3) Update Staff"); 
                System.out.println("(4) Remove Staff"); 
                System.out.println("(5) Back");
                
                options = scanner.nextInt();

                switch(options) {
                    case 1:
                        filterStaff(administratorController, staffListView);
                        System.out.println();
                        break;
                    case 2:
                        administratorController.addStaff(staffView);
                        System.out.println();
                        break;
                    case 3:
                        administratorController.updateStaff(staffView);
                        System.out.println();
                        break;
                    case 4:
                        administratorController.removeStaff(staffView);
                        System.out.println();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option. Please enter an integer between 1 and 5!");
                        break;
                } 
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter an integer between 1 and 5!");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while(options != 5); 
    }

    /**
     * Provides filtering options for viewing hospital staff by role, gender, or age.
     * Prompts the user to select a filtering criterion and then displays the filtered list of staff.
     *
     * @param administratorController The controller used to manage staff-related actions, including filtering.
     * @param staffListView The view interface for displaying the filtered list of staff members.
     */
    public void filterStaff(AdministratorController administratorController, IListDisplayableView<Staff> staffListView) {
        int options = 0;
        int age_range = 0;
        String gender;
        boolean isValidGender;
        boolean isValidInput = true;
        String role;

        do {
            try {
                CommonView.newPage();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Hospital Staff by Role");
                System.out.println("(2) View Hospital Staff by Gender"); 
                System.out.println("(3) View Hospital Staff by Age"); 
                System.out.println("(4) Back");
                
                options = scanner.nextInt();

                switch(options) {
                    case 1:
                        scanner.nextLine();
                        do {
                            System.out.println("Enter Role of Staff:");
                            role = scanner.nextLine().toUpperCase();
                            if (!(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Pharmacist") || 
                                  role.equalsIgnoreCase("Administrator") || role.equalsIgnoreCase("Nurse"))) {
                                System.out.println("Error: Invalid Role. Must be either Doctor, Pharmacist, Administrator, or Nurse");  
                            } else {
                                isValidInput = false;
                            }
                        } while(isValidInput);
                        ArrayList<Staff> filteredStaff = administratorController.filterBy(role);
                        staffListView.display(filteredStaff);
                        break;
                    case 2:
                        scanner.nextLine();
                        do {
                            System.out.println("Enter Gender: ");
                            gender = scanner.nextLine().trim().toUpperCase();
                            isValidGender = false;
                            for (Gender g : Gender.values()) {
                                if (g.name().equals(gender)) {
                                    isValidGender = true;
                                    break;
                                }
                            }
                            if (!isValidGender) {
                                System.out.println("Invalid gender. Please enter 'MALE' or 'FEMALE' or 'OTHERS'");
                            }
                        } while(!isValidGender);

                        Gender gender_ = Gender.valueOf(gender);
                        ArrayList<Staff> filteredStaffByGender = administratorController.filterBy(gender_);
                        staffListView.display(filteredStaffByGender);
                        break;
                    case 3:
                        do {
                            try {
                                CommonView.newPage();
                                System.out.println("Enter range of age: ");
                                System.out.println("(1) < 20  years");
                                System.out.println("(2) 20-30 years");
                                System.out.println("(3) 30-40 years");
                                System.out.println("(4) 40-50 years");
                                System.out.println("(5) 50-60 years");
                                System.out.println("(6) 60-70 years");
                                System.out.println("(7) 70-80 years");
                                System.out.println("(8) > 80  years");
                                scanner.nextLine();
                                age_range = scanner.nextInt();
                                if(age_range < 1 || age_range > 8) {
                                    System.out.println("Invalid age range. Please enter a number between 1 and 8!");
                                }
                            } catch(InputMismatchException e) {
                                System.out.println("Invalid input. Please enter an integer!");
                                scanner.next();
                            }
                        } while(!(age_range >= 1 && age_range <= 8));

                        ArrayList<Staff> filteredStaffByAge = administratorController.filterBy(age_range);
                        staffListView.display(filteredStaffByAge);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid option. Please enter an integer between 1 and 4!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter an integer!");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while(options != 5); 
    }
}
