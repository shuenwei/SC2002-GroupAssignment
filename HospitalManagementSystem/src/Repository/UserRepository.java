package Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Entity.*;

public class UserRepository {
    private static HashMap<String, User> users = new HashMap<String, User>();
    private static List<Appointment> allAppointments;

    public static void add(User user) {
        users.put(user.getHospitalId(),user);
    }

    public static User get(String hospitalId) {
        return users.get(hospitalId);
    }

    public static void remove(String hospitalId){
        if(users.get(hospitalId) != null){
            users.remove(hospitalId);
        }
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

   

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Patient) {
                patients.add((Patient) user);
            }
        }
      
    public static List<Staff> getAllStaff() {
        List<Staff> staffs = new ArrayList<>();
            for (User user : users.values()) {
                if (user instanceof Staff) {
                    staffs.add((Staff) user);
                }
            }
        return staffs;
    }

    public static List<Patient> getAllPatient() {
        List<Patient> patients = new ArrayList<>();
            for (User user : users.values()) {
                if (user instanceof Patient) {
                    patients.add((Patient) user);
                }
            }
        return patients;
    }
}
