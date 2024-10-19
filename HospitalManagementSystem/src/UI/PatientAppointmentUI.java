package UI;

import Entity.Appointment;
import Entity.Availability;
import Entity.Doctor;
import Entity.Patient;
import Enums.AppointmentStatus;
import Repository.UserRepository;
import Controller.AvailabilityController;
import View.ScheduleView;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.time.format.DateTimeParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PatientAppointmentUI {

    private Patient patient;
    private Scanner scanner;
    private DateTimeFormatter formatter;

    public PatientAppointmentUI(Patient patient) {
        this.patient = patient;
        scanner = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void viewSlots() {
        
        Doctor selectedDoctor = selectDoctor();

        if (selectedDoctor == null) {
            return;
        }

        System.out.println();
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
                    scanner.next();
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
                        System.out.println("Invalid selection. Please enter a number between 1 and 2");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                    scanner.next();
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

        System.out.println("Appointment scheduled with Dr. " + selectedDoctor.getName() + " on " + selectedDate.format(formatter) + " at " + selectedTime);
    }

    public void displaySlots(LocalDate selectedDate ,LocalTime startTime, LocalTime endTime,ArrayList<Appointment> existingAppointments,ArrayList<LocalTime> availableSlots) {
        while (startTime.isBefore(endTime)) {

            int slotOccupied = 0;

            for (int i = 0; i < existingAppointments.size(); i++) {
                Appointment appointment = existingAppointments.get(i);
                if (appointment.getDate().equals(selectedDate) && appointment.getTime().equals(startTime)) {
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
                scanner.nextLine();
            }
        }
    }
}
