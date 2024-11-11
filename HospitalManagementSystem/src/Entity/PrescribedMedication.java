package src.Entity;

import src.Enums.PrescriptionStatus;

/**
 * The PrescribedMedication class represents a medication that has been prescribed to a patient.
 * It contains details about the medication's name and its prescription status.
 */
public class PrescribedMedication {

    /**
     * The name of the prescribed medication.
     */
    private String medicineName;


    /**
     * The status of the prescribed medication, which indicates if it is pending or completed.
     */
    private PrescriptionStatus status;

    /**
     * Constructs a PrescribedMedication object with the specified medication name and sets its status to PENDING.
     *
     * @param medicineName The name of the prescribed medication.
     */
    public PrescribedMedication(String medicineName) {
        this.medicineName = medicineName;
        this.status = PrescriptionStatus.PENDING;
    }

    /**
     * Constructs a {@link PrescribedMedication} with the specified medicine name and prescription status.
     * 
     * @param medicineName the name of the prescribed medicine.
     * @param prescriptionStatus the {@link PrescriptionStatus} indicating the status of the prescription.
     */
    public PrescribedMedication(String medicineName, PrescriptionStatus prescriptionStatus) {
        this.medicineName = medicineName;
        this.status = prescriptionStatus;
    }

    /**
     * Gets the name of the prescribed medication.
     *
     * @return The name of the medication.
     */
    public String getMedicineName() {
        return this.medicineName;
    }

    /**
     * Gets the status of the prescribed medication.
     *
     * @return The current status of the medication.
     */
    public PrescriptionStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the prescribed medication.
     *
     * @param status The new status of the medication.
     */
    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
}