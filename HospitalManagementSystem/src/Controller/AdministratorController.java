package Controller;

import Entity.Administrator;
import Entity.Inventory;
import Entity.Medication;
import Entity.Request;
import Entity.Staff;
import Enums.RequestStatus;
import Repository.UserRepository;

public class AdministratorController extends StaffController{

    // private Staff staff;
    private Administrator administrator;

    public AdministratorController(Administrator administrator){
        this.administrator = administrator;
    }

    public void addStaff(Staff staff){
        UserRepository.add(staff);

    }

    public void updateStaffAge(Staff staff, int age){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setAge(age);
        
    }

    public void updateStaffPassword(Staff staff, String password){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setPassword(password);
        
    }

    public void updateStaffName(Staff staff, String name){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setName(name);
        
    }

    public void updateStaffGender(Staff staff, String gender){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setGender(gender);
        
    }

    public void removeStaff(String hospitalId){
        UserRepository.remove(hospitalId);
    }


    // StaffManagementController staffManagementController = new StaffManagementController

    public void addStock(String medicineName, int quantity) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() + quantity;
            medicine.setStock(newStock);
    
            // if (newStock > medicine.getStockThreshold() && medicine.getIsLowStock() == true) {
            //     medicine.setIsLowStock(false);
            // }

            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
    }

    public void removeStock(String medicineName, int quantity) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            int newStock = medicine.getStock() - quantity;
            medicine.setStock(newStock);
    
            // if (newStock <= medicine.getStockThreshold() && medicine.getIsLowStock() == false) {
            //     medicine.setIsLowStock(true);
            // }

            System.out.printf("Successfully updated stock level for %s%n", medicineName);
        }
    }

    private void replenishStock(String medicineName) {
        Medication medicine = Inventory.get(medicineName);
        if (medicine != null) {
            medicine.setStock(medicine.getStockThreshold());
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
        }
    }
}
