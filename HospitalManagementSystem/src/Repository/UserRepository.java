package Repository;

import java.util.ArrayList;
import java.util.HashMap;
import Entity.*;

/**
 * The UserRepository class provides methods for managing user entities in a static repository.
 * It allows adding, retrieving, and removing users by their hospital ID, as well as retrieving specific user types.
 */
public class UserRepository {
    
    private static HashMap<String, User> users = new HashMap<String, User>();

    /**
     * Adds a new user to the repository.
     *
     * @param user The User object to be added, identified by its hospital ID.
     */
    public static void add(User user) {
        users.put(user.getHospitalId(), user);
    }

    /**
     * Retrieves a user from the repository by their hospital ID.
     *
     * @param hospitalId The hospital ID of the user to retrieve.
     * @return The User object with the specified hospital ID, or null if not found.
     */
    public static User get(String hospitalId) {
        return users.get(hospitalId);
    }

    /**
     * Removes a user from the repository by their hospital ID.
     *
     * @param hospitalId The hospital ID of the user to remove.
     */
    public static void remove(String hospitalId) {
        if (users.get(hospitalId) != null) {
            users.remove(hospitalId);
        }
    } 

    /**
     * Retrieves all users in the repository who are doctors.
     *
     * @return An ArrayList of Doctor objects representing all doctors in the repository.
     */
    public static ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Doctor) {
                doctors.add((Doctor) user);
            }
        }
        return doctors;
    }

    /**
     * Retrieves all users in the repository who are patients.
     *
     * @return An ArrayList of Patient objects representing all patients in the repository.
     */
    public static ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Patient) {
                patients.add((Patient) user);
            }
        }
        return patients;
    }

    /**
     * Retrieves all users in the repository who are pharmacists.
     *
     * @return An ArrayList of Pharmacist objects representing all pharmacists in the repository.
     */
    public static ArrayList<Pharmacist> getAllPharmacists() {
        ArrayList<Pharmacist> pharmacist = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Pharmacist) {
                pharmacist.add((Pharmacist) user);
            }
        }
        return pharmacist;
    }

    /**
     * Retrieves all users in the repository who are administrators.
     *
     * @return An ArrayList of Administrator objects representing all administrators in the repository.
     */
    public static ArrayList<Administrator> getAllAdministrators() {
        ArrayList<Administrator> administrators = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Administrator) {
                administrators.add((Administrator) user);
            }
        }
        return administrators;
    }

    /**
     * Retrieves all users in the repository who are nurses.
     *
     * @return An ArrayList of Nurse objects representing all nurses in the repository.
     */
    public static ArrayList<Nurse> getAllNurses() {
        ArrayList<Nurse> nurses = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Nurse) {
                nurses.add((Nurse) user);
            }
        }
        return nurses;
    }

    /**
     * Retrieves all users in the repository who are staff members.
     *
     * @return An ArrayList of Staff objects representing all staff members in the repository.
     */
    public static ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> staffs = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Staff) {
                staffs.add((Staff) user);
            }
        }
        return staffs;
    }
}
