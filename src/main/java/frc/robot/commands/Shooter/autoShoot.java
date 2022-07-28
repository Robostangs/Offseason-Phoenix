package frc.robot.commands.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class autoShoot extends CommandBase {
    private ShooterSubsystem mShooter;

    public autoShoot(){
        mShooter = new ShooterSubsystem();
    }

    // @Override
    // public void initialize() {
    //     super.initialize();
    // }

    @Override
    public void execute() {
        mShooter.flywheelGo();
        mShooter.shoot();
        // super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        mShooter.stop();
        // super.end(interrupted);
    }

}
