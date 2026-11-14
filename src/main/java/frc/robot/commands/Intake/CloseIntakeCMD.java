package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.Intake.IntakeSUB;

public class CloseIntakeCMD extends Command {
  private IntakeSUB intake;

  public CloseIntakeCMD(IntakeSUB intake) {
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    System.out.println("Command Close is ON");
  }

  @Override
  public void execute() {
    intake.setTargetPosition(IntakeConstants.closeTargetPosition.get());
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return intake.isAtTargetPosition(IntakeConstants.closeTargetPosition.get());
  }
}
