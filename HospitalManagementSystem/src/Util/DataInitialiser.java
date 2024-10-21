package Util;

import Entity.*;
import Repository.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataInitialiser {
    public static void initialisePatient() {
        String patientCsvFilePath = "data/patientCsv.csv";
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(patientCsvFilePath))) {  
            line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {  
                String[] patientCsv = line.split(",");

                String hospitalId = patientCsv[0];
                String password = patientCsv[1];
                String name = patientCsv[2];
                String dateOfBirth = patientCsv[3];
                String gender = patientCsv[4];
                String bloodType = patientCsv[5];
                String contactInformation = patientCsv[6];

                Patient patient = new Patient(hospitalId,password,name,gender,dateOfBirth,bloodType,contactInformation);
                UserRepository.add(patient);  
            }     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialiseStaff() {
        String staffCsvFilePath = "data/staffCsv.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(staffCsvFilePath))) {
            line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] staffCsv = line.split(",");

                String hospitalId = staffCsv[0];
                String password = staffCsv[1];
                String name = staffCsv[2];
                String role = staffCsv[3];
                String gender = staffCsv[4];
                int age = Integer.valueOf(staffCsv[5]);

                if (role.equals("Doctor")) {
                    Doctor doctor = new Doctor(hospitalId,password,name,gender,Enums.Role.DOCTOR,age);
                    UserRepository.add(doctor);
                }
                else if (role.equals("Pharmacist")) {
                    Pharmacist pharmacist = new Pharmacist(hospitalId,password,name,gender,Enums.Role.PHARMACIST,age);
                    UserRepository.add(pharmacist);
                }
                else if (role.equals("Administrator")) {
                    Administrator administrator = new Administrator(hospitalId,password,name,gender,Enums.Role.ADMINISTRATOR,age);
                    UserRepository.add(administrator);
                }
                else {
                    throw new IllegalArgumentException("Invalid role: " + role);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void initialiseMedicine() {
        String medicineListCsvFilePath = "data/medicineListCsv.csv";
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(medicineListCsvFilePath))) {  
            line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {  
                String[] medicineListCsv = line.split(",");

                String medicineName = medicineListCsv[0];
                int stock = Integer.valueOf(medicineListCsv[1]);
                int lowStockLevel = Integer.valueOf(medicineListCsv[2]);

                Medication medicine = new Medication(medicineName, stock, lowStockLevel);
                Inventory.add(medicine);  
            }     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}