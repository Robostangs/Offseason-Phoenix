package frc.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShooterMappings {

    public static final HashMap<Double, Double> kShooterStates;

    static {
        kShooterStates = new HashMap<Double, Double>();
        kShooterStates.put(131.0, 3600.0);
        kShooterStates.put(102.0, 3300.0);
        kShooterStates.put(173.0, 4850.0);
        kShooterStates.put(154.0, 4100.0);
        kShooterStates.put(115.0, 3350.0); 
    };
    private static final ArrayList<Double> kDistances = new ArrayList<Double>();
    static {
        kDistances.addAll(kShooterStates.keySet());
        Collections.sort(kDistances);
    }

    public static double getNearestKey(double distance) {
        int index = Collections.binarySearch(kDistances, distance);
        if (index >= 0) {
            return kDistances.get(index);
        } else {
            index = -index - 1;
            if(index < 1) {
                return kDistances.get(0);
            } else if(index >= kDistances.size()) {
                return kDistances.get(kDistances.size() - 1);
            }
            double below = kDistances.get(index - 1);
            double above = kDistances.get(index);
            return (distance - below) > (above - distance) ? above : below;
        }
    }

    public static double getValueWithDistance(double distance) {
        return ((0.2816 * Math.pow(distance, 2)) - (55.524 * distance) + 6027.64);
    }

    public static Double getShooterState(double distance) {
        return kShooterStates.get(getNearestKey(distance));
    }
}