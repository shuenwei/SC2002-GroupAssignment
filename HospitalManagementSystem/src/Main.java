import UI.LoginUI;
import Util.*;
import View.CommonView;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        DataInitialiser.initialiseAll();

        while (true) {
            try{
                CommonView.newPage();
                System.out.println("Welcome to Hospital Management System");
                System.out.println("(1) Login");
                System.out.println("(2) Shutdown");
                choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        LoginUI.loginMenu();
                        break;
                    case 2:
                        System.out.println("Exiting the system...");
                        DataStore.saveAll();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input. Please enter an integer of either 1 or 2!");
                        break;
                    }

            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter an integer of either 1 or 2!");
                scanner.next(); 
            }catch (Exception e) {
                System.out.println("HMS crashed. Please restart the system.");
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}


