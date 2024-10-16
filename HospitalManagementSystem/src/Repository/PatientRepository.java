package Repository;

import java.util.HashMap;

import Entity.Patient;

public class PatientRepository {
    private static HashMap<String, Patient> patients = new HashMap<String, Patient>();

    public static void add(Patient patient) {
        patients.put(patient.getHospitalId(),patient);
    }

    public static Patient get(String HospitalId) {
        return patients.get(HospitalId);
    }
}
