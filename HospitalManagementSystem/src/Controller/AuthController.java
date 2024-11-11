package src.Controller;

import src.Entity.*;
import src.Repository.*;

/**
 * The {@code AuthController} class handles user authentication and password management.
 * It provides methods for authenticating users, checking if they are using a default password,
 * and updating passwords when needed.
 */
public class AuthController {

    /**
     * Authenticates a user based on their hospital ID and password.
     * The method checks if the user exists in the repository and verifies the password.
     * If the user has a default empty password, it only requires a default "password" to authenticate.
     *
     * @param hospitalId the hospital ID of the user attempting to authenticate
     * @param password   the password provided for authentication
     * @return the authenticated {@code User} object if credentials are valid, {@code null} otherwise
     */
    public static User authenticate(String hospitalId, String password) {
        User user = UserRepository.get(hospitalId);
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

    /**
     * Checks if a user is using the default password (an empty string).
     *
     * @param user the user whose password status is to be checked
     * @return {@code true} if the user has a default empty password, {@code false} otherwise
     */
    public static boolean checkDefaultPassword(User user) {
        if (user.getPassword().equals("")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Changes the user's password from the default to a specified new password.
     *
     * @param user        the user whose password is to be updated
     * @param newPassword the new password to be set
     */
    public static void changeDefaultPassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }
}
