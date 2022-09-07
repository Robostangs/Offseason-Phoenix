package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultIntakeCommand extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;

    private final DoubleSupplier m_intakePowerSupplier;

    public DefaultIntakeCommand(IntakeSubsystem intakeSubsystem,
                               DoubleSupplier intakePowerSupplier) {
        this.m_intakeSubsystem = intakeSubsystem; 
        this.m_intakePowerSupplier = intakePowerSupplier;
        m_intakeSubsystem.setCompresser(true);

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.setIntakePosition(true);
        /*
        if(m_intakePowerSupplier.getAsDouble() < -0.1) {
            m_intakeSubsystem.setIntakePosition(true);
            m_intakeSubsystem.setIntakeMotorPower(m_intakePowerSupplier.getAsDouble());
        } else {
            m_intakeSubsystem.setIntakePosition(false);
            m_intakeSubsystem.stopIntake();
        }
        */
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakePosition(false);
        m_intakeSubsystem.setCompresser(false);
        m_intakeSubsystem.stopIntake();
    }
}
