package UI;

import Entity.MedicalHistory;

import java.util.ArrayList;

public class MedicalHistoryUI {

    private ArrayList<MedicalHistory> medicalHistory;

    public MedicalHistoryUI(ArrayList<MedicalHistory> diagnoses) {
        this.medicalHistory = diagnoses;
    }

    public void printMedicalHistory() {
        if(medicalHistory.isEmpty()) {
            System.out.println("No diagnoses");
        }
        else {
            for (MedicalHistory medicalHistory : this.medicalHistory) {
                System.out.println("Name of MedicalHistory: " + medicalHistory.getDiagnosisName());
                System.out.println("Treatment Plan: " + medicalHistory.getTreatmentPlan());
                System.out.println("Prescribed Medication: " + medicalHistory.getPrescribedMedications());
            }
        }
    }

}
