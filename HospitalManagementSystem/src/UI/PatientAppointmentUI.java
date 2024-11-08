package UI;

import Entity.Appointment;
import Entity.Availability;
import Entity.Doctor;
import Entity.Patient;
import Enums.AppointmentStatus;
import Interface.IListDisplayableView;
import Interface.IDisplayableView;
import Repository.UserRepository;
import View.CommonView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.AppointmentController;
import Controller.PatientController;

/**
 * Provides a user interface for managing patient appointments, including viewing available slots, 
 * scheduling, rescheduling, canceling, and displaying appointments for a specific patient.
 * Extends the {@link AppointmentUI} class.
 */
public class PatientAppointmentUI extends AppointmentUI {

    /**
     * The current patient for whom the appointments are managed.
     */
    private Patient patient;

    /**
     * Controller for managing patient-related operations.
     */
    private PatientController patientController;

    /**
     * Scanner for reading user inputs.
     */
    private Scanner scanner;

    /**
     * Formatter for parsing and formatting dates.
     */
    private DateTimeFormatter formatter;


    /**
     * Initializes the UI with a patient and patient controller.
     *
     * @param patient          The patient for whom appointments are managed.
     * @param patientController Controller for handling patient-related operations.
     */
    public PatientAppointmentUI(Patient patient,PatientController patientController) {
        this.patient = patient;
        this.patientController = patientController;
        scanner = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    /**
     * Displays available time slots for the selected doctor on a specified date.
     */
    public void viewSlots() {
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
                CommonView.newPage();
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

    /**
     * Schedules an appointment for the patient with a selected doctor on a chosen date and time slot.
     *
     * @param appointmentController The controller managing appointment creation.
     */
    public void scheduleAppointment(AppointmentController appointmentController) {
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
        appointmentController.newAppointment(selectedDoctor, patient, selectedDate, selectedTime);

        System.out.println("Appointment scheduled with Dr. " + selectedDoctor.getName() + " on " + selectedDate.format(formatter) + " at " + selectedTime);
    }

    /**
     * Reschedules an existing appointment for the patient, allowing the patient to choose a new 
     * date and time slot.
     *
     * @param appointmentView      The view displaying the details of the appointment.
     * @param appointmentListView  The view displaying a list of appointments.
     */
    public void rescheduleAppointment(IDisplayableView<Appointment> appointmentView,IListDisplayableView<Appointment> appointmentListView){
        
        ArrayList<Appointment> appointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> pendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        appointments.addAll(pendingAppointments);

        if (appointments.isEmpty()) {
            System.out.println("You have no appointments to reschedule.");
            return;
        }

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
        appointmentView.display(selectedAppointment);
    } 

    /**
     * Displays all appointments for the patient, grouped by appointment status.
     *
     * @param appointmentListView The view displaying a list of appointments.
     */
    public void displayAppointments(IListDisplayableView<Appointment> appointmentListView){
        
        ArrayList<Appointment> pendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        ArrayList<Appointment> confirmedAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> medicinePendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.MEDICINE_PENDING);
        ArrayList<Appointment> completedAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.COMPLETED);
        ArrayList<Appointment> cancelledAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CANCELLED);

        super.displayAppointments(appointmentListView, pendingAppointments, confirmedAppointments, medicinePendingAppointments, completedAppointments, cancelledAppointments);
    
    }

    /**
     * Cancels an appointment by marking it with the status {@link AppointmentStatus#CANCELLED}.
     *
     * @param appointmentListView The view displaying a list of appointments.
     */
    public void cancelAppointment(IListDisplayableView<Appointment> appointmentListView){
        
        ArrayList<Appointment> appointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.CONFIRMED);
        ArrayList<Appointment> pendingAppointments = patientController.getAppointmentsByStatus(Enums.AppointmentStatus.PENDING);
        appointments.addAll(pendingAppointments);

        if (appointments.isEmpty()) {
            System.out.println("You have no appointments to cancel.");
            return;
        }

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

    /**
     * Displays the available time slots for the selected date, ensuring no conflict with existing appointments.
     *
     * @param selectedDate       The date selected for the appointment.
     * @param startTime          The starting time of availability.
     * @param endTime            The ending time of availability.
     * @param existingAppointments A list of existing appointments for the doctor.
     * @param availableSlots     A list to store available time slots.
     */
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

    /**
     * Prompts the user to select a doctor from the available list.
     *
     * @return The selected doctor, or null if no doctors are available.
     */
    public Doctor selectDoctor() {
    
        List<Doctor> doctors = UserRepository.getAllDoctors();

        CommonView.newPage();

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

    /**
     * Prompts the user to enter a date for scheduling or rescheduling an appointment.
     *
     * @return The selected date.
     */
    public LocalDate selectDate() {
        LocalDate appointmentDate = null;

        while (true) {
            CommonView.newPage();
            System.out.print("Enter appointment date (dd-mm-yyyy): ");
            String input = scanner.nextLine();

            try {
                appointmentDate = LocalDate.parse(input, formatter);
                if (appointmentDate.isBefore(LocalDate.now())) {
                    System.out.println("Invalid date. You may only book an appointment from today onwards.");
                } else {
                    return appointmentDate;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in 'dd-mm-yyyy' format.");
            }
        }
    }

}
