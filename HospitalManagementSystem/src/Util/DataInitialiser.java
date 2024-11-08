package Util;

import Entity.*;
import Enums.AppointmentStatus;
import Enums.PrescriptionStatus;
import Enums.RequestStatus;
import Repository.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DataInitialiser {

    public static void initialiseAll(){
        DataInitialiser.initialisePatient();
        DataInitialiser.initialiseStaff();
        DataInitialiser.initialiseMedicine();
        DataInitialiser.initialiseAppointments();
        DataInitialiser.initialiseAvailability();
        DataInitialiser.initialiseRequest();
        DataInitialiser.initialiseMedicalHistory();
    }
    
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
                String emailAddress = patientCsv[6];
                String phoneNumber = patientCsv[7];

                Patient patient = new Patient(hospitalId,password,name,gender,dateOfBirth,bloodType,emailAddress,phoneNumber);

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

                if (role.equals("DOCTOR")) {
                    Doctor doctor = new Doctor(hospitalId,password,name,gender,Enums.Role.DOCTOR,age);
                    UserRepository.add(doctor);
                }
                else if (role.equals("PHARMACIST")) {
                    Pharmacist pharmacist = new Pharmacist(hospitalId,password,name,gender,Enums.Role.PHARMACIST,age);
                    UserRepository.add(pharmacist);
                }
                else if (role.equals("ADMINISTRATOR")) {
                    Administrator administrator = new Administrator(hospitalId,password,name,gender,Enums.Role.ADMINISTRATOR,age);
                    UserRepository.add(administrator);
                }
                else if (role.equals("NURSE")){
                    Nurse nurse = new Nurse(hospitalId,password,name,gender,Enums.Role.NURSE,age);
                    UserRepository.add(nurse);
                }
                else {
                    throw new IllegalArgumentException("Invalid role: " + role);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialiseAvailability() {
    String availabilityCsvFilePath = "data/availabilityCsv.csv";
    try (BufferedReader br = new BufferedReader(new FileReader(availabilityCsvFilePath))) {
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            
            // Get the Doctor ID and retrieve the Doctor object
            String doctorId = data[0];
            Doctor doctor = (Doctor) UserRepository.get(doctorId);

            // Check if the doctor exists in the repository
            if (doctor != null) {
                // Iterate over each day's availability
                for (int i = 1; i <= 7; i++) {
                    String availabilityData = data[i];
                    DayOfWeek dayOfWeek = DayOfWeek.of(i);

                    if (!availabilityData.equals("Not Available")) {
                        String[] times = availabilityData.split("-");
                        LocalTime startTime = LocalTime.parse(times[0]);
                        LocalTime endTime = LocalTime.parse(times[1]);

                        Availability availability = new Availability(dayOfWeek, startTime, endTime);
                        doctor.setAvailability(dayOfWeek, availability);
                    }
                }
            } else {
                System.err.println("Doctor with ID " + doctorId + " not found.");
            }
        }
    } catch (IOException | DateTimeParseException e) {
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
                InventoryRepository.add(medicine);  
            }     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialiseRequest() {
        String requestCsvFilePath = "data/requestCsv.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(requestCsvFilePath))) {
            // Skip the header line
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Parse the medicine name and request status
                String medicineName = data[0];
                RequestStatus status = RequestStatus.valueOf(data[1]);

                // Create a new Request object and set its status
                Request request = new Request(medicineName);
                request.setStatus(status);

                // Add the request to the list
                InventoryRepository.addRequest(request);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void initialiseMedicalHistory() {
        String medicalHistoryCsvFilePath = "data/medicalHistoryCsv.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(medicalHistoryCsvFilePath))) {
            String line = br.readLine(); // Skip header
    
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
    
                String patientId = data[0];
                String diagnosisandType = data[1];
                String treatmentPlan = data[2];
                String[] medications = data[3].split(";");
    
                Patient patient = (Patient) UserRepository.get(patientId);
    
                if (patient != null) {
                    MedicalHistory history = new MedicalHistory(diagnosisandType, treatmentPlan);
    
                    for (String medication : medications) {
                        if (!medication.isEmpty()) {
                            history.addPrescribedMedications(medication);
                        }
                    }
    
                    patient.getMedicalRecord().addMedicalHistory(history);
                } else {
                    System.err.println("Error: Patient with ID " + patientId + " not found.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void initialiseAppointments() {
    String appointmentCsvFilePath = "data/appointmentCsv.csv";
    try (BufferedReader br = new BufferedReader(new FileReader(appointmentCsvFilePath))) {
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            String patientId = data[0];
            String doctorId = data[1];
            LocalDate date = LocalDate.parse(data[2]);
            LocalTime time = LocalTime.parse(data[3]);
            Enums.AppointmentStatus status = Enums.AppointmentStatus.valueOf(data[4]);

            Patient patient = (Patient) UserRepository.get(patientId);
            Doctor doctor = (Doctor) UserRepository.get(doctorId);

            AppointmentOutcomeRecord appointmentOutcomeRecord = null;

            if (data.length > 5) {
                String typeOfService = data[5];
                String consultationNotes = data[6];

                ArrayList<PrescribedMedication> prescribedMedications = new ArrayList<>();
                if (data.length > 7) {
                    String[] medications = data[7].split(";");
                    for (String PrescribedMedication : medications) {
                        String medicineName = PrescribedMedication.split("-")[0];
                        PrescriptionStatus prescriptionStatus = PrescriptionStatus.valueOf(PrescribedMedication.split("-")[1]);
                        PrescribedMedication prescribedMedication = new PrescribedMedication(medicineName,prescriptionStatus);
                        prescribedMedications.add(prescribedMedication);
                    }
                }

                appointmentOutcomeRecord = new AppointmentOutcomeRecord(typeOfService, consultationNotes, prescribedMedications, null);
            }

            Appointment appointment = new Appointment(doctor, patient, date, time, status, appointmentOutcomeRecord);

            if (appointmentOutcomeRecord != null) {
                appointmentOutcomeRecord.setAppointment(appointment);
            }

            AppointmentRepository.add(appointment);
            patient.addAppointment(appointment);
            doctor.addAppointment(appointment);
        }
    } catch (IOException | DateTimeParseException | IllegalArgumentException e) {
        e.printStackTrace();
    }
}

}