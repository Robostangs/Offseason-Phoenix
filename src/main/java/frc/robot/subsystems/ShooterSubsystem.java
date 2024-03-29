package frc.robot.subsystems;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
 
public class ShooterSubsystem extends SubsystemBase {
    private final TalonFX m_flywheelMotor;
 
    public ShooterSubsystem() {
       m_flywheelMotor = new TalonFX(SHOOTER_FLYWHEEL_MOTOR);
       SmartDashboard.putNumber("ShooterVelo", 0);
       m_flywheelMotor.configAllSettings(SHOOTER_CONFIGURATION);
    }
 
    public double getFlywheelVelocity() {
       return m_flywheelMotor.getSelectedSensorVelocity() * (600.0 / 2048.0);
    }

    public void setShooterMotorVelocity(double velo) {
        m_flywheelMotor.set(ControlMode.Velocity, velo / (600.0 / 2048.0));
    }
    
    public void setShooterMotorPower(double power) {
       m_flywheelMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putNumber("Set Shooter Speed", getFlywheelVelocity());
    }
}
