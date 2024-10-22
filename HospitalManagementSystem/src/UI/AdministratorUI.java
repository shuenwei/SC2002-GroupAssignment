package UI;

import Controller.AdministratorController;
import Entity.Administrator;
import Entity.Appointment;
import Entity.Doctor;
import Entity.Pharmacist;
import Entity.Staff;
import Entity.User;
import Interface.IStaffManagement;
import Repository.UserRepository;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdministratorUI {

    private Administrator administrator;
    private Staff staff;
    private IStaffManagement staff_management;
    Scanner scanner = new Scanner(System.in);

    public AdministratorUI(Administrator administrator) {
        this.administrator = administrator;
    }

    public void displayMenu(){
        
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
                    case 1: manageUserMenu();
                            break;
                    case 2: showAllDoctorsAppointment(); //incompleted
                            break;
                    case 3: manageInventoryMenu();
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("You are now logged out.");
                        LoginUI.loginMenu();
                        break;
                    case 6:
                        return;
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

    
    public void manageUserMenu(){
        
        int options;

        System.out.println();
        System.out.println("Select an option:");
        System.out.println();
        System.out.println("(1) View Hospital Staff");
        System.out.println("(2) Add Staff"); 
        System.out.println("(3) Update Staff"); 
        System.out.println("(4) Remove Staff"); 
        
        options = scanner.nextInt();

        switch(options){
            case 1: showStaffs();
                    System.out.println();
                    break;
            case 2: addStaff();
                    System.out.println();
                    break;
            case 3: updateStaff();
                    System.out.println();
                    break;
            case 4: removeStaff();
                    System.out.println();
                    break;
            default:
                    System.out.println("Invalid option. Please try again.");
                    break;
        }


    }

    public void addStaff(){

        try{
        System.out.println("Enter Name: ");
        scanner.nextLine();
        String hospName = scanner.nextLine();
        if (hospName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        System.out.println("Enter Gender: ");
        String hospGender = scanner.nextLine();
        if (hospGender.isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty.");
        }
        System.out.println("Enter Role: ");
        String hospRole = scanner.nextLine();
        if (hospRole.isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty.");
        }
        System.out.println("Enter Age: ");
        int hospAge = scanner.nextInt();
        System.out.println();

        switch (hospRole.charAt(0)) {

            case 'D':

                ArrayList<Doctor> doctors = UserRepository.getAllDoctors();
        
                int max_d = 0;
                for(Doctor d : doctors){
                    if(Integer.parseInt(d.getHospitalId().substring(1))>max_d){
                        max_d = Integer.parseInt(d.getHospitalId().substring(1));
                    }
                }
                max_d++;
                staff = new Doctor("D" + String.format("%03d", max_d), "",hospName, hospGender, Enums.Role.DOCTOR, hospAge);
    
                break;

            case 'P':
                
                ArrayList<Pharmacist> pharmacists = UserRepository.getAllPharmacists();

                int max_p = 0;
                for(Pharmacist p : pharmacists){
                    if(Integer.parseInt(p.getHospitalId().substring(1))>max_p){
                        max_p = Integer.parseInt(p.getHospitalId().substring(1));
                    }
                }
                max_p++;
                
                staff = new Pharmacist("P" + String.format("%03d", max_p), "", hospName, hospGender, Enums.Role.PHARMACIST, hospAge);
                break;
                
            case 'A':

                ArrayList<Administrator> administrators = UserRepository.getAllAdministrators();

                int max_a = 0;
                for(Administrator a : administrators){
                    if(Integer.parseInt(a.getHospitalId().substring(1))>max_a){
                        max_a = Integer.parseInt(a.getHospitalId().substring(1));
                    }
                }
                max_a++;
                
                staff = new Administrator("A" + String.format("%03d", max_a), "password", hospName, hospGender, Enums.Role.ADMINISTRATOR, hospAge);
                break;
            default:
                throw new IllegalArgumentException("Error: Invalid Hospital ID. Must start with 'D', 'P', or 'A'.");
        }

        staff_management = new AdministratorController(administrator);
        staff_management.addStaff((Staff) staff);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type. Age must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());

        }


    }

    public void showStaffs(){
        List <Staff> staffs = UserRepository.getAllStaff();
        System.out.println();
        for(Staff s : staffs){
            System.out.print(s.getName() + " ");
            System.out.println(s.getRole());
        }
    }

    public void removeStaff(){

        try{
        System.out.println("Enter Hospital ID: ");
        scanner.nextLine();
        String hospID = scanner.nextLine();
        if (hospID.isEmpty()) {
            throw new IllegalArgumentException("Hospital ID cannot be empty.");
        }

        staff_management = new AdministratorController(administrator);
        staff_management.removeStaff(hospID);
       } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        }

    }

    public void updateStaff(){

        int select;
        try{
        System.out.println("Enter Hospital ID: ");
        scanner.nextLine();
        String hospID = scanner.nextLine();
        if (hospID.isEmpty()) {
            throw new IllegalArgumentException("Hospital ID cannot be empty.");
        }

        User staff_details = UserRepository.get(hospID);
        staff_management = new AdministratorController(administrator);
        Staff staff_det = (Staff) staff_details;

        System.out.println();
        System.out.println("Select an option:");
        System.out.println();
        System.out.println("(1) Change Password"); 
        System.out.println("(2) Change Name"); 
        System.out.println("(3) Change Gender");
        System.out.println("(4) Change Age");

        select = scanner.nextInt();

        switch(select){
            case 1: System.out.println("Enter Password: ");
                    scanner.nextLine();
                    String hospPass = scanner.nextLine();
                    if (hospPass.isEmpty()) {
                        throw new IllegalArgumentException("Password cannot be empty.");
                    }
                    staff_management.updateStaffPassword(staff_det, hospPass);
                    System.out.println();
                    System.out.println("Your updated details are as follows"); 
                    System.out.println("Hospital ID : " + staff_details.getHospitalId());
                    System.out.println("Password    : " + staff_details.getPassword());
                    System.out.println("Name        : " + staff_details.getName());
                    System.out.println("Gender      : " + staff_details.getGender());
                    System.out.println("Age         : " + staff_det.getAge());
                    break;
            case 2: System.out.println("Enter Name: ");
                    scanner.nextLine();
                    String hospName = scanner.nextLine();
                    if (hospName.isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty.");
                    }
                    staff_management.updateStaffName(staff_det, hospName);
                    System.out.println();
                    System.out.println("Your updated details are as follows"); 
                    System.out.println("Hospital ID : " + staff_details.getHospitalId());
                    System.out.println("Password    : " + staff_details.getPassword());
                    System.out.println("Name        : " + staff_details.getName());
                    System.out.println("Gender      : " + staff_details.getGender());
                    System.out.println("Age         : " + staff_det.getAge());
                    break;
            case 3: System.out.println("Enter Gender: ");
                    scanner.nextLine();
                    String hospGender = scanner.nextLine();
                    if (hospGender.isEmpty()) {
                        throw new IllegalArgumentException("Gender cannot be empty.");
                    }
                    staff_management.updateStaffGender(staff_det, hospGender);
                    System.out.println();
                    System.out.println("Your updated details are as follows"); 
                    System.out.println("Hospital ID : " + staff_details.getHospitalId());
                    System.out.println("Password    : " + staff_details.getPassword());
                    System.out.println("Name        : " + staff_details.getName());
                    System.out.println("Gender      : " + staff_details.getGender());
                    System.out.println("Age         : " + staff_det.getAge());
                    break;
            case 4: System.out.println("Enter Age: ");
                    scanner.nextLine();
                    int hospAge = scanner.nextInt();
                    staff_management.updateStaffAge(staff_det, hospAge);
                    System.out.println();
                    System.out.println("Your updated details are as follows"); 
                    System.out.println("Hospital ID : " + staff_details.getHospitalId());
                    System.out.println("Password    : " + staff_details.getPassword());
                    System.out.println("Name        : " + staff_details.getName());
                    System.out.println("Gender      : " + staff_details.getGender());
                    System.out.println("Age         : " + staff_det.getAge());
                    break;
            default:
                    System.out.println("Invalid option. Please try again.");
                    break;
        
        }

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type. Age must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());

        }
    }

    public static void showAllDoctorsAppointment(){

        List <Doctor> doctors = UserRepository.getAllDoctors();
        System.out.println();

        for (Doctor s : doctors) {
        System.out.println(s.getName() + " has the following appointments:");
        System.out.println();
        List<Appointment> appointments = s.getAppointments();

        if (appointments.isEmpty()) {
            System.out.println(" No appointments.");
        } else {
            int count = 1; 
            for (Appointment a : appointments) {
                System.out.println(" " + count + ". " + a);
                count++;
                }
            }
        System.out.println();
        }

    }

    public void manageInventoryMenu(){
        
        int options;

        System.out.println();
        System.out.println("Select an option:");
        System.out.println();
        System.out.println("(1) View Inventory Level");
        System.out.println("(2) Add Medicine"); 
        System.out.println("(3) Update Medicine"); 
        System.out.println("(4) Remove Medicine"); 
        
        options = scanner.nextInt();

        switch(options){
            case 1: showStockLevel();
                    System.out.println();
                    break;
            case 2: addStaff();
                    System.out.println();
                    break;
            case 3: updateStaff();
                    System.out.println();
                    break;
            case 4: removeStaff();
                    System.out.println();
                    break;
            default:
                    System.out.println("Invalid option. Please try again.");
                    break;
        }

    }

    public void showStockLevel(){
        List <Staff> staffs = UserRepository.getAllStaff();
        System.out.println();
        for(Staff s : staffs){
            System.out.print(s.getName() + " ");
            System.out.println(s.getRole());
        }
    }


}



