package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultAuto extends SequentialCommandGroup {

    private DrivetrainSubsystem mDrivetrainSubsystem;
    private IntakeSubsystem mIntakeSubsystem;
    private FeederSubsystem mFeederSubsystem;
    private ShooterSubsystem mShooterSubsystem;
    private LimelightSubsystem mLimelightSubsystem;

    public DefaultAuto(DrivetrainSubsystem drivetrainSubsystem, 
                        IntakeSubsystem intakeSubsystem, 
                        FeederSubsystem feederSubsystem, 
                        ShooterSubsystem shooterSubsystem,
                        LimelightSubsystem limelightSubsystem) {

        mDrivetrainSubsystem = drivetrainSubsystem;
        mIntakeSubsystem = intakeSubsystem;
        mFeederSubsystem = feederSubsystem;
        mShooterSubsystem = shooterSubsystem;
        mLimelightSubsystem = limelightSubsystem;

        setName("Auto");
        
        addRequirements(mDrivetrainSubsystem, mIntakeSubsystem, mFeederSubsystem, mShooterSubsystem, mLimelightSubsystem);
        addCommands(
            new ParallelCommandGroup(
                new DefaultFeederCommand(mFeederSubsystem, () -> -1.0),
                new SequentialCommandGroup(
                    new ParallelDeadlineGroup(                    
                        new SequentialCommandGroup(
                            new InstantCommand(() -> mDrivetrainSubsystem.zeroGyroscope()),
                            new InstantCommand(() -> mDrivetrainSubsystem.drive(new ChassisSpeeds(-1.0, 0.0, 0.0))),
                            new WaitCommand(1.3),
                            new InstantCommand(() -> mDrivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0)))), 
                        new DefaultIntakeCommand(mIntakeSubsystem, () -> -1.0)),    
                    new DefaultShooterCommand(mShooterSubsystem, mLimelightSubsystem, mDrivetrainSubsystem, new AutoShootCommand(mFeederSubsystem)),
                    new WaitCommand(1.5),
                    new DefaultShooterCommand(mShooterSubsystem, mLimelightSubsystem, mDrivetrainSubsystem, new AutoShootCommand(mFeederSubsystem)),
                    new WaitCommand(1.5)
                )
            )
        );
    }

    @Override
    public void execute() {
        // new DefaultIntakeCommand(mIntakeSubsystem, () -> Constants.INTAKE_INGEST_SPEED);
        // new InstantCommand(() -> mDrivetrainSubsystem.zeroGyroscope());
        // new InstantCommand(() -> mDrivetrainSubsystem.drive(new ChassisSpeeds(-1.0, 0.0, 0.0)));
        // new WaitCommand(1.3);
        // new InstantCommand(() -> mDrivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0)));
        // new DefaultShooterCommand(mShooterSubsystem, mLimelightSubsystem, mDrivetrainSubsystem, new AutoShootCommand(mFeederSubsystem));
        // new WaitCommand(1.5);
        // new DefaultShooterCommand(mShooterSubsystem, mLimelightSubsystem, mDrivetrainSubsystem, new AutoShootCommand(mFeederSubsystem));
        // new WaitCommand(1.5);
        super.execute();
    }
    
}