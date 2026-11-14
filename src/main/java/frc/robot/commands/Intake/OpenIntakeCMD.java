package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.Intake.IntakeSUB;

public class OpenIntakeCMD extends Command {
  private IntakeSUB intake;

  public OpenIntakeCMD(IntakeSUB intake) {
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    System.out.println("Command Open is ON");
  }

  @Override
  public void execute() {
    intake.setTargetPosition(IntakeConstants.openTargetPosition.get());
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return intake.isAtTargetPosition(IntakeConstants.openTargetPosition.get());
  }
}
