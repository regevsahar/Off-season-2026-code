package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.Intake.IntakeRollerSUB;

public class IntakeBallCMD extends Command {
  private IntakeRollerSUB intakeRoller;
  
  public IntakeBallCMD(IntakeRollerSUB intakeRoller) {
    this.intakeRoller = intakeRoller;
    addRequirements(intakeRoller);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeRoller.intakeBall(IntakeConstants.INTAKE_ROLLER_SPEED.get());
  }

  @Override
  public void end(boolean interrupted) {
    intakeRoller.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
