package Controller;

import Entity.*;
import Repository.*;

public class AuthController {

    public static User authenticate(String hospitalId, String password) {
        User user = UserRepository.get(hospitalId);
        if (user == null) {
            return null;
        }

        if (user.getPassword().equals("") && password.equals("password")) {
            return user;
        }
        else if (!user.getPassword().equals("") && user.getPassword().equals(password)) {
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

    public static void changeDefaultPassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }
}
