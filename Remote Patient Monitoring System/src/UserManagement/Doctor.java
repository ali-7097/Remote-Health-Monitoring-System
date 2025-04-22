package UserManagement;

import AppointmentScheduling.Appointment;
import AppointmentScheduling.AppointmentManager;
import ChatAndVedioConsultation.ChatClient;
import ChatAndVedioConsultation.VideoCall;
import ChatAndVedioConsultation.ChatServer;
import Doctor_PatientInteraction.Feedback;
import Doctor_PatientInteraction.MedicalHistory;
import Doctor_PatientInteraction.Prescription;
import HealthDataHandling.VitalsDatabase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Doctor extends User {
    private String specialization;
    static Scanner sc = new Scanner(System.in);

    public static HashMap<Integer, Doctor> doctors = new HashMap<>();
    public HashMap<Integer, Appointment> appointments = new HashMap<>();
    public HashSet<Integer> approveAppointments = new HashSet<>();
    public HashMap<Integer, String> messages = new HashMap<>();
    public HashSet<String> notifications = new HashSet<>();

    public Doctor(int userID, String name, int age, String gender, String specialization, String address, String email) {
        super(userID, name, age, gender, address, email);
        this.specialization = specialization;
        doctors.put(userID, this);
    }

    public static Doctor getDoctor(int userID) {
        return doctors.get(userID);
    }

    public void loginDoctor() {
        while (true) {
            System.out.println("Enter 1 to see all patients");
            System.out.println("Enter 2 to see all appointments");
            System.out.println("Enter 3 to view emergency notifications");
            System.out.println("Enter 4 to view messages");
            System.out.println("Enter 5 to reply to a message");
            System.out.println("Enter 6 to start chat");
            System.out.println("Enter 7 to start video call");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 0 -> { return; }
                case 1 -> viewAllPatients();
                case 2 -> viewAppointments();
                case 3 -> viewNotifications();
                case 4 -> viewMessages();
                case 5 -> replyToMessage();
                case 6 -> ChatClient.startChat(this.getUserID());
                case 7 -> VideoCall.startCall(this.getUserID());
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    public void viewNotifications() {
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
        if (Patient.getPatient(id) != null) {
            Patient.getPatient(id).messages.put(this.getUserID(), reply);
            ChatServer.logMessage(this.getUserID(), id, reply);
        } else {
            System.out.println("Invalid recipient.");
        }
    }

    private void viewAllPatients() {
        System.out.println(Patient.patients);
        while (true) {
            System.out.println("Enter 1 to view patient's data");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 0 -> { return; }
                case 1 -> viewPatientsData(Patient.patients);
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void viewPatientsData(HashMap<Integer, Patient> patients) {
        System.out.print("Enter patient's ID: ");
        int id = sc.nextInt();
        while (true) {
            System.out.println("Enter 1 to see medical history");
            System.out.println("Enter 2 to see heart rate");
            System.out.println("Enter 3 to see oxygen level");
            System.out.println("Enter 4 to see blood pressure");
            System.out.println("Enter 5 to see temperature");
            System.out.println("Enter 6 to see all vitals");
            System.out.println("Enter 7 to prescribe medicines");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 0 -> { return; }
                case 1 -> fullHistory(patients.get(id));
                case 2 -> System.out.println(VitalsDatabase.getHeartRate(id));
                case 3 -> System.out.println(VitalsDatabase.getOxygenLevel(id));
                case 4 -> System.out.println(VitalsDatabase.getBloodPressure(id));
                case 5 -> System.out.println(VitalsDatabase.getTemperature(id));
                case 6 -> System.out.println(VitalsDatabase.getAllVitals(id));
                case 7 -> prescribeMedicines(patients.get(id));
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void viewAppointments() {
        System.out.println(appointments);
        while (true) {
            System.out.println("Enter 1 to approve an appointment");
            System.out.println("Enter 2 to reject an appointment");
            System.out.println("Enter 3 to see all approved appointments");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 0 -> { return; }
                case 1 -> approveAppointment();
                case 2 -> rejectAppointment();
                case 3 -> seeApprovedAppointments();
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void approveAppointment() {
        System.out.print("Enter appointment ID to approve: ");
        int appointmentid = sc.nextInt();
        AppointmentManager.approveAppointment(appointmentid, this.getUserID());
    }

    private void rejectAppointment() {
        System.out.print("Enter appointment ID to reject: ");
        int appointmentid = sc.nextInt();
        AppointmentManager.rejectAppointment(appointmentid, this.getUserID());
    }

    private void seeApprovedAppointments() {
        AppointmentManager.viewApprovedAppointments(this.getUserID());
    }

    private void prescribeMedicines(Patient patient) {
        Feedback feedback = new Feedback(patient.getUserID(), this.getUserID());
        feedback.prescribeMedicines();
        feedback.prescribeSchedule();
        Prescription.prescriptions.put(patient.getUserID(), feedback);
        MedicalHistory.pastConsultants.put(this.getUserID(), patient);
    }

    private void fullHistory(Patient patient) {
        MedicalHistory.seeMedicalHistory(patient.getUserID());
    }

    @Override
    public String toString() {
        return "Doctor: Name=" + this.getName() + ", Age=" + this.getAge() + ", Specialization=" + this.specialization;
    }
}
