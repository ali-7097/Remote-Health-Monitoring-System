package HealthDataHandling;

import java.util.HashMap;

public class VitalsDatabase {
    public static HashMap<Integer, VitalSign> vitals = new HashMap<>();

    public static String getHeartRate(int patientid) {
        if (vitals.containsKey(patientid))
            return "Heart rate: " + vitals.get(patientid).getHeartRate();
        else
            return "No such patient exists.";
    }

    public static String getOxygenLevel(int patientid) {
        if (vitals.containsKey(patientid))
            return "Oxygen level: " + vitals.get(patientid).getOxygenLevel();
        else
            return "No such patient exists.";
    }

    public static String getBloodPressure(int patientid) {
        if (vitals.containsKey(patientid))
            return "Blood pressure: " + vitals.get(patientid).getBloodPressure();
        else
            return "No such patient exists.";
    }

    public static String getTemperature(int patientid) {
        if (vitals.containsKey(patientid))
            return "Temperature: " + vitals.get(patientid).getTemperature();
        else
            return "No such patient exists.";
    }

    public static String getAllVitals(int patientid) {
        if (vitals.containsKey(patientid))
            return vitals.get(patientid).toString();
        else
            return "No such patient exists.";
    }
}
