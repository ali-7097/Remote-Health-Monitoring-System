package UserManagement;

import AppointmentScheduling.Appointment;
import AppointmentScheduling.AppointmentManager;
import ChatAndVedioConsultation.ChatClient;
import ChatAndVedioConsultation.VideoCall;
import Doctor_PatientInteraction.MedicalHistory;
import Doctor_PatientInteraction.Prescription;
import EmergencyAlertSystem.PanicButton;
import HealthDataHandling.VitalSign;
import HealthDataHandling.VitalsDatabase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Patient extends User {
    private VitalSign vitals;
    static Scanner sc = new Scanner(System.in);

    public static HashMap<Integer, Patient> patients = new HashMap<>();
    public HashMap<Integer, Appointment> appointments = new HashMap<>();
    public HashMap<Integer, String> messages = new HashMap<>();
    public HashSet<String> notifications = new HashSet<>();

    public Patient(int userID, String name, int age, String gender, String address, String email) {
        super(userID, name, age, gender, address, email);
        patients.put(userID, this);
        this.vitals = new VitalSign(this.getUserID());
    }

    public static Patient getPatient(int userID) {
        return patients.get(userID);
    }

    public void loginPatient() {
        while (true) {
            System.out.println("Enter 1 to book an appointment");
            System.out.println("Enter 2 to see all appointments");
            System.out.println("Enter 3 to manage vital uploads");
            System.out.println("Enter 4 to see your medical history");
            System.out.println("Enter 5 to press Panic Button");
            System.out.println("Enter 6 to view notifications");
            System.out.println("Enter 7 to view messages");
            System.out.println("Enter 8 to reply to message");
            System.out.println("Enter 9 to start chat");
            System.out.println("Enter 10 to start video call");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 0 -> { return; }
                case 1 -> bookAppointment();
                case 2 -> seeAppointments();
                case 3 -> manageVitals();
                case 4 -> medicalHistory();
                case 5 -> new PanicButton(this.getUserID());
                case 6 -> viewNotifications();
                case 7 -> viewMessages();
                case 8 -> replyToMessage();
                case 9 -> ChatClient.startChat(this.getUserID());
                case 10 -> VideoCall.startCall(this.getUserID());
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void viewNotifications() {
        if (notifications.isEmpty()) System.out.println("No notifications.");
        else {
            System.out.println("--- Notifications ---");
            for (String note : notifications) System.out.println(note);
            notifications.clear();
        }
    }

    public void viewMessages() {
        if (messages.isEmpty()) System.out.println("No messages.");
        else messages.forEach((id, msg) -> System.out.println("From " + id + ": " + msg));
    }

    public void replyToMessage() {
        System.out.print("Enter ID to reply to: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter reply: ");
        String reply = sc.nextLine();
        if (Doctor.getDoctor(id) != null) {
            Doctor.getDoctor(id).messages.put(this.getUserID(), reply);
            ChatAndVedioConsultation.ChatServer.logMessage(this.getUserID(), id, reply);
        } else {
            System.out.println("Invalid recipient.");
        }
    }

    // Existing methods remain unchanged:
    private void bookAppointment() {
        System.out.print("Enter your patient ID: ");
        int patientid = sc.nextInt();
        System.out.print("Enter the doctor's ID to book an appointment with: ");
        int doctorid = sc.nextInt();
        AppointmentManager.bookAppointment(patientid, doctorid);
    }

    private void seeAppointments() {
        System.out.println(AppointmentManager.appointments.values());
        while (true) {
            System.out.println("Enter 1 to cancel an appointment");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> { return; }
                case 1 -> cancelAppointment();
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void cancelAppointment() {
        System.out.print("Enter the appointment ID you want to cancel: ");
        int appointmentid = sc.nextInt();
        AppointmentManager.cancelAppointment(appointmentid);
    }

    private void manageVitals() {
        while (true) {
            System.out.println("Enter 1 to change all vitals");
            System.out.println("Enter 2 to change heart rate");
            System.out.println("Enter 3 to change oxygen level");
            System.out.println("Enter 4 to change blood pressure");
            System.out.println("Enter 5 to change temperature");
            System.out.println("Enter 6 to see all vitals");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> { return; }
                case 1 -> changeAllVitals();
                case 2 -> changeHeartRate();
                case 3 -> changeOxygenLevel();
                case 4 -> changeBloodpressure();
                case 5 -> changeTemperature();
                case 6 -> seeAllVitals();
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void changeHeartRate() {
        System.out.print("Enter your current heart rate: ");
        double heartRate = sc.nextDouble();
        vitals.setHeartRate(heartRate);
        VitalsDatabase.vitals.put(this.getUserID(), vitals);
    }

    private void changeOxygenLevel() {
        System.out.print("Enter your current oxygen level: ");
        double oxygen = sc.nextDouble();
        vitals.setOxygenLevel(oxygen);
        VitalsDatabase.vitals.put(this.getUserID(), vitals);
    }

    private void changeBloodpressure() {
        System.out.print("Enter your current blood pressure (e.g. 120/80): ");
        String bp = sc.next();
        vitals.setBloodPressure(bp);
        VitalsDatabase.vitals.put(this.getUserID(), vitals);
    }

    private void changeTemperature() {
        System.out.print("Enter your current temperature: ");
        double temp = sc.nextDouble();
        vitals.setTemperature(temp);
        VitalsDatabase.vitals.put(this.getUserID(), vitals);
    }

    private void changeAllVitals() {
        changeHeartRate();
        changeOxygenLevel();
        changeBloodpressure();
        changeTemperature();
    }

    private void seeAllVitals() {
        System.out.println(VitalsDatabase.getAllVitals(this.getUserID()));
    }

    private void medicalHistory() {
        while (true) {
            System.out.println("Enter 1 to see prescription from a specific doctor");
            System.out.println("Enter 2 to see full medical history");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> { return; }
                case 1 -> seePrescription();
                case 2 -> MedicalHistory.seeMedicalHistory(this.getUserID());
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void seePrescription() {
        System.out.println(Prescription.seeprescriptions(this.getUserID()));
    }

    @Override
    public String toString() {
        return "Patient ID: " + this.getUserID() + ", Name: " + this.getName() + ", Age: " + this.getAge();
    }
}