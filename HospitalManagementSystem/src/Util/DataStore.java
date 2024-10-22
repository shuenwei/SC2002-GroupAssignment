package Util;

import Entity.*;
import Repository.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataStore {
    
    public static void saveStaffData() {
        String staffCsvFilePath = "data/staffCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(staffCsvFilePath))) {
            pw.println("HospitalId,Password,Name,Role,Gender,Age"); // Writing header
            for (Staff staff : UserRepository.getAllStaff()) {
                String[] data = {
                    staff.getHospitalId(),
                    staff.getPassword(),
                    staff.getName(),
                    staff.getRole().toString(),
                    staff.getGender(),
                    String.valueOf(staff.getAge())
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}