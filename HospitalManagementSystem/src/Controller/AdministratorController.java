package Controller;

import Entity.Administrator;
import Entity.Appointment;
import Entity.Doctor;
import Entity.Inventory;
import Entity.Medication;
import Entity.Nurse;
import Entity.Pharmacist;
import Entity.PrescribedMedication;
import Entity.Request;
import Entity.Staff;
import Entity.User;
import Enums.RequestStatus;
import Interface.IDisplayableView;
import Repository.AppointmentRepository;
import Repository.UserRepository;
import View.StaffView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class AdministratorController extends StaffController{

    // private Staff staff;
    private Administrator administrator;
    private Staff staff;
    private Scanner scanner;

    public AdministratorController(Administrator administrator){
        this.administrator = administrator;
        scanner = new Scanner(System.in);
    }

    public void addStaff(Staff staff){
        UserRepository.add(staff);
    }

    public void updateStaffAge(Staff staff, int age){
        staff.setAge(age);
    }

    public void updateStaffPassword(Staff staff, String password){
        staff.setPassword(password);
    }

    public void updateStaffName(Staff staff, String name){
        staff.setName(name);       
    }

    public void updateStaffGender(Staff staff, String gender){
        staff.setGender(gender);
    }

    public void removeStaff(String hospitalId){
        UserRepository.remove(hospitalId);
    }


    public void addStock(String medicineName, int quantity) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() + quantity;
            medicine.setStock(newStock);
            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
    }

    public void removeStock(String medicineName, int quantity) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() - quantity;
            medicine.setStock(newStock);
            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
    }

    private void replenishStock(String medicineName) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            System.out.printf("How many %s would you like to replenish?%n", medicineName);
            int num = scanner.nextInt();
            medicine.setStock(num);
        }
    }

    public void addNewMedicine(String medicineName, int stock, int lowStockThreshold) {
        Medication newMedicine = new Medication(medicineName, stock, lowStockThreshold);
        Inventory.add(newMedicine);
    }

    public void approveRequest(String requestedMedicine) {
        Request request = Inventory.getRequest(requestedMedicine);

        if (request != null) {
            replenishStock(requestedMedicine);
            request.setStatus(RequestStatus.FULFILLED);
            Inventory.removeRequest(requestedMedicine);

            System.out.printf("%s has been successfully replenished.", requestedMedicine);
            System.out.println();
        }
    }

    public void showAllAppointments(IDisplayableView<Appointment> appointmentView) {

        System.out.println();

        ArrayList<Appointment> appointments = AppointmentRepository.getAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println(" No appointments.");
        } else {
            int count = 1; 
            for (Appointment a : appointments) {
                System.out.println();
                System.out.println("[" + count + "]");
                    
                appointmentView.display(a);
                    
                
                if(a.getAppointmentOutcomeRecord() != null){
                    System.out.println("Type of Service          : " + a.getAppointmentOutcomeRecord().getTypeOfService());
                    System.out.println("Consultation Notes       : " + a.getAppointmentOutcomeRecord().getConsultationNotes());

                    ArrayList<PrescribedMedication> medications = a.getAppointmentOutcomeRecord().getPrescribedMedications();
                    System.out.println("The Prescribed Medication(s) is as follows: ");
                    for (PrescribedMedication medication : medications) {
                        System.out.println("Medication Name: " + medication.getMedicineName());
                    }
                    
                }
                    
                count++;
            }
            }
        System.out.println();


    }

    public ArrayList<Staff> filterBy(Enums.Role role) {
        ArrayList<Staff> filteredStaff = new ArrayList<>();

        ArrayList<Staff> staffs = UserRepository.getAllStaff();
        
        for (Staff s : staffs) {
            if (s.getRole() == role) {
                filteredStaff.add(s);
            }
        }
        
        return filteredStaff;
    }

    public ArrayList<Staff> filterBy(String gender) {
        ArrayList<Staff> filteredStaff = new ArrayList<>();

        ArrayList<Staff> staffs = UserRepository.getAllStaff();
        
        for (Staff s : staffs) {
            if (s.getGender().equalsIgnoreCase(gender)) {
                filteredStaff.add(s);
            }
        }
        
        return filteredStaff;
    }

    public ArrayList<Staff> filterBy(int option) {

        ArrayList<Staff> filteredStaff = new ArrayList<>();

        ArrayList<Staff> staffs = UserRepository.getAllStaff();

        try{
            switch(option){
                case 1: for (Staff s : staffs) {
                            if (s.getAge() >=0 && s.getAge() <= 20) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 2: for (Staff s : staffs) {
                            if (s.getAge() >20 && s.getAge() <= 30) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 3: for (Staff s : staffs) {
                            if (s.getAge() >30 && s.getAge() <= 40) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 4: for (Staff s : staffs) {
                            if (s.getAge() >40 && s.getAge() <= 50) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 5: for (Staff s : staffs) {
                            if (s.getAge() >50 && s.getAge() <= 60) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 6: for (Staff s : staffs) {
                            if (s.getAge() >60 && s.getAge() <= 70) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 7: for (Staff s : staffs) {
                            if (s.getAge() >70 && s.getAge() <= 80) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                case 8: for (Staff s : staffs) {
                            if (s.getAge() >80) {
                                filteredStaff.add(s);
                            }
                        }
                        return filteredStaff;
                default: System.out.println("Invalid option. Please enter an integer between 1 and 8!");

            }
                }catch (Exception e) {
                    System.out.println("Invalid input. Please enter an integer between 1 and 8!");
                }
        return null;
    }

    public void addStaff(){

        try{
            System.out.println("Enter Name: ");
            //scanner.nextLine();
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
            String hospRole = scanner.nextLine().toUpperCase();
            if (hospRole.isEmpty()) {
                throw new IllegalArgumentException("Gender cannot be empty.");
            }
            System.out.println("Enter Age: ");
            int hospAge = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (hospAge <= 0) {
                throw new IllegalArgumentException("Age must be greater than 0.");
            }
            
            if(hospRole.equals("DOCTOR")){
    
                ArrayList<Doctor> doctors = UserRepository.getAllDoctors();
        
                int max_d = 0;
                for(Doctor d : doctors){
                    if(Integer.parseInt(d.getHospitalId().substring(1))>max_d){
                        max_d = Integer.parseInt(d.getHospitalId().substring(1));
                    }
                }
                max_d++;
                staff = new Doctor("D" + String.format("%03d", max_d), "",hospName, hospGender, Enums.Role.DOCTOR, hospAge);
    
            }else if(hospRole.equals("PHARMACIST")){
                        
                ArrayList<Pharmacist> pharmacists = UserRepository.getAllPharmacists();

                int max_p = 0;
                for(Pharmacist p : pharmacists){
                    if(Integer.parseInt(p.getHospitalId().substring(1))>max_p){
                        max_p = Integer.parseInt(p.getHospitalId().substring(1));
                    }
                }
                max_p++;
                
                staff = new Pharmacist("P" + String.format("%03d", max_p), "", hospName, hospGender, Enums.Role.PHARMACIST, hospAge);

            }else if(hospRole.equals("ADMINISTRATOR")){
    
                ArrayList<Administrator> administrators = UserRepository.getAllAdministrators();
    
                int max_a = 0;
                for(Administrator a : administrators){
                    if(Integer.parseInt(a.getHospitalId().substring(1))>max_a){
                        max_a = Integer.parseInt(a.getHospitalId().substring(1));
                    }
                }
                max_a++;
                
                staff = new Administrator("A" + String.format("%03d", max_a), "", hospName, hospGender, Enums.Role.ADMINISTRATOR, hospAge);

            }else if(hospRole.equals("NURSE")){

                ArrayList<Nurse> nurses = UserRepository.getAllNurses();
    
                int max_n = 0;
                for(Nurse n : nurses){
                    if(Integer.parseInt(n.getHospitalId().substring(1))>max_n){
                        max_n = Integer.parseInt(n.getHospitalId().substring(1));
                    }
                }
                max_n++;
                
                staff = new Nurse("N" + String.format("%03d", max_n), "", hospName, hospGender, Enums.Role.NURSE , hospAge);
            
            }else{
                throw new IllegalArgumentException("Error: Invalid Role. Must be either Doctor, Pharmacist or Administrator");
            }

            addStaff((Staff) staff);
            System.out.println("The following staff has been added: ");
            StaffView staffView = new StaffView();
            staffView.display((Staff) staff);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type. Age must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());

        }
    }

    public void updateStaff(){

        int select;

        IDisplayableView<Staff> staffView = new StaffView();

        try{
            System.out.println("Enter Hospital ID: ");
            //scanner.nextLine();
            String hospID = scanner.nextLine();
            if (hospID.isEmpty()) {
                throw new IllegalArgumentException("Hospital ID cannot be empty.");
            }

            if(!(UserRepository.get(hospID) instanceof Administrator || UserRepository.get(hospID) instanceof Doctor || UserRepository.get(hospID) instanceof Pharmacist || UserRepository.get(hospID) instanceof Nurse)){
                System.out.println();
                throw new IllegalArgumentException("Error: Invalid Update. Staff not found!");
            }
               
            User staff_details = UserRepository.get(hospID);
            Staff staff_det = (Staff) staff_details;

            System.out.println("The current details of the staff are as follows: ");
            staffView.display(staff_det);

            System.out.println();
            System.out.println("Select an option:");
            System.out.println();
            System.out.println("(1) Change Password"); 
            System.out.println("(2) Change Name"); 
            System.out.println("(3) Change Gender");
            System.out.println("(4) Change Age");
            System.out.println("(5) Back");

            select = scanner.nextInt();

            switch(select){
                case 1: System.out.println("Enter Password: ");
                        scanner.nextLine();
                        String hospPass = scanner.nextLine();
                        if (hospPass.isEmpty()) {
                            throw new IllegalArgumentException("Password cannot be empty.");
                        }
                        updateStaffPassword(staff_det, hospPass);
                        System.out.println();
                        System.out.println("Your updated details are as follows: "); 
                        staffView.display(staff_det);
                        break;
                case 2: System.out.println("Enter Name: ");
                        scanner.nextLine();
                        String hospName = scanner.nextLine();
                        if (hospName.isEmpty()) {
                            throw new IllegalArgumentException("Name cannot be empty.");
                        }
                        updateStaffName(staff_det, hospName);
                        System.out.println();
                        System.out.println("Your updated details are as follows: "); 
                        staffView.display(staff_det);
                        break;
                case 3: System.out.println("Enter Gender: ");
                        scanner.nextLine();
                        String hospGender = scanner.nextLine();
                        if (hospGender.isEmpty()) {
                            throw new IllegalArgumentException("Gender cannot be empty.");
                        }
                        updateStaffGender(staff_det, hospGender);
                        System.out.println();
                        System.out.println("Your updated details are as follows: "); 
                        staffView.display(staff_det);
                        break;
                case 4: System.out.println("Enter Age: ");
                        scanner.nextLine();
                        int hospAge = scanner.nextInt();
                        updateStaffAge(staff_det, hospAge);
                        System.out.println();
                        System.out.println("Your updated details are as follows: "); 
                        staffView.display(staff_det);
                        break;
                case 5: return;
                default:
                        System.out.println("Invalid option. Please enter an integer between 1 and 5!");
                        break;
            
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid option. Please enter an integer between 1 and 5!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());

        }
    }

    public void removeStaff(){

        IDisplayableView<Staff> staffView = new StaffView();

        try{
            System.out.println("Enter Hospital ID: ");
            //scanner.nextLine();
            String hospID = scanner.nextLine();
            if (hospID.isEmpty()) {
                throw new IllegalArgumentException("Hospital ID cannot be empty.");
            }

            if(UserRepository.get(hospID) instanceof Administrator || UserRepository.get(hospID) instanceof Doctor || UserRepository.get(hospID) instanceof Pharmacist || UserRepository.get(hospID) instanceof Nurse){

                User removedStaff = UserRepository.get(hospID);
                System.out.println();
                System.out.println("The following staff is removed: ");
                staffView.display((Staff) removedStaff);    
                removeStaff(hospID);

            }
            else{
                System.out.println();
                throw new IllegalArgumentException("Error: Invalid Removal. Staff not found!");
            }

       } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        }

    }
    
}

