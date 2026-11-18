package frc.robot.commands.conveyanceCmds;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.coneveyanceSub.ConveyanceConstants;
import frc.robot.subsystems.coneveyanceSub.ConveyanceSUB;

public class BallsToIntakeCMD extends Command {
  private final ConveyanceSUB conveyanceSub;
  
  public BallsToIntakeCMD(ConveyanceSUB c) {
    conveyanceSub = c;
    addRequirements(conveyanceSub);
  }

  @Override
  public void initialize() {
    conveyanceSub.ballsToIntake(ConveyanceConstants.CONVEYANCE_SPEED_VOLTS);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    conveyanceSub.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
