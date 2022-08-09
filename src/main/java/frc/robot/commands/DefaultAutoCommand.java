package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
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

public class DefaultAutoCommand extends CommandBase {
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private SequentialCommandGroup m_commandGroup;
    private Trajectory m_trajectory = null;

    public DefaultAutoCommand(DrivetrainSubsystem drivetrainSubsystem, String pathJson) {
        m_drivetrainSubsystem = drivetrainSubsystem;

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
          m_trajectory,
          m_drivetrainSubsystem::getPose,
          m_drivetrainSubsystem.getKinematics(),
          xController,
          yController,
          thetaController,
          m_drivetrainSubsystem::setModuleStates,
          m_drivetrainSubsystem);

        m_commandGroup = new SequentialCommandGroup(
          new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(m_trajectory.getInitialPose())),
          swerveControllerCommand,
          new InstantCommand(() -> m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0))));

        m_commandGroup.schedule();
    }

    @Override
    public void end(boolean interrupted) {
      m_commandGroup.cancel();
    }
}

