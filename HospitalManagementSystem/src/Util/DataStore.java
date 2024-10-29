package Util;

import Entity.*;
import Repository.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataStore {
    
    public static void saveStaffData() {
        String staffCsvFilePath = "data/staffCsv_1.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(staffCsvFilePath))) {
            pw.println("Staff ID,Password,Name,Role,Gender,Age"); // Writing header
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

    public static void savePatientData() {
        String patientCsvFilePath = "data/patientCsv_1.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(patientCsvFilePath))) {
            pw.println("Patient ID,Password,Name,Date of Birth,Gender,Blood Type,Contact Information");
            for (Patient patient : UserRepository.getAllPatients()) {
                String[] data = {
                    patient.getHospitalId(),
                    patient.getPassword(),
                    patient.getName(),
                    patient.getDateOfBirth(),
                    patient.getGender(),
                    patient.getBloodType(),
                    patient.getContactInformation(),
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}