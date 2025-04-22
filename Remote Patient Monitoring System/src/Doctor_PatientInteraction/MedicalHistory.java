package Doctor_PatientInteraction;

import UserManagement.Patient;
import java.util.HashMap;

public class MedicalHistory {
    public static HashMap<Integer, Patient> pastConsultants = new HashMap<>();

    public static void seeMedicalHistory(int patientid) {
        Patient current = Patient.getPatient(patientid);
        if (current == null) {
            System.out.println("Invalid patient access!");
            return;
        }

        System.out.println("Medical History for Patient ID: " + patientid);
        boolean found = false;

        for (int doctorid : Prescription.prescriptions.keySet()) {
            Feedback prescription = Prescription.prescriptions.get(doctorid);
            if (prescription != null && prescription.getPatientid() == patientid) {
                System.out.println("Doctor ID: " + doctorid);
                System.out.println(prescription);
                System.out.println("----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No medical history found for this patient.");
        }
    }
}