package Controller;

import Entity.Appointment;
import Entity.Medication;
import Entity.Pharmacist;
import Entity.PrescribedMedication;
import Entity.Request;
import Enums.AppointmentStatus;
import Enums.PrescriptionStatus;
import Repository.AppointmentRepository;
import Repository.InventoryRepository;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The PharmacistController class provides methods for managing medication stock and updating prescriptions
 * within appointments. This class allows pharmacists to submit stock replenishment requests and dispense prescribed medications.
 */
public class PharmacistController extends StaffController {

    private Pharmacist pharmacist;
    private Medication med;
    private Scanner sc;

    /**
     * Constructs a PharmacistController for the specified pharmacist.
     *
     * @param pharmacist The pharmacist associated with this controller.
     */
    public PharmacistController(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
        sc = new Scanner(System.in);
    }

    /**
     * Submits a replenishment request for a specified medication if it is low on stock.
     *
     * @param requestMedicine The name of the medication to request replenishment for.
     */
    public void submitRequest(String requestMedicine) {
        Medication medicine = InventoryRepository.get(requestMedicine);
        if (medicine != null) {
            if (medicine.getIsLowStock() != true) {
                System.out.printf("%s is not low on stock%n", requestMedicine);
            }
            else {
                Request newRequest = new Request(requestMedicine);
                InventoryRepository.addRequest(newRequest);
                System.out.printf("Successfully submitted replenishment request for %s%n", requestMedicine);
            }
        }
    }

    /**
     * Updates the prescription status within an appointment by allowing the pharmacist to dispense medications.
     * Updates the appointment status to completed once all prescribed medications are dispensed.
     *
     * @param a The appointment containing the prescribed medications to be dispensed.
     */
    public void updatePrescription(Appointment a){

        int index = 0;
        int choice = 0;

        do {
            try {
                index = 1;

                // Display each prescribed medication with its current status
                for (PrescribedMedication m : a.getAppointmentOutcomeRecord().getPrescribedMedications()) {
                    System.out.println(" " + index + ". " + m.getMedicineName() + " " + m.getStatus());
                    index++;
                }
                
                System.out.println(" " + (index) + ". Exit");
                System.out.println("Which Medicine number would you like to dispense?");
                choice = sc.nextInt();
                
                if (choice < 1 || choice > index) {
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
                    continue;
                }

                if (choice == index) {
                    break;
                }

                Medication med = InventoryRepository.get(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice - 1).getMedicineName());
                
                if (med.getStock() > 0 && a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice - 1).getStatus() == Enums.PrescriptionStatus.PENDING) {
                    med.setStock(med.getStock() - 1);
                    a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice - 1).setStatus(PrescriptionStatus.DISPENSED);
                    System.out.println(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice - 1).getMedicineName() + " has been dispensed!");
                } else if (med.getStock() < 0) {
                    System.out.println(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice - 1).getMedicineName() + " is not available!");
                } else {
                    System.out.println(a.getAppointmentOutcomeRecord().getPrescribedMedications().get(choice - 1).getMedicineName() + " has already been dispensed! Try another Medication!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + index);
                sc.next();
            }
            
        } while (choice != index);

        // Check if all prescribed medications have been dispensed to update appointment status
        for (int i = 0; i < a.getAppointmentOutcomeRecord().getPrescribedMedications().size(); i++) {
            if (a.getAppointmentOutcomeRecord().getPrescribedMedications().get(i).getStatus() != Enums.PrescriptionStatus.DISPENSED) {
                System.out.println("Appointment Status not Completed!");
                return;
            }
        }   
        System.out.println("Appointment Status updated to Completed!");
        a.setStatus(AppointmentStatus.COMPLETED);
    }

    /**
     * Retrieves all appointments with a specified status.
     *
     * @param status The status of the appointments to retrieve.
     * @return A list of appointments matching the specified status.
     */
    public ArrayList<Appointment> getAllAppointmentsByStatus(Enums.AppointmentStatus status) {
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();
        ArrayList<Appointment> appointments = AppointmentRepository.getAllAppointments();
        
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == status) {
                filteredAppointments.add(appointment);
            }
        }
        
        return filteredAppointments;
    }
}
