package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase{

    private TalonFX m_intakeMotor;
    private DoubleSolenoid m_intakeSolenoidLeft, m_intakeSolenoidRight;
    private Compressor m_intakeCompressor;

    public IntakeSubsystem() {
        m_intakeMotor = new TalonFX(INTAKE_MOTOR);
        m_intakeSolenoidLeft = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, INTAKE_SOLENOID_LEFT_FORWARD, INTAKE_SOLENOID_LEFT_REVERSE);
        m_intakeSolenoidRight = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, INTAKE_SOLENOID_RIGHT_FORWARD, INTAKE_SOLENOID_RIGHT_REVERSE);
        m_intakeCompressor = new Compressor(INTAKE_COMPRESSOR, PneumaticsModuleType.CTREPCM);
        SmartDashboard.putBoolean("Solenoid", false);
    }

    public void setIntakePosition(boolean state) {
        if(state) {
            m_intakeSolenoidLeft.set(Value.kForward);
            m_intakeSolenoidRight.set(Value.kForward);
        } else {
            m_intakeSolenoidLeft.set(Value.kReverse);
            m_intakeSolenoidRight.set(Value.kReverse);
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
        m_intakeMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    
    public void stopIntake() {
        m_intakeMotor.set(TalonFXControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        setCompresser(true);
        setIntakePosition(SmartDashboard.getBoolean("Solenoid", false));
        super.periodic();
    }

    
}
    