package Controller;

import Entity.Staff;
import Interface.IStaffManagement;
import Repository.UserRepository;

public class StaffManagementController implements IStaffManagement{

    private Staff staff;

    public StaffManagementController(Staff staff){
        this.staff = staff;
    }

    public void addStaff(Staff staff){
        UserRepository.add(staff);

    }

    public void updateStaffAge(int age){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setAge(age);
        
    }

    public void updateStaffPassword(String password){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setPassword(password);
        
    }

    public void updateStaffName(String name){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setName(name);;
        
    }

    public void updateStaffGender(String gender){
        // Staff staff = (Staff) UserRepository.get(hospitalId);
        staff.setGender(gender);;
        
    }

    public void removeStaff(String hospitalId){
        UserRepository.remove(hospitalId);
    }

    
}
