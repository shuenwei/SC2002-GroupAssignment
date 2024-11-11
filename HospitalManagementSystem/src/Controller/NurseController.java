package src.Controller;

import src.Entity.Nurse;
import src.Entity.Patient;
import src.Entity.User;
import src.Repository.UserRepository;

/**
 * The NurseController class provides methods for nurses to manage patient data,
 * including adding, retrieving, and removing patients from the system.
 */
public class NurseController {

    private Nurse nurse;

    /**
     * Constructs a NurseController for a specific nurse.
     *
     * @param nurse The nurse associated with this controller.
     */
    public NurseController(Nurse nurse){
        this.nurse = nurse;
    }

    /**
     * Adds a patient to the user repository.
     *
     * @param patient The patient entity to be added to the repository.
     */
    public void addPatient(Patient patient){
        UserRepository.add(patient);
    }

    /**
     * Retrieves a user by their hospital ID from the user repository.
     *
     * @param hospitalId The hospital ID of the patient to be retrieved.
     * @return The User associated with the specified hospital ID, or null if not found.
     */
    public User getPatient(String hospitalId){
        return UserRepository.get(hospitalId);
    }

    /**
     * Removes a patient from the user repository if the specified hospital ID belongs to a Patient.
     *
     * @param hospitalId The hospital ID of the patient to be removed.
     * @return true if the patient was successfully removed, false if the hospital ID does not correspond to a Patient.
     */
    public boolean removePatient(String hospitalId){
        if(UserRepository.get(hospitalId) instanceof Patient){
            UserRepository.remove(hospitalId);
            return true;
        } else {
            return false;
        }
    }
}
