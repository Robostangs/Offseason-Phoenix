package frc.robot.subsystems;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
 
public class ShooterSubsystem extends SubsystemBase {
   private final TalonFX m_flywheelMotor;
   private final TalonFX m_hoodMotor;
 
   public ShooterSubsystem() {
       m_flywheelMotor = new TalonFX(SHOOTER_FLYWHEEL_MOTOR);
       m_hoodMotor = new TalonFX(SHOOTER_HOOD_MOTOR);
   }
 
   public double getFlywheelVelocity() {
       return m_flywheelMotor.getSelectedSensorVelocity() / 4096;
   }
 
   public double getHoodPosition() {
       double angle = ((m_hoodMotor.getSelectedSensorPosition() / 4096) * 2 * Math.PI) + Math.toRadians(SHOOTER_HOOD_MOTOR_ANGLE_OFFSET);
       return angle;
   }
 
   public void shoot(double distance){
       double magic_height = HOOP_HEIGHT - SHOOTER_HEIGHT;
       double shoot_angle = 45;
   }
 
   public void resetHood() {
       m_hoodMotor.set(ControlMode.PercentOutput, 0);
   }
 
 
}
