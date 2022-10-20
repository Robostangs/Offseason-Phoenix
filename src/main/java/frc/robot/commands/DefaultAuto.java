package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultAuto extends SequentialCommandGroup {

    private DrivetrainSubsystem m_drivetrainSubsystem;
    private IntakeSubsystem m_intakeSubsystem;
    private FeederSubsystem m_feederSubsystem;
    private ShooterSubsystem m_shooterSubsystem;
    private LimelightSubsystem m_limelightSubsystem;

    public DefaultAuto(DrivetrainSubsystem drivetrainSubsystem,
            IntakeSubsystem intakeSubsystem,
            FeederSubsystem feederSubsystem,
            ShooterSubsystem shooterSubsystem,
            LimelightSubsystem limelightSubsystem) {

        m_drivetrainSubsystem = drivetrainSubsystem;
        m_intakeSubsystem = intakeSubsystem;
        m_feederSubsystem = feederSubsystem;
        m_shooterSubsystem = shooterSubsystem;
        m_limelightSubsystem = limelightSubsystem;

        setName("Auto");
        
        addRequirements(m_drivetrainSubsystem, m_intakeSubsystem, m_feederSubsystem, m_shooterSubsystem, m_limelightSubsystem);
        addCommands(
            new ParallelCommandGroup(
                new DefaultFeederCommand(m_feederSubsystem, () -> -1.0),
                new SequentialCommandGroup(
                    new ParallelDeadlineGroup(                    
                        new SequentialCommandGroup(
                            new DriveZeroGyroCommand(m_drivetrainSubsystem),
                            new DriveSpeedCommand(m_drivetrainSubsystem, new ChassisSpeeds(-1.0, 0.0, 0.0)),
                            new WaitCommand(1.3),
                            new DriveSpeedCommand(m_drivetrainSubsystem, new ChassisSpeeds(0.0, 0.0, 0.0))
                        ), 
                        new DefaultIntakeCommand(m_intakeSubsystem, () -> -1.0)
                    ),    
                    new DefaultShooterCommand(m_shooterSubsystem, m_limelightSubsystem, m_drivetrainSubsystem, new AutoShootCommand(m_feederSubsystem)),
                    new WaitCommand(1.5),
                    new DefaultShooterCommand(m_shooterSubsystem, m_limelightSubsystem, m_drivetrainSubsystem, new AutoShootCommand(m_feederSubsystem)),
                    new WaitCommand(1.5)
                )
            )
        );
    }

    @Override
    public void execute() {
<<<<<<< HEAD
        // new DefaultIntakeCommand(mIntakeSubsystem, () ->
        // Constants.INTAKE_INGEST_SPEED);
        // new InstantCommand(() -> mDrivetrainSubsystem.zeroGyroscope());
        // new InstantCommand(() -> mDrivetrainSubsystem.drive(new ChassisSpeeds(-1.0,
        // 0.0, 0.0)));
        // new WaitCommand(1.3);
        // new InstantCommand(() -> mDrivetrainSubsystem.drive(new ChassisSpeeds(0.0,
        // 0.0, 0.0)));
        // new DefaultShooterCommand(mShooterSubsystem, mLimelightSubsystem,
        // mDrivetrainSubsystem, new AutoShootCommand(mFeederSubsystem));
        // new WaitCommand(1.5);
        // new DefaultShooterCommand(mShooterSubsystem, mLimelightSubsystem,
        // mDrivetrainSubsystem, new AutoShootCommand(mFeederSubsystem));
        // new WaitCommand(1.5);
=======
>>>>>>> a84ae2680f23d17c26c18f1b6ed4b507b512c6bc
        super.execute();
    }

}
