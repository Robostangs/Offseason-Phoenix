package frc.robot;

/*
ORIGINAL DATA:

    |----------+---------------|
    | Distance | Speed         |
    |----------+---------------|
    | 102 in   | 3300 RPM      |
    | 115 in   | 3350 RPM      |
    | 131 in   | 3600 RPM      |
    | 154 in   | 4100 RPM      |
    | 173 in   | 4850 RPM      | 
    |----------+---------------|
*/
public class ShooterEquation {

    public static double getSpeedWithDistance(double distance) {
        return ((0.2816 * Math.pow(distance, 2)) - (55.524 * distance) + 6027.64);
    }
}