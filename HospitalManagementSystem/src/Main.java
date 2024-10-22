import UI.LoginUI;
import Util.*;
import View.CommonView;

public class Main {
    public static void main(String args[]) {

        boolean flag = true;

        try{
            DataInitialiser.initialisePatient();
            DataInitialiser.initialiseStaff();
            DataInitialiser.initialiseMedicine();
            System.out.println("====================Welcome to Hospital Management System=========================");
            CommonView.pressEnterToContinue();
            do{
                LoginUI.loginMenu();

            }while(flag);

        }catch (Exception e) {
            
            // DataStore.saveStaffData();

            System.out.println("HMS crashed. Please restart the system.");
            System.out.println("Error: " + e.getMessage());
        }
    
           
    
}
}

