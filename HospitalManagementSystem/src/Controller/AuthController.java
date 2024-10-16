package Controller;

import Entity.*;
import Repository.*;

public class AuthController {

    public static User authenticate(String hospitalId, String password) {

        if (hospitalId.length() == 5 && hospitalId.substring(0, hospitalId.length() - 3).equals("P1")) {
            Patient patient = PatientRepository.get(hospitalId);
            return checkCredentials(patient, password);
        } else if (hospitalId.startsWith("D")) {
            Doctor doctor = DoctorRepository.get(hospitalId);
            return checkCredentials(doctor, password);
        } else if (hospitalId.startsWith("P")) {
            Pharmacist pharmacist = PharmacistRepository.get(hospitalId);
            return checkCredentials(pharmacist, password);
        } else if (hospitalId.startsWith("A")) {
            Administrator administrator = AdministratorRepository.get(hospitalId);
            return checkCredentials(administrator, password);
        }

        return null;
    }

    private static User checkCredentials(User user, String password) {
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals("") && password.equals("password")) {
            return user;
        } else if (!user.getPassword().equals("") && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static boolean checkDefaultPassword(User user) {
        if (user.getPassword().equals("")){
            return true;
        }
        else {
            return false;
        }
    }

    public static void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }
}
