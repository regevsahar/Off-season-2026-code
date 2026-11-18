package frc.robot.commands.Hood;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood.HoodConstants;
import frc.robot.subsystems.Hood.HoodSUB;

public class HoodCMD extends Command {
  private final HoodSUB hoodSub;
  
  public HoodCMD(HoodSUB hood) {
    hoodSub = hood;
    addRequirements(hoodSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hoodSub.setTargetArc(HoodConstants.targetArc.get());
  }

  @Override
  public void end(boolean interrupted) {
    hoodSub.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
