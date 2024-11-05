package View;

import Entity.Appointment;
import Interface.IDisplayableView;
import java.time.format.DateTimeFormatter;

public class AppointmentView implements IDisplayableView<Appointment>{


    public void display(Appointment a){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String[] headers = new String[]{"Patient Name", "Doctor Name", "Date", "Time", "Status"};
        System.out.println();
        System.out.printf("| %-13s | %-13s | %-12s | %-6s | %-8s |%n", headers[0], headers[1], headers[2], headers[3], headers[4]);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-13s | %-13s | %-12s | %-6s | %-8s |%n", a.getPatient().getName(), a.getDoctor().getName(),a.getDate().format(formatter), a.getTime(), a.getStatus());
    
    }

}
