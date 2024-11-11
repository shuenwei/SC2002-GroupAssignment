package src.Entity;

import java.util.ArrayList;

/**
 * The MedicalHistory class represents the medical history of a patient, including their diagnosis, treatment plan,
 * and prescribed medications.
 */
public class MedicalHistory {

    /**
     * The diagnosis and type of service provided.
     */
    private String diagnosisandType;

    /**
     * The treatment plan prescribed to the patient for the diagnosis.
     */
    private String TreatmentPlan;

    /**
     * A list of prescribed medications for the patient.
     */
    private ArrayList<String> prescribedMedications;

    /**
     * Constructs a MedicalHistory object with the specified diagnosis and treatment plan.
     * Initializes the prescribed medications list as an empty ArrayList.
     *
     * @param diagnosisandType The diagnosis and type of service provided to the patient.
     * @param TreatmentPlan    The treatment plan prescribed for the diagnosis.
     */
    public MedicalHistory(String diagnosisandType, String TreatmentPlan) {
        this.diagnosisandType = diagnosisandType;
        this.TreatmentPlan = TreatmentPlan;
        this.prescribedMedications = new ArrayList<>();
    }

    /**
     * Returns the diagnosis and type of service provided.
     *
     * @return The diagnosis and type of service.
     */
    public String getDiagnosisandType() {
        return diagnosisandType;
    }

    /**
     * Sets the diagnosis and type of service provided.
     *
     * @param diagnosis The diagnosis and type of service to set.
     */
    public void setDiagnosisandType(String diagnosis) {
        this.diagnosisandType = diagnosis;
    }

    /**
     * Returns the treatment plan prescribed to the patient.
     *
     * @return The treatment plan.
     */
    public String getTreatmentPlan() {
        return TreatmentPlan;
    }

    /**
     * Sets the treatment plan for the patient.
     *
     * @param TreatmentPlan The treatment plan to set.
     */
    public void setTreatmentPlan(String TreatmentPlan) {
        this.TreatmentPlan = TreatmentPlan;
    }

    /**
     * Returns the list of prescribed medications for the patient.
     *
     * @return The list of prescribed medications.
     */
    public ArrayList<String> getPrescribedMedications() {
        return prescribedMedications;
    }

    /**
     * Adds a prescribed medication to the list of prescribed medications.
     *
     * @param prescribedMedications The medication to add to the list.
     */
    public void addPrescribedMedications(String prescribedMedications) {
        this.prescribedMedications.add(prescribedMedications);
    }

}
