package src.Entity;

import src.Enums.RequestStatus;

/**
 * The Request class represents a request for a medication made by a pharmacist or any authorized staff member.
 * It contains the requested medication's name and its request status.
 */
public class Request {

    /**
     * The name of the requested medication.
     */
    private String medicineName;

    /**
     * The status of the request, indicating whether the request is pending, approved, or denied.
     */
    private RequestStatus status;

    /**
     * Constructs a Request object with the specified medication name and sets its status to PENDING.
     *
     * @param medicineName The name of the requested medication.
     */
    public Request(String medicineName) {
        this.medicineName = medicineName;
        this.status = RequestStatus.PENDING;
    }
    
    /**
     * Gets the name of the requested medication.
     *
     * @return The name of the requested medication.
     */
    public String getRequestedMedicine() {
        return this.medicineName;
    }

    /**
     * Gets the status of the request.
     *
     * @return The current status of the request.
     */
    public RequestStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the request.
     *
     * @param status The new status of the request.
     */
    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}