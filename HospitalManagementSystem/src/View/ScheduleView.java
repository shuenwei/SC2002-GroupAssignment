package View;

import Entity.Availability;
import java.time.DayOfWeek;

public class ScheduleView {

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
