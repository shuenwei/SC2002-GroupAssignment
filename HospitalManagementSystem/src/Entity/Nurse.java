package src.Entity;

import src.UI.NurseUI;
import src.Enums.*;

/**
 * The Nurse class represents a nurse in the system, extending the Staff class.
 * It includes functionality for a nurse to access their menu in the user interface.
 */
public class Nurse extends Staff{

    /**
     * Constructs a Nurse object with the specified details.
     *
     * @param hospitalId The hospital ID of the nurse.
     * @param password   The password of the nurse.
     * @param name       The name of the nurse.
     * @param gender     The gender of the nurse.
     * @param role       The role of the nurse in the system.
     * @param age        The age of the nurse.
     */

    public Nurse(String hospitalId,String password, String name, String gender,Role role, int age) {

        super(hospitalId,password,name,gender,role,age);
    }

    /**
     * Displays the menu for the nurse in the system using the NurseUI class.
     *
     * @param currentUser The current user, which is assumed to be of type Nurse.
     */
    public void displayMenu(User currentUser) {
        NurseUI nurseUi = new NurseUI((Nurse) currentUser);
        nurseUi.displayMenu();
    }
}
