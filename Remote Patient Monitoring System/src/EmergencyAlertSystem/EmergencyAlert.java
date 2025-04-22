package EmergencyAlertSystem;

import java.util.Scanner;
import UserManagement.Doctor;
import NotificationAndReminders.EmailNotification;
import NotificationAndReminders.SMSNotification;

public class EmergencyAlert {
    private int patientId;
    private double heartRate, oxygenLevel, temperature;
    private String bloodPressure;

    public EmergencyAlert(int patientId, double hr, double oxy, String bp, double temp) {
        this.patientId = patientId;
        this.heartRate = hr;
        this.oxygenLevel = oxy;
        this.bloodPressure = bp;
        this.temperature = temp;
    }

    public void notifyDoctor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor ID to send emergency alert: ");
        int docId = sc.nextInt();

        Doctor doctor = Doctor.getDoctor(docId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        String message = "[EMERGENCY] Patient ID: " + patientId +
                ", HR: " + heartRate +
                ", Oxygen: " + oxygenLevel +
                ", BP: " + bloodPressure +
                ", Temp: " + temperature;

        new EmailNotification().send("doctor@hospital.com", message);
        new SMSNotification().send("+1234567890", message);

        doctor.notifications.add(message);
        System.out.println("Emergency alert sent to Doctor ID: " + docId);
    }
}