package frc.robot.commands;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.FeederSubsystem;

public class DefaultFeederCommand extends CommandBase {

    private final FeederSubsystem m_feederSubsystem;
    private final Debouncer inDebouncer = new Debouncer(FEEDER_DEBOUNCE_TIME);
    private final Debouncer outDebouncer = new Debouncer(FEEDER_DEBOUNCE_TIME);
    
    public DefaultFeederCommand(FeederSubsystem feederSubsystem) {
        m_feederSubsystem = feederSubsystem;

        addRequirements(m_feederSubsystem);
        setName("Default Feeder Command");
    }

    @Override
    public void execute() {
        m_feederSubsystem.setBeltPower(FEEDER_BELT_SPEED);
        if (outDebouncer.calculate(m_feederSubsystem.getShooterSensorLight() || !m_feederSubsystem.getShooterSensorDark())) {
            m_feederSubsystem.setRollerPower(0);
        } else if (inDebouncer.calculate(m_feederSubsystem.getIntakeSensorLight() || !m_feederSubsystem.getIntakeSensorDark())) {
            m_feederSubsystem.setRollerPower(FEEDER_ROLLER_SPEED);
        }
    }

    public void end() {
        m_feederSubsystem.setBeltPower(0);
        m_feederSubsystem.setRollerPower(0);
    }
}