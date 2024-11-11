package src.View;

import src.Entity.Patient;
import src.Interface.IDisplayableView;

/**
 * The PatientView class is responsible for displaying the details of a patient.
 * It implements the IDisplayableView interface to provide a standardized way of presenting 
 * patient information to the user.
 */
public class PatientView implements IDisplayableView<Patient>{

    /**
     * Displays the details of a patient, including their hospital ID, password, name, 
     * date of birth, gender, blood type, email address, and phone number.
     * 
     * @param patient The Patient object whose details will be displayed.
     */
    public void display(Patient patient){

        String[] headers = new String[]{"Hospital ID", "Password", "Name", "Date Of Birth", "Gender", "Blood Type", "Email Address", "Phone Number"};
        System.out.println();
        System.out.printf("| %-13s | %-10s | %-15s | %-13s | %-8s | %-12s | %-28s | %-15s |%n", headers[0], headers[1], headers[2], headers[3], headers[4], headers[5], headers[6], headers[7]);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-13s | %-10s | %-15s | %-13s | %-8s | %-12s | %-28s | %-15s |%n", patient.getHospitalId(), patient.getPassword(),patient.getName(), patient.getMedicalRecord().getDateOfBirth(), patient.getGender(), patient.getMedicalRecord().getBloodType(), patient.getMedicalRecord().getEmailAddress(), patient.getMedicalRecord().getPhoneNumber());
        
    }
}
