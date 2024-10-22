package Entity;

import java.util.ArrayList;

public class MedicalHistory {
    private String diagnosisName;
    private String TreatmentPlan;
    private ArrayList<String> prescribedMedications;

    public MedicalHistory(String diagnosisName, String TreatmentPlan) {
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

    public ArrayList<String> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void addPrescribedMedications(String prescribedMedications) {
        this.prescribedMedications.add(prescribedMedications);
    }

}
