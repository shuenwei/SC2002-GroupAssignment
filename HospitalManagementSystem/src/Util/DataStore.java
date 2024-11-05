package Util;

import Entity.*;
import Repository.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
            pw.println("Patient ID,Doctor ID,Date,Time,Status,Type of Service,Consultation Notes,Prescribed Medications[0],Prescribed Medications[1],Prescribed Medications[2]");
            for (Patient patient : UserRepository.getAllPatients()) {
                for (Appointment appointment : patient.getAppointments()) {
    
                    List<String> data = new ArrayList<>();

                    data.add(appointment.getPatient().getHospitalId());
                    data.add(appointment.getDoctor().getHospitalId());
                    data.add(appointment.getDate().toString());
                    data.add(appointment.getTime().toString());
                    data.add(appointment.getStatus().toString());

                    if (appointment.getAppointmentOutcomeRecord() != null) {
                        data.add(appointment.getAppointmentOutcomeRecord().getTypeOfService());
                        data.add(appointment.getAppointmentOutcomeRecord().getConsultationNotes());
                        for (PrescribedMedication j : appointment.getAppointmentOutcomeRecord().getPrescribedMedications()) {
                            data.add(j.getMedicineName());
                        }
                    } else {
                        data.add("");
                        data.add(""); 
                        data.add("");
                        data.add("");
                        data.add(""); 
                    }

                    String[] dataArray = data.toArray(new String[0]);
                    pw.println(String.join(",", dataArray));
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

    }

}