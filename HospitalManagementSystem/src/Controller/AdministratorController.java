package Controller;

import Entity.Administrator;
import Entity.Appointment;
import Entity.Doctor;
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
import Repository.InventoryRepository;
import Repository.UserRepository;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The AdministratorController class provides methods for managing staff, inventory, and appointments,
 * allowing the administrator to add, update, remove, and filter staff and inventory data.
 */
public class AdministratorController extends StaffController{

    private Administrator administrator;
    private Staff staff;
    private Scanner scanner;

    /**
     * Constructs an AdministratorController for the specified administrator and initializes input handling.
     *
     * @param administrator The administrator associated with this controller.
     */
    public AdministratorController(Administrator administrator){
        this.administrator = administrator;
        scanner = new Scanner(System.in);
    }

    /**
     * Adds a new staff member to the user repository.
     *
     * @param staff The staff member to be added.
     */
    public void addStaff(Staff staff){
        UserRepository.add(staff);
    }

    /**
     * Updates the age of a given staff member.
     *
     * @param staff The staff member whose age is to be updated.
     * @param age   The new age to set for the staff member.
     */
    public void updateStaffAge(Staff staff, int age){
        staff.setAge(age);
    }

    /**
     * Updates the password of a given staff member.
     *
     * @param staff    The staff member whose password is to be updated.
     * @param password The new password to set for the staff member.
     */
    public void updateStaffPassword(Staff staff, String password){
        staff.setPassword(password);
    }

    /**
     * Updates the name of a given staff member.
     *
     * @param staff The staff member whose name is to be updated.
     * @param name  The new name to set for the staff member.
     */
    public void updateStaffName(Staff staff, String name){
        staff.setName(name);       
    }

    /**
     * Updates the gender of a given staff member.
     *
     * @param staff  The staff member whose gender is to be updated.
     * @param gender The new gender to set for the staff member.
     */
    public void updateStaffGender(Staff staff, Enums.Gender gender){
        staff.setGender(gender);
    }

    /**
     * Removes a staff member from the user repository by hospital ID.
     *
     * @param hospitalId The hospital ID of the staff member to remove.
     */
    public void removeStaff(String hospitalId){
        UserRepository.remove(hospitalId);
    }

    /**
     * Adds stock quantity to an existing medication in the inventory.
     *
     * @param medicineName The name of the medication to add stock to.
     * @param quantity     The amount of stock to add.
     */
    public void addStock(String medicineName, int quantity) {
        Medication medicine = InventoryRepository.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() + quantity;
            medicine.setStock(newStock);
            System.out.println();
            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
    }

    /**
     * Removes stock quantity from an existing medication in the inventory.
     *
     * @param medicineName The name of the medication to remove stock from.
     * @param quantity     The amount of stock to remove.
     */
    public void removeStock(String medicineName, int quantity) {
        Medication medicine = InventoryRepository.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() - quantity;
            medicine.setStock(newStock);
            System.out.println();
            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
    }

    /**
     * Replenishes stock for a specific medication by a specified quantity.
     *
     * @param medicineName The name of the medication to replenish.
     */
    private void replenishStock(String medicineName) {
        Medication medicine = InventoryRepository.get(medicineName);
        int current_stock = medicine.getStock();
        if (medicine != null) {
            System.out.printf("How many %s would you like to replenish?%n", medicineName);
            int num = scanner.nextInt();
            int total_stock = current_stock + num;
            medicine.setStock(total_stock);
        }
    }

    /**
     * Adds a new medicine to the inventory with the specified stock and threshold values.
     *
     * @param medicineName     The name of the new medicine.
     * @param stock            The initial stock quantity of the new medicine.
     * @param lowStockThreshold The threshold level to trigger restocking.
     */
    public void addNewMedicine(String medicineName, int stock, int lowStockThreshold) {
        Medication newMedicine = new Medication(medicineName, stock, lowStockThreshold);
        InventoryRepository.add(newMedicine);
    }

    /**
     * Sets a new stock threshold for an existing medication in the inventory.
     *
     * @param medicineName The name of the medication.
     * @param threshold    The new threshold to set for restocking alerts.
     */
    public void setThresholdStock(String medicineName, int threshold) {
        Medication medicine = InventoryRepository.get(medicineName);
        if (medicine != null) {
            medicine.setStockThreshold(threshold);
            System.out.println();
            System.out.printf("Successfully updated stock threshold for %s%n", medicineName);
        }
    }

    /**
     * Approves a replenishment request for a specific medication and updates its status.
     *
     * @param requestedMedicine The name of the requested medication.
     */
    public void approveRequest(String requestedMedicine) {
        Request request = InventoryRepository.getRequest(requestedMedicine);

        if (request != null) {
            replenishStock(requestedMedicine);
            request.setStatus(RequestStatus.FULFILLED);

            System.out.printf("%s has been successfully replenished.", requestedMedicine);
            System.out.println();
        }
    }

    /**
     * Displays all appointments in the system, including their details and prescribed medications.
     *
     * @param appointmentView The view interface for displaying appointment details.
     */
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

    /**
     * Filters staff members by role and returns a list of matching staff.
     *
     * @param role The role to filter staff by.
     * @return A list of staff members matching the specified role.
     */
    public ArrayList<Staff> filterBy(String role) {
        ArrayList<Staff> filteredStaff = new ArrayList<>();
        ArrayList<Staff> staffs = UserRepository.getAllStaff();

        for (Staff s : staffs) {
            if (s.getRole().toString().equalsIgnoreCase(role)) {
                filteredStaff.add(s);
            }
        }
        
        return filteredStaff;
    }

    /**
     * Filters staff members by gender and returns a list of matching staff.
     *
     * @param gender The gender to filter staff by.
     * @return A list of staff members matching the specified gender.
     */
    public ArrayList<Staff> filterBy(Enums.Gender gender) {
        ArrayList<Staff> filteredStaff = new ArrayList<>();
        ArrayList<Staff> staffs = UserRepository.getAllStaff();

        for (Staff s : staffs) {
            if (s.getGender().toString().equalsIgnoreCase(gender.toString())) {
                filteredStaff.add(s);
            }
        }
        
        return filteredStaff;
    }

    /**
     * Filters staff members by age range and returns a list of matching staff.
     *
     * @param option The age range option to filter staff by.
     * @return A list of staff members matching the specified age range.
     */
    public ArrayList<Staff> filterBy(int option) {
        ArrayList<Staff> filteredStaff = new ArrayList<>();
        ArrayList<Staff> staffs = UserRepository.getAllStaff();

        try {
            switch(option){
                case 1:
                    for (Staff s : staffs) {
                        if (s.getAge() >= 0 && s.getAge() <= 20) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 2:
                    for (Staff s : staffs) {
                        if (s.getAge() > 20 && s.getAge() <= 30) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 3:
                    for (Staff s : staffs) {
                        if (s.getAge() > 30 && s.getAge() <= 40) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 4:
                    for (Staff s : staffs) {
                        if (s.getAge() > 40 && s.getAge() <= 50) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 5:
                    for (Staff s : staffs) {
                        if (s.getAge() > 50 && s.getAge() <= 60) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 6:
                    for (Staff s : staffs) {
                        if (s.getAge() > 60 && s.getAge() <= 70) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 7:
                    for (Staff s : staffs) {
                        if (s.getAge() > 70 && s.getAge() <= 80) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                case 8:
                    for (Staff s : staffs) {
                        if (s.getAge() > 80) {
                            filteredStaff.add(s);
                        }
                    }
                    return filteredStaff;
                default:
                    System.out.println("Invalid option. Please enter an integer between 1 and 8!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter an integer between 1 and 8!");
        }
        return null;
    }

    /**
     * Adds a new staff member based on provided details and displays the new staff.
     *
     * @param staffView The view interface to display the added staff.
     */
    public void addStaff(IDisplayableView<Staff> staffView) {
        try {
            System.out.println("Enter Name: ");
            String hospName = scanner.nextLine();
            if (hospName.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty.");
            }
            System.out.println("Enter Gender: ");
            String hospGender = scanner.nextLine();
            if (hospGender.isEmpty()) {
                throw new IllegalArgumentException("Gender cannot be empty.");
            }
            Enums.Gender gender = Enums.Gender.valueOf(hospGender.toUpperCase());
            System.out.println("Enter Role: ");
            String hospRole = scanner.nextLine().toUpperCase();
            if (hospRole.isEmpty()) {
                throw new IllegalArgumentException("Role cannot be empty.");
            }
            System.out.println("Enter Age: ");
            int hospAge = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (hospAge <= 0) {
                throw new IllegalArgumentException("Age must be greater than 0.");
            }
            
            if(hospRole.equals("DOCTOR")) {
                ArrayList<Doctor> doctors = UserRepository.getAllDoctors();
                int max_d = 0;
                for(Doctor d : doctors){
                    if(Integer.parseInt(d.getHospitalId().substring(1)) > max_d){
                        max_d = Integer.parseInt(d.getHospitalId().substring(1));
                    }
                }
                max_d++;
                staff = new Doctor("D" + String.format("%03d", max_d), "", hospName, gender, Enums.Role.DOCTOR, hospAge);
    
            } else if(hospRole.equals("PHARMACIST")) {
                ArrayList<Pharmacist> pharmacists = UserRepository.getAllPharmacists();
                int max_p = 0;
                for(Pharmacist p : pharmacists){
                    if(Integer.parseInt(p.getHospitalId().substring(1)) > max_p){
                        max_p = Integer.parseInt(p.getHospitalId().substring(1));
                    }
                }
                max_p++;
                staff = new Pharmacist("P" + String.format("%03d", max_p), "", hospName, gender, Enums.Role.PHARMACIST, hospAge);

            } else if(hospRole.equals("ADMINISTRATOR")) {
                ArrayList<Administrator> administrators = UserRepository.getAllAdministrators();
                int max_a = 0;
                for(Administrator a : administrators){
                    if(Integer.parseInt(a.getHospitalId().substring(1)) > max_a){
                        max_a = Integer.parseInt(a.getHospitalId().substring(1));
                    }
                }
                max_a++;
                staff = new Administrator("A" + String.format("%03d", max_a), "", hospName, gender, Enums.Role.ADMINISTRATOR, hospAge);

            } else if(hospRole.equals("NURSE")) {
                ArrayList<Nurse> nurses = UserRepository.getAllNurses();
                int max_n = 0;
                for(Nurse n : nurses){
                    if(Integer.parseInt(n.getHospitalId().substring(1)) > max_n){
                        max_n = Integer.parseInt(n.getHospitalId().substring(1));
                    }
                }
                max_n++;
                staff = new Nurse("N" + String.format("%03d", max_n), "", hospName, gender, Enums.Role.NURSE, hospAge);
            } else {
                throw new IllegalArgumentException("Error: Invalid Role. Must be either Doctor, Pharmacist, Administrator, or Nurse.");
            }

            addStaff((Staff) staff);
            System.out.println("The following staff has been added: ");
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

    /**
     * Updates an existing staff member's information based on user input.
     *
     * @param staffView The view interface to display the updated staff details.
     */
    public void updateStaff(IDisplayableView<Staff> staffView) {
        int select;
        try {
            System.out.println("Enter Hospital ID: ");
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
            System.out.println("(1) Change Password"); 
            System.out.println("(2) Change Name"); 
            System.out.println("(3) Change Gender");
            System.out.println("(4) Change Age");
            System.out.println("(5) Back");

            select = scanner.nextInt();

            switch(select) {
                case 1:
                    System.out.println("Enter Password: ");
                    scanner.nextLine();
                    String hospPass = scanner.nextLine();
                    if (hospPass.isEmpty()) {
                        throw new IllegalArgumentException("Password cannot be empty.");
                    }
                    updateStaffPassword(staff_det, hospPass);
                    System.out.println("Your updated details are as follows: "); 
                    staffView.display(staff_det);
                    break;
                case 2:
                    System.out.println("Enter Name: ");
                    scanner.nextLine();
                    String hospName = scanner.nextLine();
                    if (hospName.isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty.");
                    }
                    updateStaffName(staff_det, hospName);
                    System.out.println("Your updated details are as follows: "); 
                    staffView.display(staff_det);
                    break;
                case 3:
                    System.out.println("Enter Gender: ");
                    scanner.nextLine();
                    String hospGender = scanner.nextLine();
                    Enums.Gender gender = Enums.Gender.valueOf(hospGender.toUpperCase());
                    if (hospGender.isEmpty()) {
                        throw new IllegalArgumentException("Gender cannot be empty.");
                    }
                    updateStaffGender(staff_det, gender);
                    System.out.println("Your updated details are as follows: "); 
                    staffView.display(staff_det);
                    break;
                case 4:
                    System.out.println("Enter Age: ");
                    scanner.nextLine();
                    int hospAge = scanner.nextInt();
                    updateStaffAge(staff_det, hospAge);
                    System.out.println("Your updated details are as follows: "); 
                    staffView.display(staff_det);
                    break;
                case 5:
                    return;
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

    /**
     * Removes a staff member by hospital ID and displays the details of the removed staff member.
     *
     * @param staffView The view interface to display the removed staff details.
     */
    public void removeStaff(IDisplayableView<Staff> staffView) {
        try {
            System.out.println("Enter Hospital ID: ");
            String hospID = scanner.nextLine();
            if (hospID.isEmpty()) {
                throw new IllegalArgumentException("Hospital ID cannot be empty.");
            }

            if(UserRepository.get(hospID) instanceof Administrator || UserRepository.get(hospID) instanceof Doctor || UserRepository.get(hospID) instanceof Pharmacist || UserRepository.get(hospID) instanceof Nurse) {
                User removedStaff = UserRepository.get(hospID);
                System.out.println("The following staff is removed: ");
                staffView.display((Staff) removedStaff); 
                if(removedStaff instanceof Doctor){
                    for(Appointment a : ((Doctor) removedStaff).getAppointments()){
                        
                        if(a.getStatus().equals(Enums.AppointmentStatus.PENDING) || a.getStatus().equals(Enums.AppointmentStatus.CONFIRMED)){
                            a.setStatus(Enums.AppointmentStatus.CANCELLED);
                        }
                        
                    }
                }
                removeStaff(hospID);
            } else {
                throw new IllegalArgumentException("Error: Invalid Removal. Staff not found!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
