package EmergencyAlertSystem;

import HealthDataHandling.VitalsDatabase;
import HealthDataHandling.VitalSign;
import UserManagement.Patient;

import java.util.Scanner;

public class PanicButton {
    static Scanner sc = new Scanner(System.in);

    public PanicButton(int patientId) {
        Patient patient = Patient.getPatient(patientId);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }
        VitalSign vitals = VitalsDatabase.vitals.get(patientId);
        if (vitals == null) {
            System.out.println("No vitals found for this patient.");
            return;
        }
        emergency(patientId, vitals.getHeartRate(), vitals.getOxygenLevel(), vitals.getBloodPressure(), vitals.getTemperature());
    }

    public void emergency(int patientId, double hr, double oxy, String bp, double temp) {
        EmergencyAlert alert = new EmergencyAlert(patientId, hr, oxy, bp, temp);
        alert.notifyDoctor();
    }
}