// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ShooterCMD;
import frc.robot.subsystems.ShooterSUB;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private ShooterSUB shooter = new ShooterSUB();
  private ShooterCMD shooterCmd = new ShooterCMD(shooter); 

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    m_driverController.a().whileTrue(shooterCmd); 
  }

  public Command getAutonomousCommand() {
    // No autonomous
    return null;
  }
}
