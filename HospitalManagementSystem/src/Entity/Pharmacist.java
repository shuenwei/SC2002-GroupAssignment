package Entity;

import UI.PharmacistUI;

public class Pharmacist extends Staff {
    public Pharmacist(String hospitalId,String password, String name, String gender,Enums.Role role, int age) {
        super(hospitalId,password,name,gender,role,age);
    }

    public void displayMenu(User currentUser) {
    PharmacistUI pharmacistUi = new PharmacistUI((Pharmacist) currentUser);
        pharmacistUi.displayMenu();
    }
}