package src.UI;

import src.Entity.Appointment;
import src.Entity.Doctor;
import src.Interface.IDisplayableView;
import src.Interface.IListDisplayableView;
import src.Enums.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.Controller.DoctorController;

/**
 * A user interface for managing a doctor's appointments, allowing the doctor to
 * view, accept, or decline appointment requests. Extends the AppointmentUI class to
 * include doctor-specific appointment functionalities.
 */
public class DoctorAppointmentUI extends AppointmentUI {

    /**
     * The doctor associated with this UI instance.
     */
    private Doctor doctor;

    /**
     * The scanner used for reading user input.
     */
    private Scanner scanner;

    /**
     * A formatter for displaying date in the format dd-MM-yyyy.
     */
    private DateTimeFormatter formatter;

    /**
     * The controller responsible for managing doctor-related operations.
     */
    private DoctorController doctorController;

    /**
     * Initializes a new instance of DoctorAppointmentUI with the specified doctor.
     * 
     * @param doctor The doctor associated with this UI instance.
     */
    public DoctorAppointmentUI(Doctor doctor) {
        this.doctor = doctor;
        scanner = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    }

    /**
     * Allows the doctor to accept or decline pending appointments.
     * 
     * @param appointmentView     The view interface for displaying individual appointments.
     * @param appointmentListView The view interface for displaying a list of appointments.
     * @param doctorController    The controller for handling doctor operations.
     */
    public void acceptDecline(IDisplayableView<Appointment> appointmentView, IListDisplayableView<Appointment> appointmentListView, DoctorController doctorController){

        ArrayList<Appointment> pendingAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.PENDING);

        if (pendingAppointments.isEmpty()) {
            System.out.println("You have no Pending Appointments.");
            return;
        } 
        
        appointmentListView.display(pendingAppointments);

        int appointmentChoice = -1;

        while (appointmentChoice == -1) {
            System.out.print("Please select an appointment to update status by entering the appointment number: ");
            try {
                appointmentChoice = scanner.nextInt();
                scanner.nextLine();
                if (appointmentChoice >= 1 && appointmentChoice <= pendingAppointments.size()) {
                    break;
                } else {
                    appointmentChoice = -1;
                    System.out.println("Invalid selection. Please enter a number between 1 and " + pendingAppointments.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        
        Appointment selectedAppointment = pendingAppointments.get(appointmentChoice - 1);
        System.out.println();
        System.out.println("Selected Appointment:");
        appointmentView.display(selectedAppointment);

        int statusChoice = -1;

        while (statusChoice == -1) {
            System.out.println();
            System.out.println("(1) Accept");
            System.out.println("(2) Deny");
            System.out.print("Please select to Accept or Deny the appointment by entering the corresponding number: ");
            try {
                statusChoice = scanner.nextInt();
                scanner.nextLine();
                if (statusChoice >= 1 && statusChoice <= 2) {
                    break;
                } else {
                    statusChoice = -1;
                    System.out.println("Invalid selection. Please enter a number between 1 and 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        if (statusChoice == 1) {
            selectedAppointment.setStatus(AppointmentStatus.CONFIRMED);
            System.out.println("Appointment Status updated to CONFIRMED");
        }
        else if (statusChoice == 2) {
            selectedAppointment.setStatus(AppointmentStatus.CANCELLED);
            System.out.println("Appointment Status updated to CANCELLED");
        }

    }

    /**
     * Displays a categorized list of appointments, showing pending, confirmed,
     * medicine pending, completed, and cancelled appointments.
     * 
     * @param appointmentListView The view interface for displaying a list of appointments.
     * @param doctorController    The controller for handling doctor operations.
     */
    public void displayAppointments(IListDisplayableView<Appointment> appointmentListView,DoctorController doctorController){
    
        ArrayList<Appointment> pendingAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.PENDING);
        ArrayList<Appointment> confirmedAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> medicinePendingAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.MEDICINE_PENDING);
        ArrayList<Appointment> completedAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.COMPLETED);
        ArrayList<Appointment> cancelledAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.CANCELLED);

        super.displayAppointments(appointmentListView, pendingAppointments, confirmedAppointments, medicinePendingAppointments, completedAppointments, cancelledAppointments);

    }   

}
