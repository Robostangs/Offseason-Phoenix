package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultTeleOpCommand extends CommandBase {

    private ParallelCommandGroup m_commandGroup;
    private final DefaultDriveCommand m_driveCommand;
    private final DefaultIntakeCommand m_intakeCommand;
    private final DefaultFeederCommand m_feederCommand;

    public DefaultTeleOpCommand(DrivetrainSubsystem drivetrainSubsystem,
                                IntakeSubsystem intakeSubsystem,
                                FeederSubsystem feederSubsystem,
                                ShooterSubsystem shooterSubsystem,
                                LimelightSubsystem limelightSubsystem,
                                DoubleSupplier translationXSupplier,
                                DoubleSupplier translationYSupplier,
                                DoubleSupplier rotationSupplier,
                                DoubleSupplier intakeSpeedSupplier,
                                DoubleSupplier shooterSpeedSupplier,
                                BooleanSupplier aButtonSupplier) {
        m_driveCommand = new DefaultDriveCommand(drivetrainSubsystem,
                                                 translationXSupplier,
                                                 translationYSupplier,
                                                 rotationSupplier);
        m_intakeCommand = new DefaultIntakeCommand(intakeSubsystem, intakeSpeedSupplier);
        m_feederCommand = new DefaultFeederCommand(feederSubsystem, shooterSpeedSupplier);

        m_commandGroup = new ParallelCommandGroup(m_driveCommand, m_intakeCommand, m_feederCommand);

        addRequirements(intakeSubsystem);
        addRequirements(feederSubsystem);
        addRequirements(shooterSubsystem);
        addRequirements(drivetrainSubsystem);
        addRequirements(limelightSubsystem);
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
