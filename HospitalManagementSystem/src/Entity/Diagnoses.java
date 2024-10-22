package Entity;

import java.util.ArrayList;

public class Diagnoses {
    private String diagnosisName;
    private String TreatmentPlan;
    private ArrayList<PrescribedMedication> prescribedMedications;

    public Diagnoses(String diagnosisName,String TreatmentPlan) {
        this.diagnosisName = diagnosisName;
        this.TreatmentPlan = TreatmentPlan;
        this.prescribedMedications = new ArrayList<>();
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosisName = diagnosis;
    }

    public String getTreatmentPlan() {
        return TreatmentPlan;
    }

    public void setTreatmentPlan(String TreatmentPlan) {
        this.TreatmentPlan = TreatmentPlan;
    }

    public ArrayList<PrescribedMedication> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void addPrescribedMedications(PrescribedMedication prescribedMedications) {
        this.prescribedMedications.add(prescribedMedications);
    }

}
