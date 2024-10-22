package UI;

import java.util.Scanner;
import java.util.ArrayList;

import Entity.Appointment;
import Entity.AppointmentOutcomeRecord;
import Controller.AppointmentOutcomeController;
import Entity.PrescribedMedication;

public class AppointmentOutcomeUI {

    private Appointment appointment;
    Scanner sc = new Scanner(System.in);

    public AppointmentOutcomeUI(Appointment appointment) {
        this.appointment = appointment;
    }

    public void createAppointmentOutcome() {
        ArrayList<String> prescribedMedications = new ArrayList<>();
        ArrayList<PrescribedMedication> prescribedMedicationsList = new ArrayList<>();
        System.out.println("Create Appointment Outcome");
        System.out.print("Enter type of service administered: ");
        String service = sc.nextLine();
        System.out.println("Enter consultation notes: ");
        String notes = sc.nextLine();
        while(true) {
            System.out.print("Enter names of medication to be prescribed: ");
            String name = sc.nextLine();
            try{
                Integer.parseInt(name);
                break;
            } catch (NumberFormatException e) {
                boolean exist = AppointmentOutcomeController.checkMedicationExist(name);
                if(exist) {
                    prescribedMedications.add(name);
                    System.out.println("Medication added: " + name);
                }
                else{
                    System.out.println("Medication does not exist");
                }
            }
        }
        for(String medication : prescribedMedications){
            PrescribedMedication prescribedMedication = new PrescribedMedication(medication);
            prescribedMedicationsList.add(prescribedMedication);
        }

        AppointmentOutcomeRecord record = AppointmentOutcomeController.createAppointmentOutcomeRecord(service, notes, prescribedMedicationsList);
        appointment.setAppointmentOutcomeRecord(record);

    }
}
