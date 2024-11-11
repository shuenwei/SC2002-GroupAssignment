package src.UI;

import src.Entity.Appointment;
import src.Entity.AppointmentOutcomeRecord;
import src.Entity.MedicalHistory;
import src.Entity.Patient;
import src.Interface.IDisplayableView;
import src.Interface.IListDisplayableView;
import src.View.AppointmentListView;
import src.View.AppointmentOutcomeRecordView;
import src.View.AppointmentView;
import src.View.MedicalHistoryListView;
import src.View.CommonView;

import java.util.InputMismatchException;
import java.util.Scanner;

import src.Controller.AppointmentController;
import src.Controller.PatientController;

/**
 * The PatientUI class provides a user interface for patients to interact with the system.
 * It allows patients to manage their personal information, view medical records, schedule appointments,
 * view past appointment records, and perform other actions related to appointments and medical records.
 */
public class PatientUI {

    /**
     * The patient associated with this UI.
     */
    private Patient patient;
    private PatientAppointmentUI patientAppointmentUI;

    /**
     * User interface for managing patient medical records.
     */
    private PatientMedicalRecordUI patientMedicalRecordUI;

    /**
     * Constructs a PatientUI instance for the specified patient.
     *
     * @param patient The patient associated with this UI.
     */
    public PatientUI (Patient patient) {
        this.patient = patient;
        patientAppointmentUI = new PatientAppointmentUI(this.patient);
        patientMedicalRecordUI = new PatientMedicalRecordUI(patient.getMedicalRecord(),patient);
    }

    /**
     * Displays the main menu for the patient to choose actions.
     * Options include viewing and updating medical records, managing appointments, and logging out.
     */
    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        PatientController patientController = new PatientController(patient);
        AppointmentController appointmentController = new AppointmentController();
        IDisplayableView<Appointment> appointmentView = new AppointmentView();
        IDisplayableView<AppointmentOutcomeRecord> appointmentOutcomeView = new AppointmentOutcomeRecordView();
        IListDisplayableView<Appointment> appointmentListView = new AppointmentListView();
        IListDisplayableView<MedicalHistory> medicalHistoryView = new MedicalHistoryListView();

        do {
            try {
                CommonView.newPage();
                System.out.println("Hello, " + patient.getName());
                System.out.println();
                System.out.println("Select an option:");
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
                        patientAppointmentUI.scheduleAppointment(appointmentController);
                        break;
                    case 5:
                        patientAppointmentUI.rescheduleAppointment(appointmentView,appointmentListView, patientController);
                        break;
                    case 6:
                        patientAppointmentUI.cancelAppointment(appointmentListView, patientController);
                        break;
                    case 7:
                        patientAppointmentUI.displayAppointments(appointmentListView, patientController);
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
