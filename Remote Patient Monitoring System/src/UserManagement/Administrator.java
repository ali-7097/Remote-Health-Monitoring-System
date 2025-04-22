package UserManagement;

import NotificationAndReminders.ReminderService;

import java.util.HashMap;
import java.util.Scanner;

public class Administrator extends User {
    public static HashMap<Integer, Administrator> administrators = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public Administrator(int userID, String name, int age, String gender, String address, String email) {
        super(userID, name, age, gender, address, email);
        administrators.put(userID, this);
    }

    public void loginAdministrator() {
        while (true) {
            System.out.println("Enter 1 to see all doctors");
            System.out.println("Enter 2 to see all patients");
            System.out.println("Enter 3 to remove doctors");
            System.out.println("Enter 4 to remove patients");
            System.out.println("Enter 5 to add patients");
            System.out.println("Enter 6 to send reminder to patient");
            System.out.println("Enter 0 to go back");
            System.out.print("Enter here: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 0 -> { return; }
                case 1 -> seeDoctors();
                case 2 -> seePatients();
                case 3 -> removeDoctor();
                case 4 -> removePatient();
                case 5 -> addPatient();
                case 6 -> sendReminderToPatient();
                default -> System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    private void seeDoctors() {
        System.out.println(Doctor.doctors);
    }

    private void seePatients() {
        System.out.println(Patient.patients);
    }

    private void removeDoctor() {
        System.out.print("Enter doctor ID to remove: ");
        int id = sc.nextInt();
        if (Doctor.doctors.containsKey(id)) {
            Doctor.doctors.remove(id);
            System.out.println("Doctor with ID " + id + " removed.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private void removePatient() {
        System.out.print("Enter patient ID to remove: ");
        int id = sc.nextInt();
        if (Patient.patients.containsKey(id)) {
            Patient.patients.remove(id);
            System.out.println("Patient with ID " + id + " removed.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void addPatient() {
        System.out.print("Enter patient ID: ");
        int id = sc.nextInt();
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        System.out.print("Enter gender: ");
        String gender = sc.next();
        System.out.print("Enter address: ");
        sc.nextLine();
        String address = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.next();

        new Patient(id, name, age, gender, address, email);
        System.out.println("Patient added successfully.");
    }

    private void sendReminderToPatient() {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Reminder Message: ");
        String msg = sc.nextLine();
        ReminderService.sendReminder(pid, msg);
    }
}
