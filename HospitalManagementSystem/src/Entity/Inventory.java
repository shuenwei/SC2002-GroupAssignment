package Entity;

import java.util.ArrayList;
import java.util.HashMap;

import Enums.RequestStatus;

public class Inventory {
    private static HashMap<String, Medication> medicineRepo = new HashMap<String, Medication>();
    private static HashMap<String, Request> requestRepo = new HashMap<String, Request>();

    public static void add(Medication medicine) {
        medicineRepo.put(medicine.getMedicineName(), medicine);
    }

    public static Medication get(String medicineName) {
        Medication medicine = medicineRepo.get(medicineName);
        if (medicine != null) {
            return medicineRepo.get(medicineName);
        }
        else {
            System.out.println("Medicine does not exist");
            return null;
        }
    }

    public static void remove(String medicineName) {
        if (medicineRepo.get(medicineName) != null) {
            medicineRepo.remove(medicineName);
        }
    }

    private static ArrayList<Medication> getAllMedicines() {
        ArrayList<Medication> medicines = new ArrayList<Medication>();
            for (Medication medicine : medicineRepo.values()) {
                medicines.add(medicine);
            }
        return medicines;
    }

    public static void viewInventory() {
        ArrayList<Medication> medicines = getAllMedicines();
        String[] headers = new String[]{"Medicine Name", "Stock", "AlertLevel", "LowStock"};
        System.out.println();
        System.out.printf("| %-15s | %-7s | %-12s | %-7s |%n", headers[0], headers[1], headers[2], headers[3]);
        System.out.println("-------------------------------------------------------");
        for (Medication medicine : medicines) {
            if (medicine != null) {
                if(medicine.getIsLowStock()){
                    System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), "YES");
                }
                else{
                    System.out.printf("| %-15s | %-7d | %-12d | %-8s |%n", medicine.getMedicineName(), medicine.getStock(), medicine.getStockThreshold(), "NO");
                }
            }
        }
    }

    public static Request getRequest(String requestedMedicine) {
        Request request = requestRepo.get(requestedMedicine);
        if (request != null) {
            return request;
        }
        else {
            System.out.println("Request does not exist");
            return null;
        }
    }

    public static void addRequest(Request request) {
        requestRepo.put(request.getRequestedMedicine(), request);
    }

    public static void removeRequest(String requestedMedicine) {
        Request request = getRequest(requestedMedicine);
        if (request != null) {
            if (request.getStatus() == RequestStatus.FULFILLED) {
                requestRepo.remove(requestedMedicine);
            }
            else {
                System.out.println("Request has not been fulfilled yet.");
            }
        }
    }

    private static ArrayList<Request> getAllRequests() {
        ArrayList<Request> requests = new ArrayList<Request>();
            for (Request request : requestRepo.values()) {
                requests.add(request);
            }
        return requests;
    }

    public static void viewRequests() {
        ArrayList<Request> requests = getAllRequests();
        String header = "Requests";

        System.out.println();
        System.out.printf("| %-15s |%n", header);
        System.out.println("-------------------");
        
        for (Request request : requests) {
            if (request != null) {
                System.out.printf("| %-15s |%n", request.getRequestedMedicine());
            }
        }
    }
}