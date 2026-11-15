// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.conveyanceCmds;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.coneveyanceSub.ConveyanceConstants;
import frc.robot.subsystems.coneveyanceSub.ConveyanceSUB;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class BallsToIntakeCMD extends Command {
  private final ConveyanceSUB conveyanceSub;
  
  /** Creates a new ballsToThroatCMD. */
  public BallsToIntakeCMD(ConveyanceSUB c) {
    conveyanceSub = c;
    addRequirements(conveyanceSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    conveyanceSub.ballsToIntake(ConveyanceConstants.CONVEYANCE_SPEED_VOLTS);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyanceSub.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
