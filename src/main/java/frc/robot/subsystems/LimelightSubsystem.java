package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable m_limelightTable;

    private double tx = 0;
    private double ty = 0;
    private double tv = 0;
    private double ta = 0;

    public LimelightSubsystem(String limelightName) {
        m_limelightTable = NetworkTableInstance.getDefault().getTable(limelightName);
        if(m_limelightTable == null) {
            DataLogManager.log("LLtable is Null"); 
        }
        m_limelightTable.getEntry("pipeline").setNumber(0);

    }

    @Override
    public void periodic() {
        tx = m_limelightTable.getEntry("tx").getDouble(0);
        ty = m_limelightTable.getEntry("ty").getDouble(0);
        tv = m_limelightTable.getEntry("tv").getDouble(0);
        ta = m_limelightTable.getEntry("ta").getDouble(0);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("tx", () -> tx, null);
        builder.addDoubleProperty("ty", () -> ty, null);
        builder.addDoubleProperty("tv", () -> tv, null);
        builder.addDoubleProperty("ta", () -> ta, null);
        builder.addDoubleProperty("distance", this::getDistance, null);
    }

    public double getDistance() {
        //System.out.println((Math.tan(degToRad(ty + Limelight.kLimelightAngle))));
        return Limelight.kTargetHeightDelta / (Math.tan(degToRad(ty + Limelight.kLimelightAngle)));
    }

    public double degToRad(double x) {
        return (Math.PI / 180.0) *x;
    }

    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }

    public int getTv() {
        return (int) Math.round(tv);
    }

    public double getTa() {
        return ta;
    }

    public void enableLEDs() {
        m_limelightTable.getEntry("ledMode").setNumber(0);
    }

    public void disableLEDs() {
        m_limelightTable.getEntry("ledMode").setNumber(1);
    }

    public void blinkLEDs() {
        m_limelightTable.getEntry("ledMode").setNumber(2);
    }
}
