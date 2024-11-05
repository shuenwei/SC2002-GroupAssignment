package UI;

import Controller.AdministratorController;
import Entity.Staff;
import Enums.Role;
import Interface.IListDisplayableView;
import View.CommonView;
import View.StaffListView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class ManageStaffUI {
    private Scanner scanner;
    private AdministratorController administratorController;
    private Staff staff;

    public ManageStaffUI(AdministratorController administratorController){
        scanner = new Scanner(System.in);
        this.administratorController = administratorController;
    }
    
    public void manageUserMenu(){
        int options=0;
        do{
            try{
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

                switch(options){
                    case 1: filterStaff();
                            System.out.println();
                            break;
                    case 2: administratorController.addStaff();
                            System.out.println();
                            break;
                    case 3: administratorController.updateStaff();
                            System.out.println();
                            break;
                    case 4: administratorController.removeStaff();
                            System.out.println();
                            break;
                    case 5: return;
                    default:
                            System.out.println("Invalid option. Please enter an integer between 1 and 5!");
                            break;
                } 
            }catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter an integer between 1 and 5!");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        }while(options != 5); 

    }



    public void filterStaff(){
        int options=0;

        IListDisplayableView<Staff> staffListView = new StaffListView();

        do{
            try{
                CommonView.newPage();
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Hospital Staff by Role");
                System.out.println("(2) View Hospital Staff by Gender"); 
                System.out.println("(3) View Hospital Staff by Age"); 
                System.out.println("(4) Back");
                
                options = scanner.nextInt();

                switch(options){
                    case 1: try{
                            System.out.println("Enter Role of Staff:");
                            scanner.nextLine();
                            Role role = Role.valueOf(scanner.nextLine().toUpperCase());
                            if(!(role.equals(Role.DOCTOR) || role.equals(Role.PHARMACIST) || role.equals(Role.ADMINISTRATOR) || role.equals(Role.NURSE))){
                                throw new Exception("Error: Invalid Role. Must be either Doctor, Pharmacist or Administrator");  
                            }
                            ArrayList<Staff> filteredStaff = administratorController.filterBy(role);
                            staffListView.display(filteredStaff);
                            } catch(Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                            break;
                    case 2: try{
                            System.out.println("Enter Gender: ");
                            scanner.nextLine();
                            String gender = scanner.nextLine();
                            if(gender.isEmpty()){
                                throw new IllegalArgumentException("Role cannot be empty.");
                            }
                            if(gender.matches(".*\\d.*")){
                                throw new IllegalArgumentException("Error: Invalid! Please input a string.");
                            }
                            ArrayList<Staff> filteredStaff = administratorController.filterBy(gender);
                            staffListView.display(filteredStaff);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                            break;
                    case 3: try{
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
                            int age_range = scanner.nextInt();
                            if(age_range < 1 || age_range > 8){
                                throw new IllegalArgumentException("Invalid range of age. Please enter an integer between 1 and 8!");
                            }
                            ArrayList <Staff> filteredStaff = administratorController.filterBy(age_range);
                            staffListView.display(filteredStaff);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("An unexpected error occurred: " + e.getMessage());
                            }
                            break;
                    case 4: return;
                    default:
                            System.out.println("Invalid option. Please enter an integer between 1 and 4!");
                            break;
                } 
            }catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter an integer between 1 and 4!");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        }while(options != 5); 

    }

    

    





    
}
