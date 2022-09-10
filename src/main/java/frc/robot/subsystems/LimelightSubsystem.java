package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class LimelightSubsystem extends SubsystemBase {

    private final static NetworkTable LimelightTable = NetworkTableInstance.getDefault().getTable("limelight-driver");

    private static double tx = 0;
    private static double ty = 0;
    private static double tv = 0;
    private static double ta = 0;

    public LimelightSubsystem() {
        if(LimelightTable == null) {
            DataLogManager.log("LLtable is Null"); 
        }
        LimelightTable.getEntry("pipeline").setNumber(0);

    }

    @Override
    public void periodic() {
        tx = LimelightTable.getEntry("tx").getDouble(0);
        ty = LimelightTable.getEntry("ty").getDouble(0);
        tv = LimelightTable.getEntry("tv").getDouble(0);
        ta = LimelightTable.getEntry("ta").getDouble(0);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("tx", () -> tx, null);
        builder.addDoubleProperty("ty", () -> ty, null);
        builder.addDoubleProperty("tv", () -> tv, null);
        builder.addDoubleProperty("ta", () -> ta, null);
        builder.addDoubleProperty("distance", LimelightSubsystem::getDistance, null);
    }

    public static double getDistance() {
        //System.out.println((Math.tan(degToRad(ty + Limelight.kLimelightAngle))));
        return Limelight.kTargetHeightDelta / (Math.tan(degToRad(ty + Limelight.kLimelightAngle)));
    }

    public static double degToRad(double x) {
        return (Math.PI / 180.0) *x;
    }

    public static double getTx() {
        return tx;
    }

    public static double getTy() {
        return ty;
    }

    public static int getTv() {
        return (int) Math.round(tv);
    }

    public static double getTa() {
        return ta;
    }

    public static void enableLEDs() {
        LimelightTable.getEntry("ledMode").setNumber(0);
    }

    public static void disableLEDs() {
        LimelightTable.getEntry("ledMode").setNumber(1);
    }

    public static void blinkLEDs() {
        LimelightTable.getEntry("ledMode").setNumber(2);
    }
}
