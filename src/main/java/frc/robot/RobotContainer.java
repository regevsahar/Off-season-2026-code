package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Intake.IntakeBallCMD;
import frc.robot.commands.Intake.OuttakeBallCMD;
import frc.robot.subsystems.Intake.IntakeRollerSUB;


public class RobotContainer {

  private final CommandXboxController driver =  new CommandXboxController(Constants.kDriverControllerPort);
  private final CommandXboxController operator =  new CommandXboxController(Constants.kOperatorControllerPort);

  private final IntakeRollerSUB Intake = new IntakeRollerSUB();

  private final IntakeBallCMD intakeBall = new IntakeBallCMD(Intake);
  private final OuttakeBallCMD outtakeBall = new OuttakeBallCMD(Intake);


  public RobotContainer() {
    configureBindings();
  }

  
  private void configureBindings() {
    operator.rightTrigger().whileTrue(intakeBall);
    operator.leftTrigger().whileTrue(outtakeBall);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}