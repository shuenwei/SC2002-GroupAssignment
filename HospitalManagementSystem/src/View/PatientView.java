package View;

import Entity.Patient;
import Interface.IDisplayableView;

public class PatientView implements IDisplayableView<Patient>{

    public void display(Patient patient){

        String[] headers = new String[]{"Hospital ID", "Password", "Name", "Date Of Birth", "Gender", "Blood Type", "Email Address", "Phone Number"};
        System.out.println();
        System.out.printf("| %-13s | %-10s | %-15s | %-13s | %-8s | %-12s | %-28s | %-15s |%n", headers[0], headers[1], headers[2], headers[3], headers[4], headers[5], headers[6], headers[7]);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-13s | %-10s | %-15s | %-13s | %-8s | %-12s | %-28s | %-15s |%n", patient.getHospitalId(), patient.getPassword(),patient.getName(), patient.getMedicalRecord().getDateOfBirth(), patient.getGender(), patient.getMedicalRecord().getBloodType(), patient.getMedicalRecord().getEmailAddress(), patient.getMedicalRecord().getPhoneNumber());
        
    }
}
