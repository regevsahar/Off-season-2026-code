package frc.robot.commands.shooterCmds;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooterSub.ShooterConstants;
import frc.robot.subsystems.shooterSub.ShooterSUB;

public class ShooterCMD extends Command {
  private final ShooterSUB shooterSub;
  
  public ShooterCMD(ShooterSUB s) {
    shooterSub = s;
    addRequirements(shooterSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooterSub.setTargetRpm(ShooterConstants.targetRpm.get());
  }

  @Override
  public void end(boolean interrupted) {
    shooterSub.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
