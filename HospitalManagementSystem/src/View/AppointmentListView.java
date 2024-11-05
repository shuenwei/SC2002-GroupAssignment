package View;

import Entity.Appointment;
import Interface.IListDisplayableView;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class AppointmentListView implements IListDisplayableView<Appointment>{

    public void display(ArrayList<Appointment> apptList){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int index = 1;

        String[] headers = new String[]{"No.","Patient Name", "Doctor Name", "Date", "Time", "Status"};
        System.out.println();
        System.out.printf("| %-3s | %-13s | %-13s | %-12s | %-6s | %-8s |%n", headers[0], headers[1], headers[2], headers[3], headers[4], headers[5]);
        System.out.println("--------------------------------------------------------------------------");
        for(Appointment a : apptList){
            System.out.printf("| %-3s | %-13s | %-13s | %-12s | %-6s | %-8s |%n", index, a.getPatient().getName(), a.getDoctor().getName(),a.getDate().format(formatter), a.getTime(), a.getStatus());
            index++;
        }
        
    }


}
