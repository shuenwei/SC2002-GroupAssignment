package UI;

import Controller.AvailabilityController;
import Controller.DoctorController;
import Controller.PatientController;
import Entity.Appointment;
import Entity.Doctor;
import Entity.Patient;
import Enums.AppointmentStatus;//
import View.AppointmentListView;
import View.AppointmentView;
import Repository.UserRepository;
import View.ScheduleView;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class AvailabilityUI {
    private Doctor doctor;
    private Scanner scanner;
    private DoctorController doctorController;

    public AvailabilityUI(Doctor doctor){
        this.doctor = doctor;
        scanner = new Scanner(System.in);
        doctorController = new DoctorController(doctor);
    }

    public void viewSchedule(){
        AvailabilityController availabilityController = new AvailabilityController(doctor);
        ScheduleView scheduleView = new ScheduleView();
        scheduleView.display(availabilityController.getSchedule());
    }

    public void setSchedule(){
        AvailabilityController availabilityController = new AvailabilityController(doctor);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        int day = -1;

        do {
            try {
                System.out.println();
                System.out.println("Select day to set availability:");
                System.out.println();
                System.out.println("(1) Monday");
                System.out.println("(2) Tuesday"); 
                System.out.println("(3) Wednesday"); 
                System.out.println("(4) Thursday"); 
                System.out.println("(5) Friday"); 
                System.out.println("(6) Saturday"); 
                System.out.println("(7) Sunday"); 
                System.out.println("---------------------------------"); 
                System.out.println("(8) Confirm Updated Availability"); 
                day = scanner.nextInt();
                scanner.nextLine();

                if (day >= 1 && day <= 7) {
                    boolean validTime = false;
                    LocalTime startTime = null;
                    LocalTime endTime = null;
                    System.out.println();
                    System.out.println("Editing availability for " + DayOfWeek.of(day) + ":");
                    System.out.println("---------------------------------"); 
    
                    while (!validTime) {
                        while (startTime == null) {
                            try {
                                System.out.print("Enter Start Time (e.g. 08:00 for 8.00AM): ");
                                String startTimeStr = scanner.nextLine();
                                startTime = LocalTime.parse(startTimeStr, timeFormatter);

                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid time format. Please enter time in HH:MM format (08:00 for 08.00AM).");
                            }
                        }

                        while (endTime == null) {
                            try {
                                System.out.print("Enter End Time (e.g. 14:00 for 2.00PM): ");
                                String endTimeStr = scanner.nextLine();
                                endTime = LocalTime.parse(endTimeStr, timeFormatter);
        
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid time format. Please enter time in HH:MM format (08:00 for 08.00AM).");
                            }
                        }

                        if (endTime.isAfter(startTime)) {
                            validTime = true;
                        } else {
                            startTime = null;
                            endTime = null;
                            System.out.println("Invalid time frame. End time must be after Start time.");
                        }
                    }
            
                    DayOfWeek dayOfWeek = DayOfWeek.of(day);
                    availabilityController.setSchedule(dayOfWeek, startTime, endTime);
                    
                } else if (day != 8) {
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.next();
            }
        } while (day != 8);
    }

    public void acceptDecline(){

        ArrayList<Appointment> pendingAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);

        if (pendingAppointments.isEmpty()) {
            System.out.println("You have no Pending Appointments.");
            return;
        } 
        
        AppointmentListView appointmentListView = new AppointmentListView();
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
        AppointmentView appointmentView = new AppointmentView();
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

    public void displayAppointments(){
    
    ArrayList<Appointment> pendingAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
    ArrayList<Appointment> confirmedAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
    ArrayList<Appointment> cancelledAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.CANCELLED);
    ArrayList<Appointment> completedAppointments = doctorController.getAppointmentsByStatus(Enums.AppointmentStatus.COMPLETED);

    int noOfPendingAppointments = pendingAppointments.size();
    int noOfConfirmedAppointments = confirmedAppointments.size();
    int noOfCancelledAppointments = cancelledAppointments.size();
    int noOfCompletedAppointments = completedAppointments.size();

    if (doctor.getAppointments().size() == 0 ) {
        System.out.println("You have no appointments.");
    } 
    else {
        AppointmentListView appointmentListView = new AppointmentListView();

        System.out.println("You have:"); 
        System.out.println(noOfPendingAppointments + " Pending Appointments");
        System.out.println(noOfConfirmedAppointments + " Confirmed Appointments");
        System.out.println(noOfCancelledAppointments + " Cancelled Appointments");
        System.out.println(noOfCompletedAppointments + " Completed Appointments");
        System.out.println(); 
        System.out.println("Which would you like to view?");
        System.out.println("(1) Pending");
        System.out.println("(2) Confirmed");
        System.out.println("(3) Cancelled");
        System.out.println("(4) Completed");

        int choice = -1;
        while (choice == -1) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        if (noOfPendingAppointments == 0) {
                            System.out.println("You have no pending appointments.");
                        }
                        else {
                            appointmentListView.display(pendingAppointments);
                        }
                        break;
                    case 2:
                        if (noOfConfirmedAppointments == 0) {
                            System.out.println("You have no confirmed appointments.");
                        }
                        else {
                            appointmentListView.display(confirmedAppointments);
                        }
                        break;
                    case 3:
                        if (noOfCancelledAppointments == 0) {
                            System.out.println("You have no cancelled appointments.");
                        }
                        else {
                            appointmentListView.display(cancelledAppointments);
                        }
                        break;
                    case 4:
                        if (noOfCompletedAppointments == 0) {
                            System.out.println("You have no completed appointments.");
                        }
                        else {
                            appointmentListView.display(completedAppointments);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                        choice = -1;
                        break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine();
                choice = -1;
            }
        
        }
    }

}
}


