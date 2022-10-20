// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD licens file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final FeederSubsystem m_feederSubsystem = new FeederSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem("limelight-driver");

  private final XboxController m_DriverController = new XboxController(0);
  private final XboxController m_ManipController = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    //TODO: FIX THIS
    m_drivetrainSubsystem.setDefaultCommand(new DefaultTeleOpCommand(
            m_drivetrainSubsystem,
            m_intakeSubsystem,
            m_feederSubsystem,
            m_shooterSubsystem,
            m_limelightSubsystem,
            () -> -modifyAxis(m_DriverController.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_DriverController.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(-m_DriverController.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
            () -> -modifyAxis(m_ManipController.getRightTriggerAxis()),
            () -> -modifyAxis(m_ManipController.getLeftTriggerAxis()),
            () -> m_DriverController.getAButton()
    ));

    new JoystickButton(m_ManipController, XboxController.Button.kA.value)
      .whileHeld(new DefaultShooterCommand(m_shooterSubsystem,
                                          m_limelightSubsystem,
                                          m_drivetrainSubsystem,
                                          new AutoShootCommand(m_feederSubsystem)
      )
    );

    new JoystickButton(m_ManipController, XboxController.Button.kB.value)
      .whileHeld(new ManualShootCommand(m_feederSubsystem, m_shooterSubsystem, MANUAL_SHOOT_SPEED));
    
    new JoystickButton(m_ManipController, XboxController.Button.kY.value)
      .whileHeld(new ManualShootCommand(m_feederSubsystem, m_shooterSubsystem, -2000));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Back button zeros the gyroscope
    new Button(m_DriverController::getBackButton)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_drivetrainSubsystem::zeroGyroscope);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
      return new DefaultAuto(m_drivetrainSubsystem, m_intakeSubsystem, m_feederSubsystem, m_shooterSubsystem, m_limelightSubsystem);
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}
