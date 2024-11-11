package UI;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Entity.Appointment;
import Interface.IListDisplayableView;

/**
 * The {@code AppointmentUI} class provides a user interface to display appointments categorized by their status.
 * It allows users to view appointments based on their state (Pending, Confirmed, Medicine Pending, Completed, or Cancelled).
 */
public class AppointmentUI {

    /**
     * Displays a menu for the user to view appointments by status. Lists the number of appointments in each status,
     * and prompts the user to select a category to view its appointments.
     *
     * @param appointmentListView         the view interface to display appointments
     * @param pendingAppointments         list of pending appointments
     * @param confirmedAppointments       list of confirmed appointments
     * @param medicinePendingAppointments list of appointments with pending medication
     * @param completedAppointments       list of completed appointments
     * @param cancelledAppointments       list of cancelled appointments
     */
    public void displayAppointments(IListDisplayableView<Appointment> appointmentListView,
                                    ArrayList<Appointment> pendingAppointments,
                                    ArrayList<Appointment> confirmedAppointments,
                                    ArrayList<Appointment> medicinePendingAppointments,
                                    ArrayList<Appointment> completedAppointments,
                                    ArrayList<Appointment> cancelledAppointments) {
        Scanner scanner = new Scanner(System.in);

        int noOfPendingAppointments = pendingAppointments.size();
        int noOfConfirmedAppointments = confirmedAppointments.size();
        int noOfMedicinePendingAppointments = medicinePendingAppointments.size();
        int noOfCompletedAppointments = completedAppointments.size();
        int noOfCancelledAppointments = cancelledAppointments.size();
        int totalNoOfAppointments = noOfPendingAppointments + noOfConfirmedAppointments +
                noOfMedicinePendingAppointments + noOfCompletedAppointments + noOfCancelledAppointments;

        if (totalNoOfAppointments == 0) {
            System.out.println("You have no appointments.");
        } else {
            System.out.println("You have:");
            System.out.println(noOfPendingAppointments + " Pending Appointments");
            System.out.println(noOfConfirmedAppointments + " Confirmed Appointments");
            System.out.println(noOfMedicinePendingAppointments + " Medicine Pending Appointments");
            System.out.println(noOfCompletedAppointments + " Completed Appointments");
            System.out.println(noOfCancelledAppointments + " Cancelled Appointments");
            System.out.println();
            System.out.println("Which would you like to view?");
            System.out.println("(1) Pending");
            System.out.println("(2) Confirmed");
            System.out.println("(3) Medicine Pending");
            System.out.println("(4) Completed");
            System.out.println("(5) Cancelled");

            int choice = -1;
            while (choice == -1) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            if (noOfPendingAppointments == 0) {
                                System.out.println("You have no pending appointments.");
                            } else {
                                appointmentListView.display(pendingAppointments);
                            }
                            break;
                        case 2:
                            if (noOfConfirmedAppointments == 0) {
                                System.out.println("You have no confirmed appointments.");
                            } else {
                                appointmentListView.display(confirmedAppointments);
                            }
                            break;
                        case 3:
                            if (noOfMedicinePendingAppointments == 0) {
                                System.out.println("You have no Medicine Pending appointments.");
                            } else {
                                appointmentListView.display(medicinePendingAppointments);
                            }
                            break;
                        case 4:
                            if (noOfCompletedAppointments == 0) {
                                System.out.println("You have no completed appointments.");
                            } else {
                                appointmentListView.display(completedAppointments);
                            }
                            break;
                        case 5:
                            if (noOfCancelledAppointments == 0) {
                                System.out.println("You have no cancelled appointments.");
                            } else {
                                appointmentListView.display(cancelledAppointments);
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a number between 1 and 5.");
                            choice = -1;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    scanner.nextLine();
                    choice = -1;
                }
            }
        }
    }
}
