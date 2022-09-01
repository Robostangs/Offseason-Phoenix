package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class DefaultExgestCommand extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;

    public DefaultExgestCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem; 
        m_intakeSubsystem.setCompresser(true);

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.setIntakePosition(true);
        m_intakeSubsystem.setIntakeMotorPower(Constants.INTAKE_EXGEST_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakePosition(false);
        m_intakeSubsystem.setCompresser(false);
        m_intakeSubsystem.stopIntake();
    }
}
