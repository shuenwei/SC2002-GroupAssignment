package src.Controller;

import src.Entity.Availability;
import src.Entity.Doctor;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * The AvailabilityController class manages a doctor's availability schedule,
 * allowing retrieval and updates to the schedule.
 */
public class AvailabilityController {
    
    private Doctor doctor;

    /**
     * Constructs an AvailabilityController for the specified doctor.
     *
     * @param doctor The doctor whose availability is managed by this controller.
     */
    public AvailabilityController(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Retrieves the weekly availability schedule of the doctor.
     *
     * @return An array of Availability objects representing the doctor's schedule for each day of the week.
     */
    public Availability[] getSchedule() {
        return doctor.getSchedule();
    }

    /**
     * Sets or updates the availability of the doctor for a specified day, start time, and end time.
     * If availability exists for the specified day, it is updated; otherwise, a new availability entry is created.
     *
     * @param day        The day of the week for which availability is being set.
     * @param startTime  The start time of availability for the specified day.
     * @param endTime    The end time of availability for the specified day.
     */
    public void setSchedule(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        Availability[] schedule = doctor.getSchedule();
        int index = day.getValue() - 1;
    
        if (schedule[index] != null) {
            Availability updatedAvailability = schedule[index];
            updatedAvailability.setStartTime(startTime);
            updatedAvailability.setEndTime(endTime);
            System.out.println("Availability updated for " + day + ": " + startTime + " - " + endTime);
        } else {
            Availability newAvailability = new Availability(day, startTime, endTime);
            doctor.setAvailability(day, newAvailability);
            System.out.println("Availability set for " + day + ": " + startTime + " - " + endTime);
        }
    }
}
