package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;

    private final DoubleSupplier m_shooterPowerSupplier;

    public DefaultShooterCommand(ShooterSubsystem shooterSubsystem,
                               DoubleSupplier shooterPowerSupplier) {
        this.m_shooterSubsystem = shooterSubsystem; 
        this.m_shooterPowerSupplier = shooterPowerSupplier;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        m_shooterSubsystem.setShooterMotorPower(-1.0);
    }

    @Override
    public void end(boolean interrupted) {
            m_shooterSubsystem.setShooterMotorPower(0);
    }
}
