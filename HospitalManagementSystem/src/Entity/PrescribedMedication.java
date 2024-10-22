package Entity;

import Enums.PrescriptionStatus;

public class PrescribedMedication {
    private String medicineName;
    private PrescriptionStatus status;

    public PrescribedMedication(String medicineName) {
        this.medicineName = medicineName;
        this.status = PrescriptionStatus.PENDING;
    }
    public String getMedicineName() {
        return this.medicineName;
    }
    public PrescriptionStatus getStatus() {
        return this.status;
    }
    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
}