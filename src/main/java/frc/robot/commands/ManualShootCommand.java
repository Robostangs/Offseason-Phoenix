package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShootCommand extends CommandBase {

    private final FeederSubsystem m_feederSubsystem;
    private final ShooterSubsystem m_shooterSubsystem;
    
    public ManualShootCommand(FeederSubsystem feederSubsystem, ShooterSubsystem shooterSubsystem) {
        m_feederSubsystem = feederSubsystem;
        m_shooterSubsystem = shooterSubsystem;

        setName("Default Feeder Command");
    }

    @Override
    public void execute() {
        m_shooterSubsystem.setShooterMotorVelocity(MANUAL_SHOOT_SPEED);
        System.out.println(m_shooterSubsystem.getFlywheelVelocity());
        if(m_shooterSubsystem.getFlywheelVelocity() >= MANUAL_SHOOT_SPEED - 100 &&
            m_shooterSubsystem.getFlywheelVelocity() <= MANUAL_SHOOT_SPEED + 100) {
                System.out.println("Shooting!");
                m_feederSubsystem.setBeltPower(0);
                m_feederSubsystem.setRollerPower(FEEDER_ROLLER_SHOOT_SPEED);
        }
    }

    public void end() {
        m_feederSubsystem.setBeltPower(0);
        m_feederSubsystem.setRollerPower(0);
    }
}