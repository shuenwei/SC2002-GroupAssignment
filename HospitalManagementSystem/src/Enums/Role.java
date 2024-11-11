package src.Enums;

/**
 * The {@code Role} enum represents the various roles within the healthcare system.
 * It is used to assign specific roles to users in the system, defining their permissions and access levels.
 */
public enum Role {

    /**
     * Represents a doctor, a medical professional responsible for diagnosing and treating patients.
     */
    DOCTOR,

    /**
     * Represents a patient, an individual who receives healthcare services.
     */
    PATIENT,

    /**
     * Represents an administrator, a user who manages the system and its operations.
     */
    ADMINISTRATOR,

    /**
     * Represents a pharmacist, a healthcare professional responsible for dispensing medications.
     */
    PHARMACIST,

    /**
     * Represents a nurse, a healthcare professional who assists in patient care and treatment.
     */
    NURSE
}
