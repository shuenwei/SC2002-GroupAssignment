package Controller;

import Entity.Administrator;
import Entity.Staff;
import Interface.IStaffManagement;
import Repository.UserRepository;

public class AdministratorController implements IStaffManagement{

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


}
