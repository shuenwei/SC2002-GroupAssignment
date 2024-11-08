package Util;

import Entity.*;
import Repository.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    
    public static void saveStaffData() {
        String staffCsvFilePath = "data/staffCsv.csv";
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

    public static void saveDoctorAvailability() {
        String doctorAvailabilityCsvFilePath = "data/availabilityCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(doctorAvailabilityCsvFilePath))) {
            pw.println("Doctor ID,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday");

            for (Doctor doctor : UserRepository.getAllDoctors()) {
                String[] data = new String[8];

                data[0] = doctor.getHospitalId();
            
                for (int i = 1; i <= 7; i++) {
                    Availability availability = doctor.getAvailability(DayOfWeek.of(i));
                    
                    if (availability != null) {
                        String startTime = availability.getStartTime().toString();
                        String endTime = availability.getEndTime().toString();
                        data[i] = startTime + "-" + endTime;
                    } else {
                        data[i] = "Not Available";
                    }

                }

                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePatientData() {
        String patientCsvFilePath = "data/patientCsv.csv";
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
            pw.println("Patient ID,Doctor ID,Date,Time,Status,Type of Service,Consultation Notes,Prescribed Medications");
            for (Appointment appointment : AppointmentRepository.getAllAppointments()) {

                List<String> data = new ArrayList<>();

                data.add(appointment.getPatient().getHospitalId());
                data.add(appointment.getDoctor().getHospitalId());
                data.add(appointment.getDate().toString());
                data.add(appointment.getTime().toString());
                data.add(appointment.getStatus().toString());

                if (appointment.getAppointmentOutcomeRecord() != null) {
                    data.add(appointment.getAppointmentOutcomeRecord().getTypeOfService());
                    data.add(appointment.getAppointmentOutcomeRecord().getConsultationNotes());

                    List<String> prescriptionData = new ArrayList<>();
                    for (PrescribedMedication prescribedMedication : appointment.getAppointmentOutcomeRecord().getPrescribedMedications()) {
                        List<String> prescriptionDetails = new ArrayList<>();
                        prescriptionDetails.add(prescribedMedication.getMedicineName());
                        prescriptionDetails.add(prescribedMedication.getStatus().toString());
                        String prescription = String.join("-", prescriptionDetails);
                        prescriptionData.add(prescription);
                    }
                    String joinedPrescribedMedications = String.join(";", prescriptionData);
                    data.add(joinedPrescribedMedications );
                }

                String[] dataArray = data.toArray(new String[0]);
                pw.println(String.join(",", dataArray));
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }



}