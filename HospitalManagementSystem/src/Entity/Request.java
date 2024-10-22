package Entity;

import Enums.RequestStatus;

public class Request {
    private String medicineName;
    private RequestStatus status;

    public Request(String medicineName) {
        this.medicineName = medicineName;
        this.status = RequestStatus.PENDING;
    }
    
    public String getRequestedMedicine() {
        return this.medicineName;
    }

    public RequestStatus getStatus() {
        return this.status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}