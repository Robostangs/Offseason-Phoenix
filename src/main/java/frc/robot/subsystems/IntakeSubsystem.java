package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase{

    private TalonSRX m_intakeMotor;
    private DoubleSolenoid m_intakeSolenoid;
    private Compressor m_intakeCompressor;

    public IntakeSubsystem() {
        m_intakeMotor = new TalonSRX(INTAKE_MOTOR);
        m_intakeSolenoid = new DoubleSolenoid(INTAKE_COMPRESSOR, PneumaticsModuleType.CTREPCM, INTAKE_SOLENOID_FORWARD, INTAKE_SOLENOID_REVERSE);
        m_intakeCompressor = new Compressor(INTAKE_COMPRESSOR, PneumaticsModuleType.CTREPCM);
        SmartDashboard.putBoolean("Solenoid", false);
    }

    public void setIntakePosition(boolean state) {
        if(state) {
            m_intakeSolenoid.set(Value.kForward);
        } else {
            m_intakeSolenoid.set(Value.kReverse);
        }
    }

    public void setCompresser(boolean state) {
        if(state) {
            m_intakeCompressor.enableDigital();
        } else {
            m_intakeCompressor.disable();
        }
    }

    public void setIntakeMotorPower(double speed) {
        m_intakeMotor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    
    public void stopIntake() {
        m_intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        setCompresser(true);
        setIntakePosition(SmartDashboard.getBoolean("Solenoid", false));
        super.periodic();
    }
}    