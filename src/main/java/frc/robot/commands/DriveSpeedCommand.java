package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveSpeedCommand extends CommandBase {
    private DrivetrainSubsystem m_drivetrainSubsystem;
    private ChassisSpeeds m_speeds;
    public DriveSpeedCommand(DrivetrainSubsystem drivetrainSubsystem, ChassisSpeeds speeds) {
        m_drivetrainSubsystem = drivetrainSubsystem;
        m_speeds = speeds;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        m_drivetrainSubsystem.drive(m_speeds);
    }
}
