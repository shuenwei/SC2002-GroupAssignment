package Entity;

import UI.DoctorUI;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class Doctor extends Staff {

    private ArrayList<Appointment> appointments;
    private Availability[] schedule = new Availability[7];

    public Doctor(String hospitalId,String password, String name, String gender,Enums.Role role, int age) {
        super(hospitalId,password,name,gender,role,age);
        appointments = new ArrayList<Appointment>();
    }

    public void displayMenu(User currentUser) {
        DoctorUI doctorUi = new DoctorUI((Doctor) currentUser);
        doctorUi.displayMenu();
    }

    public String getName(){
        return super.getName();
    }//

    public void setName(String name){
        super.setName(name);
    }//

    public Availability[] getSchedule() {
        return schedule;
    }

    public void setAvailability(DayOfWeek day, Availability availability) {
        int index = day.getValue() - 1;
        schedule[index] = availability;
    }

    public Availability getAvailability(DayOfWeek day) {
        int index = day.getValue() - 1;
        return schedule[index];
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
    }
}