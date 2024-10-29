package Entity;

import UI.NurseUI;

public class Nurse extends Staff{
    public Nurse(String hospitalId,String password, String name, String gender,Enums.Role role, int age) {
        super(hospitalId,password,name,gender,role,age);
    }

    public void displayMenu(User currentUser) {
        NurseUI nurseUi = new NurseUI((Nurse) currentUser);
        nurseUi.displayMenu();
    }
}
