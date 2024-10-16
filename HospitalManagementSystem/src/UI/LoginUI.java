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
                    System.out.println("Your account is using a default password. You are required to change your password. ");
                    System.out.print("Enter New Password: ");
                    String newPassword = scanner.nextLine();
                    AuthController.changePassword(currentUser, newPassword);
                }
                System.out.println("Hello, " + currentUser.getName());
            }
        }
    }
}
