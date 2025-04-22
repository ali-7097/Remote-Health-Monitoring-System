package UserManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Preload sample patient data
        new Patient(101, "Ali Khan", 25, "Male", "Street 1, Islamabad", "ali.khan@example.com");
        new Patient(102, "Ayesha Tariq", 30, "Female", "Sector G-10, Islamabad", "ayesha.tariq@example.com");
        new Patient(103, "Bilal Ahmed", 22, "Male", "DHA, Karachi", "bilal.ahmed@example.com");
        new Patient(104, "Sara Malik", 28, "Female", "Johar Town, Lahore", "sara.malik@example.com");
        new Patient(105, "Hassan Raza", 35, "Male", "Model Town, Lahore", "hassan.raza@example.com");

        // Preload sample doctor data
        new Doctor(201, "Dr. Faisal Siddiqui", 45, "Male", "Cardiologist", "F-7 Clinic, Islamabad", "faisal.siddiqui@example.com");
        new Doctor(202, "Dr. Nadia Hassan", 38, "Female", "Dermatologist", "Clifton Medical Center, Karachi", "nadia.hassan@example.com");

        // Preload administrator
        new Administrator(2004, "Zain Ul Abideen", 40, "Male", "H-12, Islamabad", "zain.abideen@example.com");

        // Login prompt
        System.out.print("Enter your user ID to login: ");
        int id = sc.nextInt();
        User.login(id);
    }
}
