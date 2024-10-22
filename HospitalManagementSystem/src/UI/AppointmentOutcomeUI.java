package UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import Controller.DoctorController;
import Entity.Appointment;
import Entity.AppointmentOutcomeRecord;
import Controller.AppointmentOutcomeController;
import Entity.Doctor;
import Entity.PrescribedMedication;
import Enums.AppointmentStatus;
import View.AppointmentListView;

public class AppointmentOutcomeUI {

    private AppointmentOutcomeController appointment;
    private Doctor doctor;
    private DoctorController doctorController;
    private Scanner scanner;
    Scanner sc = new Scanner(System.in);

    public AppointmentOutcomeUI(Doctor doctor) {
        this.doctor = doctor;
        scanner = new Scanner(System.in);
        doctorController = new DoctorController(doctor);
    }

    public void createAppointmentOutcome() {
        ArrayList<Appointment> ConfirmedAppointments = doctorController.getAppointmentsByStatus(AppointmentStatus.CONFIRMED);

        if (ConfirmedAppointments.isEmpty()) {
            System.out.println("You have no Confirmed Appointments.");
            return;
        }

        AppointmentListView appointmentListView = new AppointmentListView();
        appointmentListView.display(ConfirmedAppointments);

        int appointmentChoice = -1;

        while (appointmentChoice == -1) {
            System.out.print("Please select an appointment to create appointment outcome record for by entering the corresponding number: ");
            try {
                appointmentChoice = scanner.nextInt();
                scanner.nextLine();
                if (appointmentChoice >= 1 && appointmentChoice <= ConfirmedAppointments.size()) {
                    break;
                } else {
                    appointmentChoice = -1;
                    System.out.println("Invalid selection. Please enter a number between 1 and " + ConfirmedAppointments.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        Appointment selectedAppointment = ConfirmedAppointments.get(appointmentChoice - 1);

        ArrayList<String> prescribedMedications = new ArrayList<>();
        ArrayList<PrescribedMedication> prescribedMedicationsList = new ArrayList<>();
        System.out.println("Create Appointment Outcome");
        System.out.println("Enter type of service administered: ");
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

        AppointmentOutcomeRecord record = AppointmentOutcomeController.createAppointmentOutcomeRecord(service, notes, prescribedMedicationsList,selectedAppointment);
        selectedAppointment.setAppointmentOutcomeRecord(record);
        if(selectedAppointment.getAppointmentOutcomeRecord().getPrescribedMedications().size() == 0) {
            selectedAppointment.setStatus(AppointmentStatus.COMPLETED);
        }
        else {
            selectedAppointment.setStatus(AppointmentStatus.MEDICINE_PENDING);
        }

    }


}
