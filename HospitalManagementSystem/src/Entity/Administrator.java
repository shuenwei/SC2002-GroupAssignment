package Entity;

import UI.AdministratorUI;

public class Administrator extends Staff {
    public Administrator(String hospitalId,String password, String name, String gender,Enums.Role role, int age) {
        super(hospitalId,password,name,gender,role,age);
    }

    public void displayMenu(User currentUser) {
        AdministratorUI administratorUi = new AdministratorUI((Administrator) currentUser);
        administratorUi.displayMenu();
    }
}