package UserManagement;

import java.util.HashSet;
import java.util.Scanner;

public class User {
    static Scanner sc = new Scanner(System.in);
    private static HashSet<Integer> userIDs = new HashSet<>();

    private int userID;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String email;

    public User(int userID, String name, int age, String gender, String address, String email) {
        setUserID(userID);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
    }

    private void setUserID(int userID) {
        if (userIDs.contains(userID))
            throw new IllegalArgumentException("User ID '" + userID + "' already exists.");
        else {
            this.userID = userID;
            userIDs.add(userID);
        }
    }

    public int getUserID() { return userID; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }

    private static void logout(int userID) {
        while (true) {
            System.out.println("Enter 1 to login again.");
            System.out.println("Enter 0 to end the program.");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 0:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                case 1:
                    System.out.print("Enter user id: ");
                    int id = sc.nextInt();
                    login(id);
                    break;
                default:
                    System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }

    public static void login(int userID) {
        while (true) {
            System.out.println("Enter 1 to login as a Doctor");
            System.out.println("Enter 2 to login as a Patient");
            System.out.println("Enter 3 to login as an Administrator");
            System.out.println("Enter 4 to logout");
            System.out.print("Enter here: ");
            int answer = sc.nextInt();

            switch (answer) {
                case 1:
                    if (!Doctor.doctors.containsKey(userID))
                        System.out.println("UserID " + userID + " is not a doctor");
                    else
                        Doctor.doctors.get(userID).loginDoctor();
                    break;
                case 2:
                    if (!Patient.patients.containsKey(userID))
                        System.out.println("UserID " + userID + " is not a patient");
                    else
                        Patient.patients.get(userID).loginPatient();
                    break;
                case 3:
                    if (!Administrator.administrators.containsKey(userID))
                        System.out.println("UserID " + userID + " is not an administrator");
                    else
                        Administrator.administrators.get(userID).loginAdministrator();
                    break;
                case 4:
                    logout(userID);
                    break;
                default:
                    System.out.println("Incorrect input! Enter a valid value");
            }
        }
    }
}
