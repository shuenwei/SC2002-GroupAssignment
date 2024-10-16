package Repository;

import java.util.HashMap;

import Entity.Administrator;

public class AdministratorRepository {
    private static HashMap<String, Administrator> administrators = new HashMap<String, Administrator>();

    public static void add(Administrator administrator) {
        administrators.put(administrator.getHospitalId(), administrator);
    }

    public static Administrator get(String hospitalId) {
        if (administrators.containsKey(hospitalId)) {
            return administrators.get(hospitalId);
        } else {
            return null;
        }
    }

    public static void remove(String hospitalId) {
        if (administrators.containsKey(hospitalId)) {
            administrators.remove(hospitalId);
        }
    }
}
