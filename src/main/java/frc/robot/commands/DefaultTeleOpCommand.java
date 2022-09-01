package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultTeleOpCommand extends CommandBase {

    private ParallelCommandGroup m_commandGroup;
    private final DefaultDriveCommand m_driveCommand;
    private final DefaultIntakeCommand m_intakeCommand;
    private final DefaultFeederCommand m_feederCommand;
    private final DefaultShooterCommand m_shooterCommand;

    public DefaultTeleOpCommand(DrivetrainSubsystem drivetrainSubsystem,
                                IntakeSubsystem intakeSubsystem,
                                FeederSubsystem feederSubsystem,
                                ShooterSubsystem shooterSubsystem,
                                DoubleSupplier translationXSupplier,
                                DoubleSupplier translationYSupplier,
                                DoubleSupplier rotationSupplier,
                                DoubleSupplier intakeSpeedSupplier,
                                DoubleSupplier shooterSpeedSupplier) {
        m_driveCommand = new DefaultDriveCommand(drivetrainSubsystem, translationXSupplier, translationYSupplier, rotationSupplier);
        m_intakeCommand = new DefaultIntakeCommand(intakeSubsystem, intakeSpeedSupplier);
        m_shooterCommand = new DefaultShooterCommand(shooterSubsystem, shooterSpeedSupplier);
        m_feederCommand = new DefaultFeederCommand(feederSubsystem, shooterSpeedSupplier);
        
        m_commandGroup = new ParallelCommandGroup(m_driveCommand, m_intakeCommand, m_feederCommand, m_shooterCommand);

        addRequirements(intakeSubsystem);
        addRequirements(feederSubsystem);
        addRequirements(shooterSubsystem);
        addRequirements(drivetrainSubsystem);
    }
    
    @Override
    public void execute() {
        m_commandGroup.schedule();
    }

    @Override
    public void end(boolean interrupted) {
        m_commandGroup.cancel();
    }
}
