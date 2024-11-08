package UI;

import Entity.*;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The PatientMedicalRecordUI class provides a user interface for managing and displaying
 * a patient's medical record, allowing patients to view, edit, and interact with
 * medical history and appointment outcome records.
 */
public class PatientMedicalRecordUI {

    /**
     * The medical record associated with the patient.
     */
    private MedicalRecord medicalRecord;

    /**
     * The patient associated with this UI.
     */
    private Patient patient;

    /**
     * Scanner for capturing user input.
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a PatientMedicalRecordUI instance for the specified medical record and patient.
     *
     * @param medicalRecord The medical record of the patient.
     * @param patient       The patient associated with this medical record.
     */
    public PatientMedicalRecordUI(MedicalRecord medicalRecord, Patient patient) {
        this.medicalRecord = medicalRecord;
        this.patient = patient;
    }

    /**
     * Displays the patient's medical record details, including personal information and medical history.
     *
     * @param medicalHistoryView A view component to display the patient's medical history.
     */
    public void displayMedicalRecord(IListDisplayableView<MedicalHistory> medicalHistoryView) {
        if(medicalRecord.getMedicalHistory() != null) {
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

    /**
     * Allows the patient to edit specific fields in their medical record, such as email address and phone number.
     */
    public void editMedicalRecord() {
        int choice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("1) Change Email Address");
        System.out.println("2) Change Phone Number");
        System.out.println("Please enter your choice:");
        while (true) {
            try {
                choice = input.nextInt();
                input.nextLine();

                if (choice >= 1 && choice <= 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }

        switch (choice) {
            case 1:
                System.out.println("Enter new Email Address:");
                String newEmail = input.nextLine();
                medicalRecord.setEmailAddress(newEmail);
                System.out.println("Updated Email Address: " + medicalRecord.getEmailAddress());
                break;
            case 2:
                System.out.println("Enter new Phone Number:");
                String newPhone = input.nextLine();
                medicalRecord.setPhoneNumber(newPhone);
                System.out.println("Updated Phone Number: " + medicalRecord.getPhoneNumber());
                break;
        }


    }

    /**
     * Displays the appointment outcome records associated with the patient's appointments.
     * Allows the user to select an appointment to view its outcome record.
     *
     * @param appointmentListView      A view component to display the list of appointments.
     * @param appointmentOutcomeView   A view component to display the outcome record of a selected appointment.
     */
    public void displayAppointmentOutcomeRecord(IListDisplayableView<Appointment> appointmentListView, IDisplayableView <AppointmentOutcomeRecord> appointmentOutcomeView) {
        if(patient.getAppointments().isEmpty()) {
            System.out.println("No Appointments Found");
        }
        else {
            ArrayList<Appointment> appointments = patient.getAppointments();

            if (appointments.isEmpty()) {
                System.out.println("You have no Confirmed Appointments.");
                return;
            }

            appointmentListView.display(appointments);
            System.out.println();

            int appointmentChoice = -1;

            while (appointmentChoice == -1) {
                System.out.print("Please select an appointment to view appointment outcome record for by entering the corresponding number: ");
                try {
                    appointmentChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (appointmentChoice >= 1 && appointmentChoice <= appointments.size()) {
                        break;
                    } else {
                        appointmentChoice = -1;
                        System.out.println("Invalid selection. Please enter a number between 1 and " + appointments.size() + ".");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            Appointment selectedAppointment = appointments.get(appointmentChoice - 1);
            appointmentOutcomeView.display(selectedAppointment.getAppointmentOutcomeRecord());
        }
    }


}
