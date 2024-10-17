package Repository;

import java.util.HashMap;

import Entity.*;

public class UserRepository {
    private static HashMap<String, User> users = new HashMap<String, User>();

    public static void add(User user) {
        users.put(user.getHospitalId(),user);
    }

    public static User get(String hospitalId) {
        return users.get(hospitalId);
    }
}
