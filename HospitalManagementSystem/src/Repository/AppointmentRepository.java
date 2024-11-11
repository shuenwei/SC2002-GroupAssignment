package src.Repository;

import src.Entity.*;
import java.util.ArrayList;

/**
 * The AppointmentRepository class manages the storage and retrieval of appointment data.
 * It provides methods to add new appointments and retrieve all existing appointments.
 */
public class AppointmentRepository {
    
    private static ArrayList<Appointment> Appointments = new ArrayList<>();

    /**
     * Adds a new appointment to the repository.
     *
     * @param appointment The Appointment object to be added.
     */
    public static void add(Appointment appointment) {
        Appointments.add(appointment);
    }

    /**
     * Retrieves all appointments stored in the repository.
     *
     * @return An ArrayList of all Appointment objects.
     */
    public static ArrayList<Appointment> getAllAppointments() {
        return Appointments;
    }
}
