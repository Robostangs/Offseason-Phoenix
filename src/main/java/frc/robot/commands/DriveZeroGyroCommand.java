package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveZeroGyroCommand extends CommandBase {
    private DrivetrainSubsystem m_drivetrainSubsystem;
    public DriveZeroGyroCommand(DrivetrainSubsystem drivetrainSubsystem) {
        m_drivetrainSubsystem = drivetrainSubsystem;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        m_drivetrainSubsystem.zeroGyroscope();
    }
}
