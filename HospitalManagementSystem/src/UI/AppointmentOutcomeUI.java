package UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import Controller.DoctorController;
import Entity.*;
import Controller.AppointmentOutcomeController;
import Enums.AppointmentStatus;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;
import View.CommonView;

public class AppointmentOutcomeUI {

    private AppointmentOutcomeController appointment;
    private Doctor doctor;
    private DoctorController doctorController;
    private Scanner scanner;
    Scanner sc = new Scanner(System.in);

    public AppointmentOutcomeUI(Doctor doctor) {
        this.doctor = doctor;
        scanner = new Scanner(System.in);
    }

    public void createAppointmentOutcome(IListDisplayableView<Appointment> appointmentListView, IDisplayableView<AppointmentOutcomeRecord> appointmentOutcomeRecordView, IDisplayableView<MedicalHistory> medicalHistoryView, DoctorController doctorController) {
        ArrayList<Appointment> ConfirmedAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.CONFIRMED);

        if (ConfirmedAppointments.isEmpty()) {
            System.out.println("You have no Confirmed Appointments.");
            return;
        }

        appointmentListView.display(ConfirmedAppointments);

        int appointmentChoice = -1;

        while (appointmentChoice == -1) {
            System.out.print("Please select an appointment to create appointment outcome record for by entering the corresponding number: ");
            System.out.println();
            try {
                appointmentChoice = scanner.nextInt();
                scanner.nextLine();
                if (appointmentChoice >= 1 && appointmentChoice <= ConfirmedAppointments.size()) {
                    break;
                } else {
                    appointmentChoice = -1;
                    System.out.println("Invalid selection. Please enter a number between 1 and " + ConfirmedAppointments.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        Appointment selectedAppointment = ConfirmedAppointments.get(appointmentChoice - 1);

        ArrayList<String> prescribedMedications = new ArrayList<>();
        ArrayList<PrescribedMedication> prescribedMedicationsList = new ArrayList<>();
        System.out.println("Create Appointment Outcome");
        System.out.println();
        String service = "";
        while (service.isEmpty()) {
            System.out.println("Enter type of service administered: ");
            service = sc.nextLine();
            if (service.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }

        String notes = "";
        while (notes.isEmpty()) {
            System.out.println("Enter consultation notes: ");
            notes = sc.nextLine();
            if (notes.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }

        while(true) {
            System.out.print("Enter names of medication to be prescribed (If no more prescriptions type 'Exit') : ");
            String name = sc.nextLine();
            if(name.equals("Exit")) {
                break;
            }

            boolean exist = AppointmentOutcomeController.checkMedicationExist(name);

            if(exist) {
                prescribedMedications.add(name);
                System.out.println("Medication added: " + name);
            }
        }

        for(String medication : prescribedMedications){
            PrescribedMedication prescribedMedication = new PrescribedMedication(medication);
            prescribedMedicationsList.add(prescribedMedication);
        }

        AppointmentOutcomeRecord record = AppointmentOutcomeController.createAppointmentOutcomeRecord(service, notes, prescribedMedicationsList,selectedAppointment);
        selectedAppointment.setAppointmentOutcomeRecord(record);
        if(selectedAppointment.getAppointmentOutcomeRecord().getPrescribedMedications().size() == 0) {
            selectedAppointment.setStatus(AppointmentStatus.COMPLETED);
        }
        else {
            selectedAppointment.setStatus(AppointmentStatus.MEDICINE_PENDING);
        }

        System.out.println();
        System.out.println("Appointment Outcome record created.");
        appointmentOutcomeRecordView.display(selectedAppointment.getAppointmentOutcomeRecord());

        int choice = -1;

        System.out.println("Do you wish to add Appointment Outcome Record to Patients Medical History?");
        System.out.println("(1) Add to Medical History");
        System.out.println("(2) Do not Add to Medical History");

        while (choice == -1) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    choice = -1;
                    System.out.println("Invalid selection. Please choose either 1 or 2 ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                Patient patient = selectedAppointment.getPatient();

                MedicalHistory newMedicalHistory = new MedicalHistory(record.getTypeOfService(), record.getConsultationNotes());
                for(PrescribedMedication i :record.getPrescribedMedications()){
                    newMedicalHistory.addPrescribedMedications(i.getMedicineName());
                }

                patient.getMedicalRecord().addMedicalHistory(newMedicalHistory);
                CommonView.newPage();
                System.out.println("Added Medical History: ");

                medicalHistoryView.display(newMedicalHistory);
                break;
            case 2:
                System.out.println("Medical History has not been updated");
                break;

        }
    }


}

