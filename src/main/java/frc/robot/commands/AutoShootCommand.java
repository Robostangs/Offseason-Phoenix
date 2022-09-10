package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import frc.robot.subsystems.FeederSubsystem;

public class AutoShootCommand extends CommandBase {

    private final FeederSubsystem m_feederSubsystem;
    
    public AutoShootCommand(FeederSubsystem feederSubsystem) {
        m_feederSubsystem = feederSubsystem;

        setName("Default Feeder Command");
    }

    @Override
    public void execute() {
        m_feederSubsystem.setBeltPower(0);
        m_feederSubsystem.setRollerPower(FEEDER_ROLLER_SHOOT_SPEED);
    }

    public void end() {
        m_feederSubsystem.setBeltPower(0);
        m_feederSubsystem.setRollerPower(0);
    }
}