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
            pw.println("Patient ID,Password,Name,Date of Birth,Gender,Blood Type,Email Address,Phone Number");
            for (Patient patient : UserRepository.getAllPatients()) {
                MedicalRecord medicalRecord = patient.getMedicalRecord();
                String[] data = {
                    patient.getHospitalId(),
                    patient.getPassword(),
                    patient.getName(),
                    medicalRecord.getDateOfBirth(),
                    patient.getGender(),
                    medicalRecord.getBloodType(),
                    medicalRecord.getEmailAddress(),
                    medicalRecord.getPhoneNumber()
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveAppointmentData() {
        String appointmentCsvFilePath = "data/appointmentCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(appointmentCsvFilePath))) {
            pw.println("Patient ID,Doctor ID,Date,Time,Status");
            for (Patient patient : UserRepository.getAllPatients()) {
                for (Appointment appointment : patient.getAppointments()) {
                    String[] data = {
                        appointment.getPatient().getHospitalId(),
                        appointment.getDoctor().getHospitalId(),
                        appointment.getDate().toString(),
                        appointment.getTime().toString(),
                        appointment.getStatus().toString()
                    };
                    pw.println(String.join(",", data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}