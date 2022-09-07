package frc.robot.commands;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.FeederSubsystem;

public class DefaultFeederCommand extends CommandBase {

    private final FeederSubsystem m_feederSubsystem;
    private final Debouncer inDebouncer = new Debouncer(FEEDER_DEBOUNCE_TIME);
    private final Debouncer outDebouncer = new Debouncer(FEEDER_DEBOUNCE_TIME);
    
    private final DoubleSupplier m_shooterPowerSupplier;
    
    public DefaultFeederCommand(FeederSubsystem feederSubsystem, DoubleSupplier shooterPowerSupplier) {
        m_feederSubsystem = feederSubsystem;
        m_shooterPowerSupplier = shooterPowerSupplier; addRequirements(m_feederSubsystem);
        setName("Default Feeder Command");
    }

    @Override
    public void execute() {
        if (inDebouncer.calculate(m_feederSubsystem.getIntakeSensorLight() || !m_feederSubsystem.getIntakeSensorDark())) {
            m_feederSubsystem.setBeltPower(FEEDER_BELT_SPEED);
        } else {
            m_feederSubsystem.setBeltPower(0);
        }
        if (outDebouncer.calculate(m_feederSubsystem.getShooterSensorLight() || !m_feederSubsystem.getShooterSensorDark())) {
            m_feederSubsystem.setRollerPower(FEEDER_ROLLER_INTAKE_SPEED);
        } else {
            m_feederSubsystem.setRollerPower(0);
        }

        SmartDashboard.putNumber("SHOOT POWER", m_shooterPowerSupplier.getAsDouble());
        if(m_shooterPowerSupplier.getAsDouble() < -0.1) {
            m_feederSubsystem.setRollerPower(FEEDER_ROLLER_SHOOT_SPEED);
        }
    }

    public void end() {
        m_feederSubsystem.setBeltPower(0);
        m_feederSubsystem.setRollerPower(0);
    }
}