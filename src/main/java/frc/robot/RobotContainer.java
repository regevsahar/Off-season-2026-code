package frc.robot;

import frc.robot.commands.Intake.CloseIntakeCMD;
import frc.robot.commands.Intake.OpenIntakeCMD;
import frc.robot.subsystems.Intake.IntakeSUB;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class RobotContainer {

  private final CommandXboxController driver =  new CommandXboxController(Constants.kDriverControllerPort);
  private final CommandXboxController operator =  new CommandXboxController(Constants.kOperatorControllerPort);

  private final IntakeSUB Intake = new IntakeSUB();

  private final OpenIntakeCMD openIntake = new OpenIntakeCMD(Intake);
  private final CloseIntakeCMD closeIntake = new CloseIntakeCMD(Intake);


  public RobotContainer() {
    configureBindings();
  }

  
  private void configureBindings() {
    operator.povUp().whileTrue(openIntake);
    operator.povDown().whileTrue(closeIntake);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
