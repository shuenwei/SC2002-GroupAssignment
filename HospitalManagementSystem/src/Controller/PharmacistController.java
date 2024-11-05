package Controller;

import Entity.Appointment;
import Entity.Doctor;
import Entity.Inventory;
import Entity.Medication;
import Entity.Pharmacist;
import Entity.PrescribedMedication;
import Entity.Request;
import Enums.AppointmentStatus;
import Enums.PrescriptionStatus;
import Repository.UserRepository;
import java.util.ArrayList;
import java.util.Scanner;

public class PharmacistController extends StaffController {

    private Pharmacist pharmacist;
    private Medication med;
    private Scanner sc;

    public PharmacistController(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
        sc = new Scanner(System.in);
    }

    public void submitRequest(String requestMedicine) {
        Medication medicine = Inventory.get(requestMedicine);
        if (medicine != null) {
            if (medicine.getIsLowStock() != true) {
                System.out.printf("%s is not low on stock%n", requestMedicine);
            }
            else {
                Request newRequest = new Request(requestMedicine);
                Inventory.addRequest(newRequest);
                System.out.printf("Successfully submitted replenishment request for %s%n", requestMedicine);
            }
        }
    }

    public void updatePrescription(Appointment a){

        int index = 1;

        System.out.println("Which Medicine number would you like to dispense?");

        for(PrescribedMedication m : a.getAppointmentOutcomeRecord().getPrescribedMedications()){

            if (m.getStatus() == Enums.PrescriptionStatus.DISPENSED) {
                System.out.println();
                System.out.println("Prescription already dispensed!");
                return; 
            }

            System.out.println(" " + index + ". " + m.getMedicineName() + " " + m.getStatus());
            index++;
        }
        
        System.out.println(" " + (index) + ". Exit");
        int choice = sc.nextInt();

        while(choice != index){
        
                Medication med = Inventory.get(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice-1).getMedicineName());
                if(med.getStock() > 0 && (a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice-1).getStatus() == Enums.PrescriptionStatus.PENDING)){
                    med.setStock(med.getStock()-1);
                    a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice-1).setStatus(PrescriptionStatus.DISPENSED);
                    System.out.println(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice-1).getMedicineName() + " has been dispensed!");
                }
                else if(med.getStock() < 0){
                    System.out.println(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice-1).getMedicineName() + " is not available!");
                }
                else{
                    System.out.println(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice-1).getMedicineName() + " has already been dispensed! Try another Medication!");
                }

                index = 1;

                for(PrescribedMedication m : a.getAppointmentOutcomeRecord().getPrescribedMedications()){
                    System.out.println(" " + index + ". " + m.getMedicineName() + " " + m.getStatus());
                    index++;
                }
                
                System.out.println(" " + (index) + ". Exit");
                
                choice = sc.nextInt();
                sc.nextLine();
        } 
           
        for(int i = 0; i < a.getAppointmentOutcomeRecord().getPrescribedMedications().size(); i++){

            if(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(i).getStatus() != Enums.PrescriptionStatus.DISPENSED){
                System.out.println("Appointment Status not Completed!");
                return;
            }
        }   
            System.out.println("Appointment Status updated to Completed!");
            a.setStatus(AppointmentStatus.COMPLETED);
        
        } 
        
        
    public ArrayList<Appointment> getAllAppointmentsByStatus(Enums.AppointmentStatus status) {
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();
        ArrayList<Doctor> doctors = UserRepository.getAllDoctors();
        ArrayList<Appointment> appointments = new ArrayList<>();
        
        for(Doctor d : doctors){
            for(Appointment a : d.getAppointments()){
                appointments.add(a);
            }
        }
        
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == status) {
                filteredAppointments.add(appointment);
            }
        }
        
        return filteredAppointments;
    }
        
    
}

