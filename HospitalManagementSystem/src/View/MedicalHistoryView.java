package View;

import Entity.MedicalHistory;
import Interface.IDisplayableView;

public class MedicalHistoryView implements IDisplayableView<MedicalHistory> {

    public void display(MedicalHistory a) {
        System.out.println("Name of Diagnosis / Type of Service: " + a.getDiagnosisandType());
        System.out.println("Treatment Plan: " + a.getTreatmentPlan());
        System.out.println("Prescribed Medications: ");
        for(String i : a.getPrescribedMedications()){
            System.out.println("- " + i);
        }
    }
}
