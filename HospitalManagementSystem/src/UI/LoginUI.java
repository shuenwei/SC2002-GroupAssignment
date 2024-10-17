package UI;

import java.util.Scanner;

import Controller.AuthController;
import Entity.*;

public class LoginUI {
    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (currentUser == null) {

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
                
                if (currentUser instanceof Patient) {
                    PatientUI patientUi = new PatientUI((Patient) currentUser);
                    patientUi.displayMenu();
                }
            }
        }
    }

    private static void changeDefaultPassword(User currentUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Your account is using a default password. You are required to change your password. ");
        while (true) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Re-enter new password to confirm: ");
            String reenterNewPassword = scanner.nextLine();

            if (newPassword.equals(reenterNewPassword)) {
                AuthController.changeDefaultPassword(currentUser, newPassword);
                System.out.println("Password changed successfully.");
                break;
            }
            else {
                System.out.println("Your new passwords do not match.");
            }
        }
    }
}
