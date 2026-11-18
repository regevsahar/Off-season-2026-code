// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Hood
import frc.robot.commands.Hood.HoodCMD;
import frc.robot.subsystems.Hood.HoodSUB;

// Conveyance
import frc.robot.commands.conveyanceCmds.BallsToIntakeCMD;
import frc.robot.commands.conveyanceCmds.BallsToThroatCMD;
import frc.robot.subsystems.coneveyanceSub.ConveyanceSUB;

// Shooter
import frc.robot.commands.shooterCmds.ShooterCMD;
import frc.robot.subsystems.shooterSub.ShooterSUB;

// Intake
import frc.robot.commands.Intake.CloseIntakeCMD;
import frc.robot.commands.Intake.OpenIntakeCMD;
import frc.robot.subsystems.Intake.IntakeSUB;

// Intake Roller
import frc.robot.commands.Intake.IntakeBallCMD;
import frc.robot.commands.Intake.OuttakeBallCMD;
import frc.robot.subsystems.Intake.IntakeRollerSUB;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Hood
  private final HoodSUB hood = new HoodSUB();
  private final HoodCMD hoodCmd = new HoodCMD(hood);

  // Conveyance
  private final ConveyanceSUB conveyance = new ConveyanceSUB();
  private final BallsToThroatCMD forwardConveyance = new BallsToThroatCMD(conveyance);
  private final BallsToIntakeCMD backwardConveyance = new BallsToIntakeCMD(conveyance);

  // Shooter
  private final ShooterSUB shooter = new ShooterSUB();
  private final ShooterCMD shooterCmd = new ShooterCMD(shooter);

  // Intake (Pivot)
  private final IntakeSUB intake = new IntakeSUB();
  private final OpenIntakeCMD openIntake = new OpenIntakeCMD(intake);
  private final CloseIntakeCMD closeIntake = new CloseIntakeCMD(intake);

  // Intake Roller
  private final IntakeRollerSUB intakeRoller = new IntakeRollerSUB();
  private final IntakeBallCMD intakeBall = new IntakeBallCMD(intakeRoller);
  private final OuttakeBallCMD outtakeBall = new OuttakeBallCMD(intakeRoller);

  // Controllers
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.kDriverControllerPort);
  private final CommandXboxController operator =
      new CommandXboxController(Constants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SignalLogger.enableAutoLogging(false);
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings.
   */
  private void configureBindings() {
    // Conveyance bindings
    m_driverController.rightBumper().whileTrue(forwardConveyance);
    m_driverController.leftBumper().whileTrue(backwardConveyance);

    // Shooter bindings
    m_driverController.a().whileTrue(shooterCmd);

    // Intake (Pivot) bindings
    operator.povUp().whileTrue(openIntake);
    operator.povDown().whileTrue(closeIntake);

    // Intake Roller bindings
    operator.rightTrigger().whileTrue(intakeBall);
    operator.leftTrigger().whileTrue(outtakeBall);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // No autonomous
    return null;
  }
}
