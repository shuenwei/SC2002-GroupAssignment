package Entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * The Availability class represents a specific time slot for a particular day of the week,
 * including the start and end time.
 */
public class Availability {

    /**
     * The day of the week for this availability slot (e.g., Monday, Tuesday).
     */
    private DayOfWeek day;

    /**
     * The start time of the availability slot.
     */
    private LocalTime startTime;

    /**
     * The end time of the availability slot.
     */
    private LocalTime endTime;

    /**
     * Constructs an Availability object with the specified day, start time, and end time.
     *
     * @param day       The day of the week for the availability slot.
     * @param startTime The start time of the availability slot.
     * @param endTime   The end time of the availability slot.
     */
    public Availability(DayOfWeek day,LocalTime startTime,LocalTime endTime){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Sets the day of the week for this availability slot.
     *
     * @param day The day of the week to set.
     */
    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    /**
     * Sets the start time of the availability slot.
     *
     * @param startTime The start time to set.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the availability slot.
     *
     * @param endTime The end time to set.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the day of the week for this availability slot.
     *
     * @return The day of the week.
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Returns the start time of the availability slot.
     *
     * @return The start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the availability slot.
     *
     * @return The end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
