package Entity;

import Enums.PrescriptionStatus;

public class PrescribedMedication {
    private String medicineName;
    private PrescriptionStatus status;
    private String tick;

    public PrescribedMedication(String medicineName) {
        this.medicineName = medicineName;
        this.status = PrescriptionStatus.PENDING;
        this.tick = "[NOT DISPENSED]";
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
    public String getTick(){
        return this.tick;
    }//
    public void setTick(String tick){
        this.tick = tick;
    }//
}