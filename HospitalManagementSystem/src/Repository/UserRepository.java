package Repository;

import java.util.ArrayList;
import java.util.HashMap;

import Entity.*;

public class UserRepository {
    private static HashMap<String, User> users = new HashMap<String, User>();
    private static ArrayList<Appointment> allAppointments;

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


    public static ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
            for (User user : users.values()) {
                if (user instanceof Doctor) {
                    doctors.add((Doctor) user);
                }
            }
        return doctors;
    }

   
    public static ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Patient) {
                patients.add((Patient) user);
            }
        }
        return patients;
    }

    public static ArrayList<Pharmacist> getAllPharmacists() {
        ArrayList<Pharmacist> pharmacist = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Pharmacist) {
                pharmacist.add((Pharmacist) user);
            }
        }
        return pharmacist;
    }

    public static ArrayList<Administrator> getAllAdministrators() {
        ArrayList<Administrator> administrators = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Administrator) {
                administrators.add((Administrator) user);
            }
        }
        return administrators;
    }

    public static ArrayList<Nurse> getAllNurses() {
        ArrayList<Nurse> nurses = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Nurse) {
                nurses.add((Nurse) user);
            }
        }
        return nurses;
    }

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
