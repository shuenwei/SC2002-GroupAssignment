package src.Entity;

import src.UI.DoctorUI;
import src.Enums.*;
import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * The Doctor class represents a doctor with a list of appointments and a weekly schedule of availability.
 * It extends the Staff class and adds functionality specific to doctors.
 */
public class Doctor extends Staff {

    /**
     * A list of appointments scheduled for the doctor.
     */
    private ArrayList<Appointment> appointments;

    /**
     * An array of Availability objects, each representing the doctor's availability for a specific day of the week.
     * The array has 7 elements, one for each day (Sunday to Saturday).
     */
    private Availability[] schedule = new Availability[7];

    /**
     * Constructs a Doctor object with the specified hospital ID, password, name, gender, role, and age.
     * Initializes the appointments list as an empty ArrayList.
     *
     * @param hospitalId The hospital ID of the doctor.
     * @param password   The password of the doctor.
     * @param name       The name of the doctor.
     * @param gender     The gender of the doctor.
     * @param role       The role of the doctor.
     * @param age        The age of the doctor.
     */
    public Doctor(String hospitalId,String password, String name, String gender, Role role, int age) {
        super(hospitalId,password,name,gender,role,age);
        appointments = new ArrayList<Appointment>();
    }

    /**
     * Displays the menu specific to the doctor user.
     *
     * @param currentUser The current user (doctor) who is interacting with the system.
     */
    public void displayMenu(User currentUser) {
        DoctorUI doctorUi = new DoctorUI((Doctor) currentUser);
        doctorUi.displayMenu();
    }

    /**
     * Returns the name of the doctor.
     *
     * @return The name of the doctor.
     */
    public String getName(){
        return super.getName();
    }

    /**
     * Sets the name of the doctor.
     *
     * @param name The name to set for the doctor.
     */
    public void setName(String name){
        super.setName(name);
    }


    /**
     * Returns the schedule of the doctor, represented as an array of Availability objects.
     * Each element corresponds to a day of the week (Sunday to Saturday).
     *
     * @return The doctor's schedule array.
     */
    public Availability[] getSchedule() {
        return schedule;
    }

    /**
     * Sets the availability for a specific day of the week.
     *
     * @param day        The day of the week (e.g., Monday, Tuesday).
     * @param availability The availability for the specified day.
     */
    public void setAvailability(DayOfWeek day, Availability availability) {
        int index = day.getValue() - 1;
        schedule[index] = availability;
    }

    /**
     * Returns the availability for a specific day of the week.
     *
     * @param day The day of the week (e.g., Monday, Tuesday).
     * @return The availability for the specified day.
     */
    public Availability getAvailability(DayOfWeek day) {
        int index = day.getValue() - 1;
        return schedule[index];
    }

    /**
     * Returns the list of appointments scheduled for the doctor.
     *
     * @return The list of appointments.
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Sets the list of appointments for the doctor.
     *
     * @param appointments The list of appointments to set.
     */
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Adds an appointment to the doctor's list of appointments.
     *
     * @param appointment The appointment to add.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Removes an appointment from the doctor's list of appointments.
     *
     * @param appointment The appointment to remove.
     */
    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
    }
}