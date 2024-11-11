package Repository;

import Entity.Medication;
import Entity.Request;
import Enums.RequestStatus;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The InventoryRepository class provides methods to manage medication and request repositories.
 * It supports adding, retrieving, and removing medications and requests, as well as viewing all entries.
 */
public class InventoryRepository {
    
    private static HashMap<String, Medication> medicineRepo = new HashMap<>();
    private static HashMap<String, Request> requestRepo = new HashMap<>();

    /**
     * Adds a medication to the repository.
     *
     * @param medicine The Medication object to be added.
     */
    public static void add(Medication medicine) {
        medicineRepo.put(medicine.getMedicineName(), medicine);
    }

    /**
     * Retrieves a medication from the repository by its name.
     *
     * @param medicineName The name of the medication to retrieve.
     * @return The Medication object if found, or null if not found.
     */
    public static Medication get(String medicineName) {
        Medication medicine = medicineRepo.get(medicineName);
        if (medicine != null) {
            return medicineRepo.get(medicineName);
        } else {
            System.out.println("Medicine does not exist");
            return null;
        }
    }

    /**
     * Removes a medication from the repository by its name.
     *
     * @param medicineName The name of the medication to remove.
     */
    public static void remove(String medicineName) {
        if (medicineRepo.get(medicineName) != null) {
            medicineRepo.remove(medicineName);
        }
    }

    /**
     * Retrieves all medications in the repository.
     *
     * @return An ArrayList of all Medication objects in the repository.
     */
    public static ArrayList<Medication> getAllMedicines() {
        ArrayList<Medication> medicines = new ArrayList<>();
        for (Medication medicine : medicineRepo.values()) {
            medicines.add(medicine);
        }
        return medicines;
    }

    /**
     * Retrieves a specific request from the repository by the requested medication's name.
     *
     * @param requestedMedicine The name of the requested medication.
     * @return The Request object if found, or null if not found.
     */
    public static Request getRequest(String requestedMedicine) {
        Request request = requestRepo.get(requestedMedicine);
        if (request != null) {
            return request;
        } else {
            System.out.println("Request does not exist");
            return null;
        }
    }

    /**
     * Adds a replenishment request to the repository.
     *
     * @param request The Request object to be added.
     */
    public static void addRequest(Request request) {
        requestRepo.put(request.getRequestedMedicine(), request);
    }

    /**
     * Removes a fulfilled replenishment request from the repository by the requested medication's name.
     * If the request is not fulfilled, it cannot be removed.
     *
     * @param requestedMedicine The name of the requested medication.
     */
    public static void removeRequest(String requestedMedicine) {
        Request request = getRequest(requestedMedicine);
        if (request != null) {
            if (request.getStatus() == RequestStatus.FULFILLED) {
                requestRepo.remove(requestedMedicine);
            } else {
                System.out.println("Request has not been fulfilled yet.");
            }
        }
    }

    /**
     * Retrieves all requests in the repository.
     *
     * @return An ArrayList of all Request objects in the repository.
     */
    public static ArrayList<Request> getAllRequests() {
        ArrayList<Request> requests = new ArrayList<>();
        for (Request request : requestRepo.values()) {
            requests.add(request);
        }
        return requests;
    }

    /**
     * Displays all replenishment requests along with their statuses.
     */
    public static void viewRequests() {
        ArrayList<Request> requests = getAllRequests();
        String[] headers = new String[]{"Request", "Status"};

        System.out.println();
        System.out.printf("| %-15s | %-10s |%n", headers[0], headers[1]);
        System.out.println("--------------------------------");
        
        for (Request request : requests) {
            if (request != null) {
                System.out.printf("| %-15s | %-10s |%n", request.getRequestedMedicine(), request.getStatus().toString());
            }
        }
    }
}
