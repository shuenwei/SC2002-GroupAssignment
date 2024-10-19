package Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Entity.*;

public class UserRepository {
    private static HashMap<String, User> users = new HashMap<String, User>();

    public static void add(User user) {
        users.put(user.getHospitalId(),user);
    }

    public static User get(String hospitalId) {
        return users.get(hospitalId);
    }

    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
            for (User user : users.values()) {
                if (user instanceof Doctor) {
                    doctors.add((Doctor) user);
                }
            }
        return doctors;
    }
}
