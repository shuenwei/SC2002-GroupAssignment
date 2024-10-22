package UI;

import Entity.Appointment;
import Entity.Availability;
import Entity.Doctor;
import Entity.Patient;
import Enums.AppointmentStatus;
import Interface.IListDisplayableView;
import Repository.UserRepository;
import View.AppointmentListView;
import View.AppointmentView;
import View.CommonView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.PatientController;

public class PatientAppointmentUI {

    private Patient patient;
    private Scanner scanner;
    private DateTimeFormatter formatter;

    public PatientAppointmentUI(Patient patient,IListDisplayableView<Appointment> listView) {
        this.patient = patient;
        scanner = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.listView = listView; 
    }

    public void viewSlots() {
        
        Doctor selectedDoctor = selectDoctor();

        if (selectedDoctor == null) {
            return;
        }

        AvailabilityUI availabilityUI = new AvailabilityUI(selectedDoctor);
        availabilityUI.viewSchedule();
        System.out.println();

        LocalDate selectedDate = null;
        Availability availability = null;
        int option = 1;

        while (option == 1) {
            selectedDate = selectDate();

            availability = selectedDoctor.getAvailability(selectedDate.getDayOfWeek());
            if (availability == null) {
                System.out.println("Doctor is not available on this day. Would you like to search for another date?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (option == 2) {
                        return;
                    }
                    else if (option != 1) {
                        System.out.println("Invalid selection. Please enter a number between 1 and 2");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                    scanner.nextLine();
                }
            }
            else {
                break;
            }
        }

        LocalTime startTime = availability.getStartTime();
        LocalTime endTime = availability.getEndTime();
        ArrayList<LocalTime> availableSlots = new ArrayList<>();
        ArrayList<Appointment> existingAppointments = selectedDoctor.getAppointments();
        displaySlots(selectedDate ,startTime, endTime,existingAppointments,availableSlots);
    }

    public void scheduleAppointment() {
        Doctor selectedDoctor = selectDoctor();
        
        if (selectedDoctor == null) {
            return;
        }
        
        AvailabilityUI availabilityUI = new AvailabilityUI(selectedDoctor);
        availabilityUI.viewSchedule();

        LocalDate selectedDate = null;
        Availability availability = null;
        int option = 1;

        while (option == 1) {
            selectedDate = selectDate();

            availability = selectedDoctor.getAvailability(selectedDate.getDayOfWeek());
            if (availability == null) {
                System.out.println("Doctor is not available on this day. Would you like to search for another date?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (option == 2) {
                        return;
                    }
                    else if (option != 1) {
                        System.out.println("Invalid selection. Please enter '1' or '2'.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                    scanner.nextLine();
                }
            }
            else {
                break;
            }
        }

        LocalTime startTime = availability.getStartTime();
        LocalTime endTime = availability.getEndTime();
        ArrayList<LocalTime> availableSlots = new ArrayList<>();
        ArrayList<Appointment> existingAppointments = selectedDoctor.getAppointments();

        displaySlots(selectedDate ,startTime, endTime,existingAppointments,availableSlots);

        int slotChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please select a time slot by entering the corresponding number: ");
            try {
                slotChoice = scanner.nextInt();
                scanner.nextLine();
                if (slotChoice >= 1 && slotChoice <= availableSlots.size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + availableSlots.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        // Schedule the Appointment
        LocalTime selectedTime = availableSlots.get(slotChoice - 1);
        Appointment appointment = new Appointment(selectedDoctor, this.patient, selectedDate, selectedTime, AppointmentStatus.PENDING, null);
        selectedDoctor.addAppointment(appointment);
        patient.addAppointment(appointment);

        System.out.println("Appointment scheduled with Dr. " + selectedDoctor.getName() + " on " + selectedDate.format(formatter) + " at " + selectedTime);
    }


    public void rescheduleAppointment(){

        PatientController patientController = new PatientController(patient);
        
        ArrayList<Appointment> appointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> pendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        appointments.addAll(pendingAppointments);

        if (appointments.isEmpty()) {
            System.out.println("You have no appointments to reschedule.");
            return;
        }

        AppointmentListView appointmentListView = new AppointmentListView();
        appointmentListView.display(appointments);

        int index;

        while (true) {
            try {
                System.out.print("Enter the appointment number you wish to reschedule:");
                index = scanner.nextInt();
                scanner.nextLine();
                if (index < 1 || index > appointments.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + appointments.size());
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        Appointment selectedAppointment = appointments.get(index -1 );
        Doctor selectedDoctor = selectedAppointment.getDoctor();

        AvailabilityUI availabilityUI = new AvailabilityUI(selectedDoctor);
        availabilityUI.viewSchedule();

        LocalDate selectedDate = null;
        Availability availability = null;
        int option = 1;

        while (option == 1) {
            selectedDate = selectDate();

            availability = selectedDoctor.getAvailability(selectedDate.getDayOfWeek());
            if (availability == null) {
                System.out.println("Doctor is not available on this day. Would you like to search for another date?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (option == 2) {
                        return;
                    }
                    else if (option != 1) {
                        System.out.println("Invalid selection. Please enter a number between 1 and 2");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                    scanner.nextLine();
                }
            }
            else {
                break;
            }
        }

        LocalTime startTime = availability.getStartTime();
        LocalTime endTime = availability.getEndTime();
        ArrayList<LocalTime> availableSlots = new ArrayList<>();
        ArrayList<Appointment> existingAppointments = selectedDoctor.getAppointments();

        displaySlots(selectedDate ,startTime, endTime,existingAppointments,availableSlots);

        int slotChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please select a time slot by entering the corresponding number: ");
            try {
                slotChoice = scanner.nextInt();
                scanner.nextLine();
                if (slotChoice >= 1 && slotChoice <= availableSlots.size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + availableSlots.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        // Reschedule the Appointment
        LocalTime selectedTime = availableSlots.get(slotChoice - 1);
        selectedAppointment.setDate(selectedDate);
        selectedAppointment.setTime(selectedTime);
        selectedAppointment.setStatus(Enums.AppointmentStatus.PENDING);
        System.out.println("Appointment has been rescheduled successfully.");
        System.out.println();
        System.out.println("Here are your new appointment details:");
        AppointmentView appointmentView = new AppointmentView();
        appointmentView.display(selectedAppointment);
        CommonView.pressEnterToContinue();
    } 

    public void displayAppointments(){
        PatientController patientController = new PatientController(patient);
        
        ArrayList<Appointment> pendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        ArrayList<Appointment> confirmedAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> cancelledAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CANCELLED);
        ArrayList<Appointment> completedAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.COMPLETED);

        int noOfPendingAppointments = pendingAppointments.size();
        int noOfConfirmedAppointments = confirmedAppointments.size();
        int noOfCancelledAppointments = cancelledAppointments.size();
        int noOfCompletedAppointments = completedAppointments.size();

        if (patient.getAppointments().size() == 0 ) {
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

    public void cancelAppointment(){
            
        PatientController patientController = new PatientController(patient);
        
        ArrayList<Appointment> appointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> pendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        appointments.addAll(pendingAppointments);

        if (appointments.isEmpty()) {
            System.out.println("You have no appointments to cancel.");
            return;
        }

        AppointmentListView appointmentListView = new AppointmentListView();
        appointmentListView.display(appointments);

        int choice = -1;

        while (choice == -1) {
            System.out.println("Enter the appointment number you wish to cancel:");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= appointments.size()) {
                    break;
                } else {
                    choice = -1;
                    System.out.println("Invalid selection. Please enter a number between 1 and " + appointments.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        Appointment selectedAppointment = appointments.get(choice-1);
        selectedAppointment.setStatus(Enums.AppointmentStatus.CANCELLED);
        System.out.println("Appointment has been cancelled successfully.");
    }

    public void displaySlots(LocalDate selectedDate ,LocalTime startTime, LocalTime endTime,ArrayList<Appointment> existingAppointments,ArrayList<LocalTime> availableSlots) {
        while (startTime.isBefore(endTime)) {

            int slotOccupied = 0;

            for (int i = 0; i < existingAppointments.size(); i++) {
                Appointment appointment = existingAppointments.get(i);
                if (appointment.getDate().equals(selectedDate) && appointment.getTime().equals(startTime) && !appointment.getStatus().equals(Enums.AppointmentStatus.CANCELLED)) {
                    slotOccupied++;
                    break;
                }
            }

            if (slotOccupied == 0) {
                availableSlots.add(startTime);
            }

            startTime = startTime.plusMinutes(30);
        }

        if (availableSlots.isEmpty()) {
            System.out.println("No available time slots for this day.");
        }
        else {
            System.out.println("Available Time Slots:");
            for (int i = 0; i < availableSlots.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + availableSlots.get(i));
            }
        }
    }

    public Doctor selectDoctor() {
    
        List<Doctor> doctors = UserRepository.getAllDoctors();

        if (doctors.isEmpty()) {
            System.out.println("No doctors are available.");
            return null;
        }

        System.out.println("Available Doctors:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println("("  +  (i + 1) + ") Dr. " + doctors.get(i).getName());
        }

        while (true) {
            try {
                System.out.print("Please select a doctor by entering the corresponding number: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > doctors.size()) {
                    System.out.println("Invalid selection. No doctors found for that input.");
                }
                else {
                    return doctors.get(choice - 1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public LocalDate selectDate() {
        LocalDate appointmentDate = null;

        while (true) {
            System.out.print("Enter appointment date (dd-mm-yyyy): ");
            String input = scanner.nextLine();

            try {
                appointmentDate = LocalDate.parse(input, formatter);
                return appointmentDate;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in 'dd-mm-yyyy' format.");
            }
        }
    }

}
