package Controller;

import Entity.Availability;
import Entity.Doctor;

import java.util.HashMap;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class AvailabilityController {
    
    private Doctor doctor;
    
    public AvailabilityController(Doctor doctor) {
        this.doctor = doctor;
    }

    public Availability[] getSchedule() {
        return doctor.getSchedule();
    }

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
            doctor.setAvailability(day,newAvailability);
            System.out.println("Availability set for " + day + ": " + startTime + " - " + endTime);
        }
    }
}
