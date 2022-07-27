package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DefaultAutoCommand extends CommandBase {
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private Trajectory m_trajectory;

    public DefaultAutoCommand(DrivetrainSubsystem drivetrainSubsystem, String pathJson) {
        m_drivetrainSubsystem = drivetrainSubsystem;
        m_trajectory = null;

        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(pathJson);
            m_trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + pathJson, ex.getStackTrace());
        }
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
          1.2, // Max speed in meters per second
          3) // Max acceleration in meters per second squared
        .setKinematics(m_drivetrainSubsystem.getKinematics());

        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
          new Pose2d(0, 0, new Rotation2d(0)),
          List.of(),
          new Pose2d(2, 1, new Rotation2d(0)),
          trajectoryConfig
        );

        PIDController xController = new PIDController(0.1, 0, 0);
        PIDController yController = new PIDController(0.1, 0, 0);
        ProfiledPIDController thetaController = new ProfiledPIDController(
          0.1, 0, 0,
          new TrapezoidProfile.Constraints(
            Math.PI * 0.4,
            Math.PI / 4)
          );
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
          trajectory,
          m_drivetrainSubsystem::getPose,
          m_drivetrainSubsystem.getKinematics(),
          xController,
          yController,
          thetaController,
          m_drivetrainSubsystem::setModuleStates,
          m_drivetrainSubsystem);

        new SequentialCommandGroup( new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(trajectory.getInitialPose())),
        swerveControllerCommand,
        new InstantCommand(() -> m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0)))).execute();
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}

