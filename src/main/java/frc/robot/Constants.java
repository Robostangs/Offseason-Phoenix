// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.60325;
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.60325;

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 2;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 6;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 23;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(282.2);

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 1;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 5;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 21;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(1.3);

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 3;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 7;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 22;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(156.2);

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 0;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 4;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 20;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(110.7);

    public static final int INTAKE_MOTOR = 30;
    public static final int INTAKE_SOLENOID_FORWARD = 4;
    public static final int INTAKE_SOLENOID_REVERSE = 5;
    public static final int INTAKE_COMPRESSOR = 31;


    public static final int FEEDER_BELT_MOTOR_ID = 0; // FIX ME: Add correct CAN ID
    public static final int FEEDER_ROLLER_MOTOR_ID = 0; // FIX ME: Add correct CAN ID

    public static final int FEEDER_DARK_INTAKE_ID = 0; // FIX ME: Add correct CAN ID
    public static final int FEEDER_LIGHT_INTAKE_ID = 0; // FIX ME: Add correct CAN ID
    public static final int FEEDER_DARK_SHOOTER_ID = 0; // FIX ME: Add correct CAN ID
    public static final int FEEDER_LIGHT_SHOOTER_ID = 0; // FIX ME: Add correct CAN ID
    
    public static final double FEEDER_DEBOUNCE_TIME = 0.1;
    public static final double FEEDER_BELT_SPEED = 0.3;
    public static final double FEEDER_ROLLER_SPEED = 0.2;

    public static class Limelight {
        public static final double kTargetHeight = 104.5;
        public static final double kLimelightHeight = 0;
        public static final double kTargetHeightDelta = kTargetHeight - kLimelightHeight;
        public static final double kLimelightAngle = 0;
    }


    public static final int SHOOTER_FLYWHEEL_MOTOR = 0; // FIX ME: Set the correct flywheel motor ID
    public static final int SHOOTER_HOOD_MOTOR = 0; // FIX ME: Set the correct hood motor ID
    public static final double SHOOTER_HOOD_MOTOR_ANGLE_OFFSET = 0; // FIX ME: Set the correct hood offset motor ID
    public static final double SHOOTER_FLYWHEEL_RADIUS = 0; // FIX ME: Set the flywheel diameter
    public static final double SHOOTER_HEIGHT = 0; // FIX ME: Set the shooter height
    public static final double HOOP_HEIGHT = 0; // FIX ME: Set the hoop height
}
