package Doctor_PatientInteraction;

import java.util.ArrayList;
import java.util.Scanner;

public class Feedback {
    private String feedback = "Nil";
    private int patientid;
    private int doctorid;

    static Scanner sc = new Scanner(System.in);
    ArrayList<String> medicines = new ArrayList<>();
    ArrayList<String> schedules = new ArrayList<>();

    public Feedback(int patientid, int doctorid) {
        this.patientid = patientid;
        this.doctorid = doctorid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void prescribeMedicines() {
        System.out.print("How many medicines to prescribe: ");
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            System.out.print("Enter medicine " + (i + 1) + ": ");
            String medicine = sc.next();
            medicines.add(medicine);
        }
    }

    public void prescribeSchedule() {
        for (String med : medicines) {
            System.out.print("Enter schedule for " + med + ": ");
            String schedule = sc.next();
            schedules.add(schedule);
        }
        collectFeedback();
    }

    private void collectFeedback() {
        System.out.print("Do you want to leave feedback for this patient (Y/N)? ");
        char response = sc.next().charAt(0);
        sc.nextLine(); // clear buffer

        if (response == 'Y' || response == 'y') {
            System.out.print("Enter your feedback: ");
            feedback = sc.nextLine();
        } else if (response == 'N' || response == 'n') {
            feedback = "";
        } else {
            System.out.println("Invalid input. Try again.");
            collectFeedback();
        }
    }

    @Override
    public String toString() {
        return "\n--- Prescription Details ---" +
                "\nDoctor ID: " + doctorid +
                "\nPatient ID: " + patientid +
                "\nMedicines: " + medicines +
                "\nSchedules: " + schedules +
                "\nFeedback: " + (feedback.isEmpty() ? "No feedback" : feedback);
    }
}
