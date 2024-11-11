package src.Entity;

import src.Enums.*;
import src.UI.AdministratorUI;

/**
 * The Administrator class represents a specific type of staff member, inheriting from the Staff class.
 * Administrators have additional functionality to display the menu specific to their role.
 */
public class Administrator extends Staff {

    /**
     * Constructs an Administrator object with the provided details.
     *
     * @param hospitalId The hospital ID of the administrator.
     * @param password   The password of the administrator.
     * @param name       The name of the administrator.
     * @param gender     The gender of the administrator.
     * @param role       The role of the administrator (should be 'ADMINISTRATOR').
     * @param age        The age of the administrator.
     */

    public Administrator(String hospitalId,String password, String name, Gender gender,Role role, int age) {

        super(hospitalId,password,name,gender,role,age);
    }

    /**
     * Displays the menu for the administrator. This method invokes the user interface for the administrator's
     * menu, where they can choose different actions.
     *
     * @param currentUser The current user, which is expected to be an Administrator object.
     */
    public void displayMenu(User currentUser) {
        AdministratorUI administratorUi = new AdministratorUI((Administrator) currentUser);
        administratorUi.displayMenu();
    }
}