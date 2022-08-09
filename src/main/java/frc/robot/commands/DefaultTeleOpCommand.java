package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class DefaultTeleOpCommand extends CommandBase {

    private ParallelCommandGroup m_commandGroup;
    private final DefaultDriveCommand m_driveCommand;
    private final DefaultIntakeCommand m_intakeCommand;

    public DefaultTeleOpCommand(DrivetrainSubsystem drivetrainSubsystem,
                                IntakeSubsystem intakeSubsystem,
                                DoubleSupplier translationXSupplier,
                                DoubleSupplier translationYSupplier,
                                DoubleSupplier rotationSupplier,
                                DoubleSupplier intakeSpeedSupplier) {
        m_driveCommand = new DefaultDriveCommand(drivetrainSubsystem, translationXSupplier, translationYSupplier, rotationSupplier);
        m_intakeCommand = new DefaultIntakeCommand(intakeSubsystem, intakeSpeedSupplier);
    }
    
    @Override
    public void execute() {
        m_commandGroup = new ParallelCommandGroup(m_driveCommand, m_intakeCommand);
        m_commandGroup.schedule();
    }

    @Override
    public void end(boolean interrupted) {
        m_commandGroup.cancel();
    }
}
