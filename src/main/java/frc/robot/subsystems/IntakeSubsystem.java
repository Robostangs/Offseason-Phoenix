package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase{

    private CANSparkMax ingester;

    private IntakeSubsystem()
    {
        ingester = new CANSparkMax(INGESTER, MotorType.kBrushless);
    }

    public void setIngestPower(double speed) {
        ingester.set(speed);
    }
    
    public void stopMotor() {
        ingester.stopMotor();
    }
}
