package UI;

import Entity.Diagnoses;

import java.util.ArrayList;

public class DiagnosesUI {

    private ArrayList<Diagnoses> diagnoses;

    public DiagnosesUI(ArrayList<Diagnoses> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public void printDiagnoses() {
        if(diagnoses.isEmpty()) {
            System.out.println("No diagnoses");
        }
        else {
            for (Diagnoses diagnose : diagnoses) {
                System.out.println("Name of Diagnoses: " + diagnose.getDiagnosisName());
                System.out.println("Treatment Plan: " + diagnose.getTreatmentPlan());
                System.out.println("Prescribed Medication: " + diagnose.getPrescribedMedications());
            }
        }
    }

}
