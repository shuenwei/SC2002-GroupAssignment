package src.UI;

import src.Controller.AuthController;
import src.Entity.*;
import src.View.CommonView;
import java.util.Scanner;

/**
 * The LoginUI class provides a user interface for logging into the system.
 * It includes methods for handling user authentication and updating default passwords.
 */
public class LoginUI {

    /**
     * Displays the login menu where users enter their hospital ID and password.
     * If the credentials are incorrect, prompts the user to try again. If the login is successful,
     * checks for a default password and prompts for a change if necessary.
     */
    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (currentUser == null) {
            CommonView.newPage();
            System.out.print("Enter Hospital ID: ");
            String hospitalId = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            currentUser = AuthController.authenticate(hospitalId, password);

            if (currentUser == null) {
                System.out.println("Invalid HospitalID or Password.");
            }
            else {
                if (AuthController.checkDefaultPassword(currentUser) == true) {
                    changeDefaultPassword(currentUser);             
                }
                else {
                    currentUser.displayMenu(currentUser);
                }
            }
        }
    }

    /**
     * Prompts the user to change their default password. Ensures the new password
     * matches a confirmation entry and updates the password if confirmed.
     *
     * @param currentUser The user who is required to change their default password.
     */
    private static void changeDefaultPassword(User currentUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Your account is using a default password. You are required to change your password.");
        while (true) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Re-enter new password to confirm: ");
            String reenterNewPassword = scanner.nextLine();

            if (newPassword.equals(reenterNewPassword)) {
                AuthController.changeDefaultPassword(currentUser, newPassword);
                System.out.println("Password changed successfully. Please re-login to access the system.");
                break;
            }
            else {
                System.out.println("Your new passwords do not match.");
            }
        }
    }
}
