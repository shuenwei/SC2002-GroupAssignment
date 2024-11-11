package Enums;

/**
 * The {@code AppointmentStatus} enum represents the various statuses that an appointment can have.
 * It is used to track the current state of an appointment within the system.
 */
public enum AppointmentStatus {

    /**
     * Indicates that the appointment is pending and has not yet been confirmed.
     */
    PENDING,

    /**
     * Indicates that the appointment has been confirmed and is scheduled.
     */
    CONFIRMED,

    /**
     * Indicates that the appointment has been cancelled.
     */
    CANCELLED,

    /**
     * Indicates that the appointment is pending due to medication-related requirements.
     */
    MEDICINE_PENDING,

    /**
     * Indicates that the appointment has been completed.
     */
    COMPLETED
}
