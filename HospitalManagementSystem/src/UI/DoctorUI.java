package UI;

import Controller.DoctorController;
import Entity.Appointment;
import Entity.AppointmentOutcomeRecord;
import Entity.Doctor;
import Entity.MedicalHistory;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;
import View.*;

import java.util.InputMismatchException;
import java.util.Scanner;


public class DoctorUI {

    private Doctor doctor;
    private Appointment appointment;
    private DoctorMedicalRecordUI doctorMedicalRecordUI;
    private AppointmentOutcomeUI appointmentOutcomeUI;
    private DoctorController doctorController;

    public DoctorUI (Doctor doctor) {
        this.doctor = doctor;
        doctorController = new DoctorController(this.doctor);
    }

    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        AvailabilityUI availabilityUI = new AvailabilityUI(this.doctor);
        DoctorAppointmentUI doctorAppointmentUI = new DoctorAppointmentUI(this.doctor,doctorController);
        doctorMedicalRecordUI = new DoctorMedicalRecordUI(doctorController);
        appointmentOutcomeUI = new AppointmentOutcomeUI(doctor,doctorController);
        IDisplayableView<Appointment> appointmentView = new AppointmentView();
        IListDisplayableView<Appointment> appointmentListView = new AppointmentListView();
        IDisplayableView<AppointmentOutcomeRecord> appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
        IDisplayableView<MedicalHistory> medicalHistoryView = new MedicalHistoryView();

        int option = -1;

        do {
            try {
                System.out.println();
                System.out.println("Select an option:");
                System.out.println();
                System.out.println("(1) View Patient Medical Records");
                System.out.println("(2) Update Patient Medical Records"); 
                System.out.println("(3) View Personal Schedule"); 
                System.out.println("(4) Set Availability for Appointments"); 
                System.out.println("(5) Accept or Decline Appointment Requests"); 
                System.out.println("(6) View Upcoming Appointments"); 
                System.out.println("(7) Record Appointment Outcome"); 
                System.out.println("(8) Logout"); 
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        doctorMedicalRecordUI.displayMedicalRecord();
                        break;
                    case 2:
                        doctorMedicalRecordUI.editMedicalRecord();
                        break;
                    case 3:
                        availabilityUI.viewSchedule();
                        break;
                    case 4:
                        availabilityUI.setSchedule();
                        break;
                    case 5:
                        doctorAppointmentUI.acceptDecline(appointmentView,appointmentListView);
                        break;
                    case 6:
                        doctorAppointmentUI.displayAppointments(appointmentListView);
                        break;
                    case 7:
                        appointmentOutcomeUI.createAppointmentOutcome(appointmentListView,appointmentOutcomeRecordView,medicalHistoryView);
                        break;
                    case 8:
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
        } while (option != 8);

        
    }
}
