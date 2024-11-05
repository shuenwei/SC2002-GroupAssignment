package Entity;

import java.util.ArrayList;

public class MedicalHistory {
    private String diagnosisandType;
    private String TreatmentPlan;
    private ArrayList<String> prescribedMedications;

    public MedicalHistory(String diagnosisandType, String TreatmentPlan) {
        this.diagnosisandType = diagnosisandType;
        this.TreatmentPlan = TreatmentPlan;
        this.prescribedMedications = new ArrayList<>();
    }

    public String getDiagnosisandType() {
        return diagnosisandType;
    }

    public void setDiagnosisandType(String diagnosis) {
        this.diagnosisandType = diagnosis;
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
