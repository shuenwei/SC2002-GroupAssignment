package Repository;

import java.util.HashMap;

import Entity.Doctor;

public class DoctorRepository {
    private static HashMap<String, Doctor> doctors = new HashMap<String, Doctor>();

    public static void add(Doctor doctor) {
        doctors.put(doctor.getHospitalId(),doctor);
    }

    public static Doctor get(String HospitalId) {
        return doctors.get(HospitalId);
    }
}
