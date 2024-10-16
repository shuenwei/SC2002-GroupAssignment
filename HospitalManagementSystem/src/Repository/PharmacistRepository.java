package Repository;

import java.util.HashMap;

import Entity.Pharmacist;

public class PharmacistRepository {
    private static HashMap<String, Pharmacist> pharmacists = new HashMap<String, Pharmacist>();

    public static void add(Pharmacist pharmacist) {
        pharmacists.put(pharmacist.getHospitalId(),pharmacist);
    }

    public static Pharmacist get(String hospitalId) {
        return pharmacists.get(hospitalId);
    }
}
