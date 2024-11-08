package View;

import Entity.Availability;
import java.time.DayOfWeek;

/**
 * The ScheduleView class is responsible for displaying the weekly schedule of availability.
 * This class shows the start and end times for each day of the week based on the provided availability.
 */
public class ScheduleView {

    /**
     * Displays the availability schedule for each day of the week.
     * It prints the start and end time of availability for each day, or indicates 
     * if a doctor is not available on that day.
     *
     * @param schedule An array of Availability objects, where each index corresponds to a day of the week (1 for Monday, 7 for Sunday).
     *                 Each Availability object holds the start and end times for that day, or null if the doctor is not available.
     */
    public void display(Availability[] schedule) {
        
        for (int i = 0; i < 7; i++) {
            DayOfWeek day = DayOfWeek.of(i + 1);

            if (schedule[i] != null) {
                Availability availability = schedule[i];
                System.out.println(day + ": " + availability.getStartTime() + " - " + availability.getEndTime());
            } else {
                System.out.println(day + ": Not Available");
            }
        }
    }
}
