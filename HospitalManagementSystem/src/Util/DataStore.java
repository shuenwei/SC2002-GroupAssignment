package src.Util;

import src.Entity.*;
import src.Repository.*;
import src.View.CommonView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DataStore} class is responsible for saving various types of application data
 * to CSV files, including staff, patient, appointment, doctor availability, medicine,
 * request, and medical history data.
 */
public class DataStore {

    /**
     * Saves all data types by calling individual methods to save staff, patient, appointment,
     * doctor availability, medicine, request, and medical history data to their respective files.
     */
    public static void saveAll() {
        CommonView.newPage();
        System.out.println("Saving application data to CSV files...");
        DataStore.saveStaffData();
        DataStore.savePatientData();
        DataStore.saveAppointmentData();
        DataStore.saveDoctorAvailability();
        DataStore.saveMedicine();
        DataStore.saveRequest();
        DataStore.saveMedicalHistory();
        CommonView.newPage();
    }

    /**
     * Saves staff data to a CSV file, including attributes like staff ID, password, name, role, gender, and age.
     */
    public static void saveStaffData() {
        String staffCsvFilePath = "data/staffCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(staffCsvFilePath))) {
            pw.println("Staff ID,Password,Name,Role,Gender,Age");
            for (Staff staff : UserRepository.getAllStaff()) {
                String[] data = {
                        staff.getHospitalId(),
                        staff.getPassword(),
                        staff.getName(),
                        staff.getRole().toString(),
                        staff.getGender().toString(),
                        String.valueOf(staff.getAge())
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves each doctor's availability data to a CSV file, including their weekly schedule.
     * Days without availability are marked as "Not Available."
     */
    public static void saveDoctorAvailability() {
        String doctorAvailabilityCsvFilePath = "data/availabilityCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(doctorAvailabilityCsvFilePath))) {
            pw.println("Doctor ID,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday");

            for (Doctor doctor : UserRepository.getAllDoctors()) {
                String[] data = new String[8];
                data[0] = doctor.getHospitalId();
                for (int i = 1; i <= 7; i++) {
                    Availability availability = doctor.getAvailability(DayOfWeek.of(i));
                    if (availability != null) {
                        String startTime = availability.getStartTime().toString();
                        String endTime = availability.getEndTime().toString();
                        data[i] = startTime + "-" + endTime;
                    } else {
                        data[i] = "Not Available";
                    }
                }
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves patient data to a CSV file, including personal details and contact information.
     */
    public static void savePatientData() {
        String patientCsvFilePath = "data/patientCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(patientCsvFilePath))) {
            pw.println("Patient ID,Password,Name,Date of Birth,Gender,Blood Type,Email Address,Phone Number");
            for (Patient patient : UserRepository.getAllPatients()) {
                MedicalRecord medicalRecord = patient.getMedicalRecord();
                String[] data = {
                        patient.getHospitalId(),
                        patient.getPassword(),
                        patient.getName(),
                        medicalRecord.getDateOfBirth(),
                        patient.getGender().toString(),
                        medicalRecord.getBloodType(),
                        medicalRecord.getEmailAddress(),
                        medicalRecord.getPhoneNumber()
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves medication data to a CSV file, including medicine name, stock level, and low stock threshold.
     */
    public static void saveMedicine() {
        String medicineListCsvFilePath = "data/medicineListCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(medicineListCsvFilePath))) {
            pw.println("Medicine Name,Stock,Low Stock Level");
            for (Medication medicine : InventoryRepository.getAllMedicines()) {
                String[] data = {
                        medicine.getMedicineName(),
                        String.valueOf(medicine.getStock()),
                        String.valueOf(medicine.getStockThreshold())
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves medication replenishment requests to a CSV file, including medicine name and request status.
     */
    public static void saveRequest() {
        String requestCsvFilePath = "data/requestCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(requestCsvFilePath))) {
            pw.println("Medicine Name,Status");
            for (Request request : InventoryRepository.getAllRequests()) {
                String[] data = {
                        request.getRequestedMedicine(),
                        request.getStatus().toString()
                };
                pw.println(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves each patient's medical history to a CSV file, including details of diagnoses, treatment plans, and prescribed medications.
     */
    public static void saveMedicalHistory() {
        String medicalHistoryCsvFilePath = "data/medicalHistoryCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(medicalHistoryCsvFilePath))) {
            pw.println("Patient ID,Diagnosis and Type,Treatment Plan,Prescribed Medications");
            for (Patient patient : UserRepository.getAllPatients()) {
                String patientId = patient.getHospitalId();
                for (MedicalHistory history : patient.getMedicalRecord().getMedicalHistory()) {
                    String prescribedMedications = String.join(";", history.getPrescribedMedications());
                    String[] data = {
                            patientId,
                            history.getDiagnosisandType(),
                            history.getTreatmentPlan(),
                            prescribedMedications
                    };
                    pw.println(String.join(",", data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves appointment data to a CSV file, including details such as patient ID, doctor ID, date, time, status,
     * type of service, consultation notes, and prescribed medications.
     */
    public static void saveAppointmentData() {
        String appointmentCsvFilePath = "data/appointmentCsv.csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(appointmentCsvFilePath))) {
            pw.println("Patient ID,Doctor ID,Date,Time,Status,Type of Service,Consultation Notes,Prescribed Medications");
            for (Appointment appointment : AppointmentRepository.getAllAppointments()) {
                List<String> data = new ArrayList<>();
                data.add(appointment.getPatient().getHospitalId());
                data.add(appointment.getDoctor().getHospitalId());
                data.add(appointment.getDate().toString());
                data.add(appointment.getTime().toString());
                data.add(appointment.getStatus().toString());

                if (appointment.getAppointmentOutcomeRecord() != null) {
                    data.add(appointment.getAppointmentOutcomeRecord().getTypeOfService());
                    data.add(appointment.getAppointmentOutcomeRecord().getConsultationNotes());

                    List<String> prescriptionData = new ArrayList<>();
                    for (PrescribedMedication prescribedMedication : appointment.getAppointmentOutcomeRecord().getPrescribedMedications()) {
                        List<String> prescriptionDetails = new ArrayList<>();
                        prescriptionDetails.add(prescribedMedication.getMedicineName());
                        prescriptionDetails.add(prescribedMedication.getStatus().toString());
                        String prescription = String.join("-", prescriptionDetails);
                        prescriptionData.add(prescription);
                    }
                    String joinedPrescribedMedications = String.join(";", prescriptionData);
                    data.add(joinedPrescribedMedications);
                }

                String[] dataArray = data.toArray(new String[0]);
                pw.println(String.join(",", dataArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
