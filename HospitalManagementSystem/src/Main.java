import UI.LoginUI;
import Util.*;
import View.CommonView;

public class Main {
    public static void main(String args[]) {

        try{
            DataInitialiser.initialisePatient();
            DataInitialiser.initialiseStaff();
            DataInitialiser.initialiseMedicine();
            do{
                CommonView.newPage();
                System.out.println("==================== Welcome to Hospital Management System =========================\n");
                CommonView.pressEnterToContinue();
                LoginUI.loginMenu();

            }while(true);

        }catch (Exception e) {
            
            // DataStore.saveStaffData();

            System.out.println("HMS crashed. Please restart the system.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}


