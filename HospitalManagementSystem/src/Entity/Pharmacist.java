package src.Entity;

import src.UI.PharmacistUI;
import src.Enums.*;

/**
 * The Pharmacist class represents a pharmacist in the system, inheriting from the Staff class.
 * It includes functionality for displaying the pharmacist's menu.
 */
public class Pharmacist extends Staff {

    /**
     * Constructs a Pharmacist object with the specified details.
     *
     * @param hospitalId The hospital ID of the pharmacist.
     * @param password   The password of the pharmacist.
     * @param name       The name of the pharmacist.
     * @param gender     The gender of the pharmacist.
     * @param role       The role of the pharmacist.
     * @param age        The age of the pharmacist.
     */
    public Pharmacist(String hospitalId,String password, String name, String gender,Role role, int age) {
        super(hospitalId,password,name,gender,role,age);
    }

    /**
     * Displays the menu for the pharmacist in the system using the PharmacistUI class.
     *
     * @param currentUser The current user, which is assumed to be of type Pharmacist.
     */
    public void displayMenu(User currentUser) {
        PharmacistUI pharmacistUi = new PharmacistUI((Pharmacist) currentUser);
        pharmacistUi.displayMenu();
    }
}