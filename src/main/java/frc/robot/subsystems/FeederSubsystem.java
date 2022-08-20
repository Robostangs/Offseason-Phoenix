package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class FeederSubsystem extends SubsystemBase {
    private final TalonSRX m_BeltMotor = new TalonSRX(FEEDER_BELT_MOTOR_ID);
    private final TalonSRX m_RollerMotor = new TalonSRX(FEEDER_ROLLER_MOTOR_ID);

    private final DigitalInput m_IntakeSensorDark = new DigitalInput(FEEDER_DARK_INTAKE_ID);
    private final DigitalInput m_ShooterSensorDark = new DigitalInput(FEEDER_DARK_SHOOTER_ID);
    private final DigitalInput m_IntakeSensorLight = new DigitalInput(FEEDER_LIGHT_INTAKE_ID);
    private final DigitalInput m_ShooterSensorLight = new DigitalInput(FEEDER_LIGHT_SHOOTER_ID);

    public FeederSubsystem() {
        m_BeltMotor.configFactoryDefault();
        m_BeltMotor.configMotionAcceleration(1500, 30);
        m_BeltMotor.configMotionCruiseVelocity(20000, 30);

        addChild("Intake Dark", m_IntakeSensorDark);
        addChild("Intake Light", m_IntakeSensorLight);
        addChild("Shooter Dark", m_ShooterSensorDark);
        addChild("Shooter Light", m_ShooterSensorLight);
    }

    public boolean getIntakeSensorDark() {
        return m_IntakeSensorDark.get();
    }

    public boolean getShooterSensorDark() {
        return m_ShooterSensorDark.get();
    }

    public boolean getIntakeSensorLight() {
        return m_IntakeSensorLight.get();
    }

    public boolean getShooterSensorLight() {
        return m_ShooterSensorLight.get();
    }

    public void setBeltPower(double beltPower) {
        m_BeltMotor.set(ControlMode.PercentOutput, beltPower);
    }

    public void setBeltPosition(double position) {
        m_BeltMotor.set(ControlMode.Position, position);
    }

    public double getBeltEncoderPos() {
        return m_BeltMotor.getActiveTrajectoryPosition();
    }

    public double getBeltVelocity() {
        return m_BeltMotor.getSelectedSensorVelocity();
    }

    public void resetBeltEncoder() {
        m_BeltMotor.setSelectedSensorPosition(0);
    }

    public void setRollerPower(double power) {
        m_RollerMotor.set(ControlMode.PercentOutput, power);
    }
}
