package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final LimelightSubsystem m_limelightSubsystem;
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private final AutoShootCommand m_shootCommand;
    private PIDController m_turnPid;

    public DefaultShooterCommand(ShooterSubsystem shooterSubsystem,
                                LimelightSubsystem limelightSubsystem,
                                DrivetrainSubsystem drivetrainSubsystem,
                                AutoShootCommand shootCommand) {
        this.m_shooterSubsystem = shooterSubsystem; 
        this.m_limelightSubsystem = limelightSubsystem;
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_shootCommand = shootCommand;
        this.m_turnPid = new PIDController(0.08, 0.01, 0);

        addRequirements(shooterSubsystem);
        addRequirements(limelightSubsystem);
        addRequirements(drivetrainSubsystem);
        SmartDashboard.putNumber("Shooter Power:", 6000);
    }

    @Override
    public void execute() {
        double speed = SmartDashboard.getNumber("Shooter Power:", 6000);
        if(m_limelightSubsystem.getTv() == 1 ) {
            m_shooterSubsystem.setShooterMotorVelocity(-speed);
            double rotateSpeed = -m_turnPid.calculate(m_limelightSubsystem.getTx());
            m_drivetrainSubsystem.spin(rotateSpeed);
            if(Math.abs(rotateSpeed) < 0.5 && Math.abs(m_limelightSubsystem.getTx()) < 1) {
                System.out.println(m_shooterSubsystem.getFlywheelVelocity());
                if(m_shooterSubsystem.getFlywheelVelocity() >= speed - 100 &&
                   m_shooterSubsystem.getFlywheelVelocity() <= speed + 100) {
                    System.out.println("Shooting!");
                    m_shootCommand.schedule();
                }
            }
        } else {
            m_drivetrainSubsystem.spin(2);
        }
    }

    @Override
    public void end(boolean interrupted) {
            m_shooterSubsystem.setShooterMotorPower(0);
    }
}
