package Doctor_PatientInteraction;

import java.util.HashMap;
import java.util.Scanner;

public class Prescription {
    static Scanner sc = new Scanner(System.in);
    public static HashMap<Integer, Feedback> prescriptions = new HashMap<>();

    public static String seeprescriptions(int patientid) {
        System.out.print("Enter the doctor ID whose prescriptions you want to see: ");
        int doctorid = sc.nextInt();

        if (!prescriptions.containsKey(patientid)) {
            return "No prescriptions found for this patient.";
        }

        Feedback feedback = prescriptions.get(patientid);

        if (feedback.getPatientid() == patientid && feedback.toString().contains("Doctor ID: " + doctorid)) {
            return feedback.toString();
        } else {
            return "No prescriptions found from this doctor for this patient.";
        }
    }
}