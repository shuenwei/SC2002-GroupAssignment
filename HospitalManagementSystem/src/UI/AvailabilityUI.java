package UI;

import Controller.AvailabilityController;
import Entity.Appointment;
import Entity.Doctor;
import Entity.Patient;
import Enums.AppointmentStatus;//
import Repository.UserRepository;
import View.ScheduleView;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AvailabilityUI {
    private Doctor doctor;
    private Scanner scanner;//

    public AvailabilityUI(Doctor doctor){
        this.doctor = doctor;
        scanner = new Scanner(System.in);
    }

    public void viewSchedule(){
        AvailabilityController availabilityController = new AvailabilityController(doctor);
        ScheduleView scheduleView = new ScheduleView(availabilityController.getSchedule());
        scheduleView.display();
    }

    public void setSchedule(){
        Scanner scanner = new Scanner(System.in);
        AvailabilityController availabilityController = new AvailabilityController(doctor);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        int day = -1;

        do {
            try {
                System.out.println();
                System.out.println("Select day to set availability:");
                System.out.println();
                System.out.println("(1) Monday");
                System.out.println("(2) Tuesday"); 
                System.out.println("(3) Wednesday"); 
                System.out.println("(4) Thursday"); 
                System.out.println("(5) Friday"); 
                System.out.println("(6) Saturday"); 
                System.out.println("(7) Sunday"); 
                System.out.println("---------------------------------"); 
                System.out.println("(8) Confirm Updated Availability"); 
                day = scanner.nextInt();
                scanner.nextLine();

                if (day >= 1 && day <= 7) {
                    boolean validTime = false;
                    LocalTime startTime = null;
                    LocalTime endTime = null;
                    System.out.println();
                    System.out.println("Editing availability for " + DayOfWeek.of(day) + ":");
                    System.out.println("---------------------------------"); 
    
                    while (!validTime) {
                        while (startTime == null) {
                            try {
                                System.out.print("Enter Start Time (e.g. 08:00 for 8.00AM): ");
                                String startTimeStr = scanner.nextLine();
                                startTime = LocalTime.parse(startTimeStr, timeFormatter);

                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid time format. Please enter time in HH:MM format (08:00 for 08.00AM).");
                            }
                        }

                        while (endTime == null) {
                            try {
                                System.out.print("Enter End Time (e.g. 14:00 for 2.00PM): ");
                                String endTimeStr = scanner.nextLine();
                                endTime = LocalTime.parse(endTimeStr, timeFormatter);
        
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid time format. Please enter time in HH:MM format (08:00 for 08.00AM).");
                            }
                        }

                        if (endTime.isAfter(startTime)) {
                            validTime = true;
                        } else {
                            startTime = null;
                            endTime = null;
                            System.out.println("Invalid time frame. End time must be after Start time.");
                        }
                    }
            
                    DayOfWeek dayOfWeek = DayOfWeek.of(day);
                    availabilityController.setSchedule(dayOfWeek, startTime, endTime);
                    
                } else if (day != 8) {
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.next();
            }
        } while (day != 8);
    }

    public void displayPendingAppointments(Doctor doctor){

        List<Appointment> pending = doctor.getAllPendingDoctorAppointments();

        if (pending.isEmpty()) {
            System.out.println("No appointments.");
        } 
        else {
            System.out.println(doctor.getName() + " has the following PENDING appointments:");
            System.out.println(); 
            int count = 1;
            for (Appointment a : pending) {
                
                System.out.println(count + ". " + a);
                count++;
            }
        }
    
        System.out.println(); 
    }
//
    // public void getPendingAppointments(){

    //     ArrayList<Appointment> pending = doctor.getAllPendingDoctorAppointments();

    //     if (pending.isEmpty()) {
    //         System.out.println("You have no pending appointments.");
    //         return;
    //     }



    // }

    // public static List<Appointment> getAllAppointmentsByPatient(Patient patient){
    //     List<Appointment> filteredAppointments = new ArrayList<>();
    //     String patientName = patient.getName();

    //     for(Appointment a : patient.getAllAppointments()){
    //         if(a.getPatient().getName().equals(patientName)){
    //             filteredAppointments.add(a);
    //         }
    //     }

    //     return filteredAppointments;
    // }

    public void acceptDecline(){

        List<Appointment> pending = doctor.getAllPendingDoctorAppointments();

        if (pending.isEmpty()) {
            System.out.println("No appointments.");
        } 

        displayPendingAppointments(doctor);

        System.out.println("Enter the appointment number you wish to accept:");
        int index = scanner.nextInt();
        if (index < 1 || index > pending.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        int count = 1;
        for(Appointment a : pending){

            if(count == index){
                List <Patient> patients = UserRepository.getAllPatient();
                doctor.removePendingAppointment(a);
                a.setStatus(AppointmentStatus.CONFIRMED);
                for(Patient p : patients){
                    if(p.getAllPendingAppointments().contains(a)){
                        p.removePendingPatientAppointment(a);
                        p.addPatientAppointment(a);
                    }
                } // just change status
                System.out.println();
                System.out.println("Appointment " + index + " has been CONFIRMED successfully.");
                System.out.println();
                break;
            }
            else{
                count++;
            }
        }

    }
}
