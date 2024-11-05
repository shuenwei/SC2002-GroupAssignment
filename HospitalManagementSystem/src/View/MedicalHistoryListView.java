package View;

import Entity.MedicalHistory;
import Interface.IListDisplayableView;
import java.util.ArrayList;

public class MedicalHistoryListView implements IListDisplayableView<MedicalHistory> {

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
