package View;

import Entity.MedicalHistory;
import Interface.IMedicalHistory;

import java.util.ArrayList;

public class MedicalHistoryView implements IMedicalHistory {

    public void display(ArrayList<MedicalHistory> medicalHistoryList) {
        if (medicalHistoryList.isEmpty()) {
            System.out.println("No diagnoses available.");
        } else {
            for (MedicalHistory medicalHistory : medicalHistoryList) {
                System.out.println("Name of Diagnosis: " + medicalHistory.getDiagnosisName());
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
            }
        }
    }
}
