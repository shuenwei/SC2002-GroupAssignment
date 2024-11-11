package src.UI;

import src.Controller.AvailabilityController;
import src.Entity.Doctor;
import src.View.CommonView;
import src.View.ScheduleView;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code AvailabilityUI} class provides a user interface for managing a doctor's availability schedule.
 * It allows viewing and setting availability, with input validation for days and times.
 */
public class AvailabilityUI {
    private Doctor doctor;
    private Scanner scanner;

    /**
     * Constructs an {@code AvailabilityUI} with the specified doctor.
     *
     * @param doctor the doctor for whom the availability schedule is managed
     */
    public AvailabilityUI(Doctor doctor) {
        this.doctor = doctor;
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the doctor's current availability schedule for appointments.
     *
     * @param availabilityController the controller that manages and retrieves the doctor's schedule
     */
    public void viewSchedule(AvailabilityController availabilityController) {
        CommonView.newPage();
        System.out.println();
        System.out.println("Dr. " + doctor.getName() + "'s Availability for Appointments:");
        ScheduleView scheduleView = new ScheduleView();
        scheduleView.display(availabilityController.getSchedule());
    }

    /**
     * Allows the user to set the doctor's availability schedule.
     * The user is prompted to select a day of the week and input a start and end time.
     * Validates that the end time is after the start time and allows confirmation to save changes.
     *
     * @param availabilityController the controller that manages and updates the doctor's schedule
     */
    public void setSchedule(AvailabilityController availabilityController) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        int day = -1;

        do {
            try {
                System.out.println();
                System.out.println("Select day to set availability:");
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
}
