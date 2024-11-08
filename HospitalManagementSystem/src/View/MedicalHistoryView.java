package View;

import Entity.MedicalHistory;
import Interface.IDisplayableView;

/**
 * The MedicalHistoryView class is responsible for displaying the details of a single medical history record.
 * It implements the IDisplayableView interface to provide a standardized way of presenting 
 * medical history information to the user.
 */
public class MedicalHistoryView implements IDisplayableView<MedicalHistory> {

    /**
     * Displays the details of a single medical history record, including the diagnosis/type of service, 
     * treatment plan, and the prescribed medications.
     * 
     * @param a The MedicalHistory object whose details will be displayed.
     */
    public void display(MedicalHistory a) {
        System.out.println("Name of Diagnosis / Type of Service: " + a.getDiagnosisandType());
        System.out.println("Treatment Plan: " + a.getTreatmentPlan());
        System.out.println("Prescribed Medications: ");
        for(String i : a.getPrescribedMedications()){
            System.out.println("- " + i);
        }
    }
}
