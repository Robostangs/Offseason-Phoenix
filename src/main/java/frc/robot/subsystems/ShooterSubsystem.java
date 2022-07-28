package frc.robot.subsystems;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
 
 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class ShooterSubsystem extends SubsystemBase {
   private final TalonFX m_flywheelMotor;
   private final TalonFX m_hoodMotor;
   private double m_hoodMotorEncoderOffset;
 
   public ShooterSubsystem() {
       m_flywheelMotor = new TalonFX(Constants.Shooter.FLYWHEELMOTOR_ID);
       m_flywheelMotor.setInverted(Constants.Shooter.FLYWHEEL_INVERTED);
 
       m_hoodMotor = new TalonFX(Constants.Shooter.HOOD_MOTOR_ID);
       m_hoodMotor.setInverted(Constants.Shooter.HOOD_INVERTED);
 
       m_hoodMotorEncoderOffset = Constants.Shooter.HOOD_MOTOR_ENCODER_OFFSET;
   }
 
// 4096 encoder ticks = 2 radians
   public double getFlywheelVelocity() { // radius of the wheeel * velocity = linear velocity * trial & error (non stickiness)
      
       double nonConvertedVelocity = m_flywheelMotor.getSelectedSensorVelocity();
       return nonConvertedVelocity/4096;
   }
 
   public double getHoodPosition() {
       double angle = ((m_hoodMotor.getSelectedSensorPosition() + m_hoodMotorEncoderOffset) / 4096) * 2 * Math.PI;
       return angle;
   }
 
   public double getAngle(){
       double maxHeight = 0;
       double distanceFromMaxHeight = 0;
       return Math.atan((2*maxHeight)/(distanceFromMaxHeight));
   }
  
   public void shoot(){
 
       double radiusofWheel = 0.0; // update later
       double trialErrorNum = 4;
       double power = getFlywheelVelocity()*radiusofWheel*trialErrorNum;
       m_hoodMotor.set(ControlMode.PercentOutput, power);
       m_hoodMotor.set(ControlMode.PercentOutput, -power);
   }
 
   public void flywheelGo(){
       double radiusofWheel = 0.0; // update later
       double trialErrorNum = 4;
       double power = getFlywheelVelocity()*radiusofWheel*trialErrorNum;
       m_flywheelMotor.set(ControlMode.PercentOutput, power);
       m_flywheelMotor.set(ControlMode.PercentOutput, -power);
   }
 
   public void stop(){
       m_flywheelMotor.set(ControlMode.PercentOutput, 0);
       m_hoodMotor.set(ControlMode.PercentOutput, 0);
   }
 
 
}
