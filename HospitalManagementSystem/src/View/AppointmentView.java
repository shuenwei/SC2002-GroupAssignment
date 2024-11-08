package View;

import Entity.Appointment;
import Interface.IDisplayableView;
import java.time.format.DateTimeFormatter;

/**
 * The AppointmentView class implements the IDisplayableView interface for displaying
 * details of a single Appointment. It outputs the appointment's patient and doctor names,
 * the date of the appointment, the appointment time, and the status of the appointment.
 */
public class AppointmentView implements IDisplayableView<Appointment>{

    /**
     * Displays the details of the given Appointment in a formatted table.
     * The displayed information includes the following fields:
     * - Patient Name
     * - Doctor Name
     * - Appointment Date
     * - Appointment Time
     * - Appointment Status
     *
     * @param a the Appointment to be displayed
     */
    @Override
    public void display(Appointment a){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String[] headers = new String[]{"Patient Name", "Doctor Name", "Date", "Time", "Status"};
        System.out.println();
        System.out.printf("| %-13s | %-13s | %-12s | %-6s | %-8s |%n", headers[0], headers[1], headers[2], headers[3], headers[4]);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-13s | %-13s | %-12s | %-6s | %-8s |%n", a.getPatient().getName(), a.getDoctor().getName(),a.getDate().format(formatter), a.getTime(), a.getStatus());
    
    }

}
