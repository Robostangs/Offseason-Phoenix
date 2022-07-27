package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase{

    private TalonSRX ingester;

    private IntakeSubsystem()
    {
        ingester = new TalonSRX(INGESTER);
    }

    public void setIngestPower(double power) {
        ingester.set(ControlMode.PercentOutput, power);
    }
}
