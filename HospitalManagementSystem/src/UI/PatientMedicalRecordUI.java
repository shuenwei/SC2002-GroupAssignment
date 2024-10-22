package UI;

import Entity.Appointment;
import Entity.AppointmentOutcomeRecord;
import Entity.MedicalRecord;
import Entity.Patient;
import View.AppointmentOutcomeRecordView;
import View.MedicalHistoryView;

import java.util.ArrayList;
import java.util.Scanner;

public class PatientMedicalRecordUI {

    private MedicalRecord medicalRecord;
    private MedicalHistoryView medicalHistoryView;
    private Patient patient;

    public PatientMedicalRecordUI(MedicalRecord medicalRecord, Patient patient) {
        this.medicalRecord = medicalRecord;
        this.patient = patient;
    }

    public void displayMedicalRecord() {
        if(medicalRecord.getMedicalHistory() != null) {
            medicalHistoryView = new MedicalHistoryView();
            System.out.println("PatientID: " + medicalRecord.getPatientID());
            System.out.println("Name: " + medicalRecord.getName());
            System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
            System.out.println("Gender: " + medicalRecord.getGender());
            System.out.println("Email Address: " + medicalRecord.getEmailAddress());
            System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
            System.out.println("BloodType: " + medicalRecord.getBloodType());
            medicalHistoryView.display(medicalRecord.getMedicalHistory());
        }
        else{
            System.out.println("PatientID: " + medicalRecord.getPatientID());
            System.out.println("Name: " + medicalRecord.getName());
            System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
            System.out.println("Gender: " + medicalRecord.getGender());
            System.out.println("Email Address: " + medicalRecord.getEmailAddress());
            System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
            System.out.println("BloodType: " + medicalRecord.getBloodType());
            System.out.println("MedicalHistory: There are no diagnoses ");
        }

    }

    public void editMedicalRecord() {
        System.out.println("1) Change Email Address");
        System.out.println("2) Change Phone Number");
        System.out.println("Please enter your choice:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("Enter new Email Address:");
            medicalRecord.setEmailAddress(input.next());
            System.out.println("Updated Email Address: " + medicalRecord.getEmailAddress());
        }
        else if (choice == 2) {
            System.out.println("Enter new Phone Number:");
            medicalRecord.setPhoneNumber(input.next());
            System.out.println("Updated Phone Number: " + medicalRecord.getPhoneNumber());
        }

    }


    public void displayAppointmentOutcomeRecord() {
        if(patient.getAppointments().isEmpty()) {
            System.out.println("No Appointments Found");
        }
        else {
            ArrayList<Appointment> appt = patient.getAppointments();
            ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords = new ArrayList<>();
            for (Appointment a : appt) {
                appointmentOutcomeRecords.add(a.getAppointmentOutcomeRecord());
            }
            AppointmentOutcomeRecordView appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
            appointmentOutcomeRecordView.display(appointmentOutcomeRecords);
        }
    }


}
