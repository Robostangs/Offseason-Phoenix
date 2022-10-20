package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase{

    private TalonSRX m_intakeMotor;
    private Solenoid m_intakeSolenoid;
    private Compressor m_intakeCompressor;

    public IntakeSubsystem() {
        m_intakeMotor = new TalonSRX(INTAKE_MOTOR);
        m_intakeSolenoid = new Solenoid(INTAKE_COMPRESSOR, PneumaticsModuleType.CTREPCM, INTAKE_SOLENOID_FORWARD);
        m_intakeCompressor = new Compressor(INTAKE_COMPRESSOR, PneumaticsModuleType.CTREPCM);
        SmartDashboard.putBoolean("Solenoid", false);
    }

    public void setIntakePosition(boolean state) {
        m_intakeSolenoid.set(state);
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
        super.periodic();
    }
}    