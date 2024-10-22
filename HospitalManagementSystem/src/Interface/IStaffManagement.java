package Interface;

import Entity.*;


public interface IStaffManagement{

    public void addStaff(Staff staff);

    public void updateStaffAge(int age);

    public void updateStaffName(String name);

    public void updateStaffGender(String gender);

    public void updateStaffPassword(String password);

    public void removeStaff(String hospitalId);

    // void List<Staff> listStaff();

}
