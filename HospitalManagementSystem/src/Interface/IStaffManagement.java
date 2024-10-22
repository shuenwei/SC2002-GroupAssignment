package Interface;

import Entity.*;


public interface IStaffManagement{

    public void addStaff(Staff staff);

    public void updateStaffAge(Staff staff, int age);

    public void updateStaffName(Staff staff, String name);

    public void updateStaffGender(Staff staff, String gender);

    public void updateStaffPassword(Staff staff, String password);

    public void removeStaff(String hospitalId);

    // void List<Staff> listStaff();

}
