package UI;

import Controller.AdministratorController;
import Entity.Administrator;
import Entity.Staff;
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
    private Administrator administrator;

    public ManageStaffUI(){
        scanner = new Scanner(System.in);
    }
    
    public void manageUserMenu(){

        int options=0;
        
        this.administratorController = new AdministratorController(administrator);

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
        int age_range = 0;
        String gender;
        boolean isValidGender;
        boolean isValidInput = true;
        String role;

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

                    case 1: scanner.nextLine();
                            do{
                            System.out.println("Enter Role of Staff:");
                            
                            role = scanner.nextLine().toUpperCase();
                            if(!(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Pharmacist") || role.equalsIgnoreCase("Administrator") || role.equalsIgnoreCase("Nurse"))){
                                System.out.println("Error: Invalid Role. Must be either Doctor, Pharmacist, Administrator or Nurse");  
                            }
                            else{
                                isValidInput = false;
                            }
                            }while(isValidInput);
                            ArrayList<Staff> filteredStaff = administratorController.filterBy(role);
                            staffListView.display(filteredStaff);
                            break;
                    case 2: 
                            scanner.nextLine();
                            do{
                            System.out.println("Enter Gender: ");
                            gender = scanner.nextLine().trim().toUpperCase();
                            isValidGender = false;
                            for (Enums.Gender g : Enums.Gender.values()) {
                                if (g.name().equals(gender)) {
                                    isValidGender = true;
                                    break;
                                }
                            }
                    
                            if (!isValidGender) {
                                System.out.println("Invalid gender. Please enter 'MALE' or 'FEMALE'or 'OTHERS'");
                            }

                        }while(!isValidGender);
                    
                            Enums.Gender gender_ = Enums.Gender.valueOf(gender);

                            ArrayList<Staff> filteredStaffByGender = administratorController.filterBy(gender_);
                            staffListView.display(filteredStaffByGender);
                            break;
                    case 3: 
                            do{
                            try{
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
                            if(age_range < 1 || age_range > 8){
                                System.out.println("Invalid age range. Please enter a number between 1 and 8!");
                            }
                            }catch(InputMismatchException e){
                                System.out.println("Invalid input. Please enter an integer!");
                                scanner.next();
                            }
                            }while(!(age_range >= 1 && age_range <= 8));
                        
                            ArrayList<Staff> filteredStaffByAge = administratorController.filterBy(age_range);
                            staffListView.display(filteredStaffByAge);
                            
                            break;
                    case 4: 
                            return;
                    default:
                            System.out.println("Invalid option. Please enter an integer between 1 and 4!");
                            break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter an integer!");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }while(options != 5); 
    }


}

    

    

    





    

