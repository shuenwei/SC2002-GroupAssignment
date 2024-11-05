package Repository;

import java.util.ArrayList;

import Entity.*;

public class AppointmentRepository {
    private static ArrayList<Appointment> Appointments;

    public static void add(Appointment appointment) {
        Appointments.add(appointment);
    }

    public static ArrayList<Appointment> getAllAppointments() {
        return Appointments;
    }
}
