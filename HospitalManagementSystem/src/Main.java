import UI.LoginUI;
import Util.DataInitialiser;

public class Main {
    public static void main(String args[]) {
        DataInitialiser.initialisePatient();
        DataInitialiser.initialiseStaff();
        DataInitialiser.initialiseMedicine();
        LoginUI.loginMenu();
    }
}
