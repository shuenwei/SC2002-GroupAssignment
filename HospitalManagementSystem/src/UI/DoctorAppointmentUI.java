package UI;

import Entity.Appointment;
import Entity.Doctor;
import Interface.IDisplayableView;
import Interface.IListDisplayableView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.DoctorController;

public class DoctorAppointmentUI extends AppointmentUI {

    private Doctor doctor;
    private Scanner scanner;
    private DateTimeFormatter formatter;
    private DoctorController doctorController;

    public DoctorAppointmentUI(Doctor doctor) {
        this.doctor = doctor;
        scanner = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    }

    public void acceptDecline(IDisplayableView<Appointment> appointmentView, IListDisplayableView<Appointment> appointmentListView, DoctorController doctorController){

        ArrayList<Appointment> pendingAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);

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
                if (appointmentChoice >= 1 && appointmentChoice <= 2) {
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
            selectedAppointment.setStatus(Enums.AppointmentStatus.CONFIRMED);
            System.out.println("Appointment Status updated to CONFIRMED");
        }
        else if (statusChoice == 2) {
            selectedAppointment.setStatus(Enums.AppointmentStatus.CANCELLED);
            System.out.println("Appointment Status updated to CANCELLED");
        }

    }

    public void displayAppointments(IListDisplayableView<Appointment> appointmentListView,DoctorController doctorController){
    
        ArrayList<Appointment> pendingAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        ArrayList<Appointment> confirmedAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> medicinePendingAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.MEDICINE_PENDING);
        ArrayList<Appointment> completedAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.COMPLETED);
        ArrayList<Appointment> cancelledAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.CANCELLED);

        super.displayAppointments(appointmentListView, pendingAppointments, confirmedAppointments, medicinePendingAppointments, completedAppointments, cancelledAppointments);

    }   

}
