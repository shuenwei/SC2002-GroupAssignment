package UI;

import Entity.Appointment;
import Entity.AppointmentOutcomeRecord;
import Entity.MedicalHistory;
import Entity.Patient;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;
import View.AppointmentListView;
import View.AppointmentOutcomeRecordView;
import View.AppointmentView;
import View.MedicalHistoryView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientUI {

    private Patient patient;
    private PatientAppointmentUI patientAppointmentUI;
    private PatientMedicalRecordUI patientMedicalRecordUI;

    public PatientUI (Patient patient) {
        this.patient = patient;
        patientAppointmentUI = new PatientAppointmentUI(this.patient);
        patientMedicalRecordUI = new PatientMedicalRecordUI(patient.getMedicalRecord(),patient);
    }

    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        IDisplayableView<Appointment> appointmentView = new AppointmentView();
        IDisplayableView<AppointmentOutcomeRecord> appointmentOutcomeView = new AppointmentOutcomeRecordView();
        IListDisplayableView<Appointment> appointmentListView = new AppointmentListView();
        IListDisplayableView<MedicalHistory> medicalHistoryView = new MedicalHistoryView();

        do {
            try {
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Medical Record");
                System.out.println("(2) Update Personal Information"); 
                System.out.println("(3) View Available Appointment Slots"); 
                System.out.println("(4) Schedule an Appointment"); 
                System.out.println("(5) Reschedule an Appointment"); 
                System.out.println("(6) Cancel an Appointment"); 
                System.out.println("(7) View Scheduled Appointments"); 
                System.out.println("(8) View Past Appointment Outcome Records"); 
                System.out.println("(9) Logout"); 
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        patientMedicalRecordUI.displayMedicalRecord(medicalHistoryView);
                        break;
                    case 2:
                        patientMedicalRecordUI.editMedicalRecord();
                        break;
                    case 3:
                        patientAppointmentUI.viewSlots();
                        break;
                    case 4:
                        patientAppointmentUI.scheduleAppointment();
                        break;
                    case 5:
                        patientAppointmentUI.rescheduleAppointment(appointmentView,appointmentListView);
                        break;
                    case 6:
                        patientAppointmentUI.cancelAppointment(appointmentListView);
                        break;
                    case 7:
                        patientAppointmentUI.displayAppointments(appointmentListView);
                        break;
                    case 8:
                        patientMedicalRecordUI.displayAppointmentOutcomeRecord(appointmentListView, appointmentOutcomeView);
                        break;
                    case 9:
                        System.out.println("You are now logged out.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.nextLine();
            }
        } while (option != 9);
    }
}
