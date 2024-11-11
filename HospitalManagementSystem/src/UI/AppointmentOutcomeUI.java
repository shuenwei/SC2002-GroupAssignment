package src.UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import src.Controller.DoctorController;
import src.Entity.*;
import src.Controller.AppointmentOutcomeController;
import src.Enums.AppointmentStatus;
import src.Interface.IDisplayableView;
import src.Interface.IListDisplayableView;
import src.View.CommonView;

/**
 * The {@code AppointmentOutcomeUI} class provides a user interface for creating appointment outcomes,
 * enabling doctors to record services, consultation notes, and prescribed medications for a selected appointment.
 * It allows doctors to update the appointment status and optionally add the outcome record to the patient's medical history.
 */
public class AppointmentOutcomeUI {

    private Doctor doctor;
    private Scanner scanner;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructs an {@code AppointmentOutcomeUI} with the specified doctor.
     *
     * @param doctor the doctor associated with this appointment outcome UI
     */
    public AppointmentOutcomeUI(Doctor doctor) {
        this.doctor = doctor;
        scanner = new Scanner(System.in);
    }

    /**
     * Creates an appointment outcome record for a confirmed appointment.
     * This method:
     * <ul>
     * <li>Displays confirmed appointments and prompts the user to select one.</li>
     * <li>Records details such as the type of service provided, consultation notes, and prescribed medications.</li>
     * <li>Sets the appointment status to "COMPLETED" or "MEDICINE_PENDING" based on prescribed medications.</li>
     * <li>Provides an option to add the outcome record to the patient's medical history.</li>
     * </ul>
     *
     * @param appointmentListView         the view to display the list of appointments
     * @param appointmentOutcomeRecordView the view to display the created appointment outcome record
     * @param medicalHistoryView          the view to display the patient's medical history
     * @param doctorController            the controller for retrieving confirmed appointments
     */
    public void createAppointmentOutcome(IListDisplayableView<Appointment> appointmentListView,
                                         IDisplayableView<AppointmentOutcomeRecord> appointmentOutcomeRecordView,
                                         IDisplayableView<MedicalHistory> medicalHistoryView,
                                         DoctorController doctorController) {
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

        while (true) {
            System.out.print("Enter names of medication to be prescribed (If no more prescriptions type 'Exit') : ");
            String name = sc.nextLine();
            if (name.equals("Exit")) {
                break;
            }

            boolean exist = AppointmentOutcomeController.checkMedicationExist(name);

            if (exist) {
                prescribedMedications.add(name);
                System.out.println("Medication added: " + name);
            }
        }

        for (String medication : prescribedMedications) {
            PrescribedMedication prescribedMedication = new PrescribedMedication(medication);
            prescribedMedicationsList.add(prescribedMedication);
        }

        AppointmentOutcomeRecord record = AppointmentOutcomeController.createAppointmentOutcomeRecord(service, notes, prescribedMedicationsList, selectedAppointment);
        selectedAppointment.setAppointmentOutcomeRecord(record);

        if (selectedAppointment.getAppointmentOutcomeRecord().getPrescribedMedications().isEmpty()) {
            selectedAppointment.setStatus(AppointmentStatus.COMPLETED);
        } else {
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
                for (PrescribedMedication i : record.getPrescribedMedications()) {
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
