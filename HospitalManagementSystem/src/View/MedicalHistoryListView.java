package View;

import Entity.MedicalHistory;
import Interface.IListDisplayableView;
import java.util.ArrayList;

/**
 * The MedicalHistoryListView class is responsible for displaying a list of medical history records.
 * It implements the IListDisplayableView interface to provide a standardized way of presenting 
 * medical history information to the user.
 */
public class MedicalHistoryListView implements IListDisplayableView<MedicalHistory> {

    /**
     * Displays a list of medical history records in a user-friendly format.
     * If the list is empty, it informs the user that there are no diagnoses available.
     * For each diagnosis, it displays the name/type of the service, treatment plan, 
     * and a list of prescribed medications (if any).
     * 
     * @param medicalHistoryList The list of MedicalHistory objects to be displayed.
     */
    public void display(ArrayList<MedicalHistory> medicalHistoryList) {
        if (medicalHistoryList.isEmpty()) {
            System.out.println("No diagnoses available.");
        } else {
            int count = 1;
            for (MedicalHistory medicalHistory : medicalHistoryList) {
                System.out.println("(" + count + ") ");
                System.out.println("Name of Diagnosis / Type of Service: " + medicalHistory.getDiagnosisandType());
                System.out.println("Treatment Plan: " + medicalHistory.getTreatmentPlan());

                ArrayList<String> prescriptions = medicalHistory.getPrescribedMedications();
                if (prescriptions.isEmpty()) {
                    System.out.println("No prescribed medications.");
                } else {
                    System.out.println("Prescribed Medications:");
                    for (String medication : prescriptions) {
                        System.out.println("- " + medication);
                    }
                }
                System.out.println();
                count++;
            }
        }
    }
}
