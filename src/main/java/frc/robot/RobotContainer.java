// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Hood.HoodCMD;
import frc.robot.subsystems.Hood.HoodSUB;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private HoodSUB hood = new HoodSUB();
  private HoodCMD hoodCmd = new HoodCMD(hood);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    //Button bindings will be configured in the final merge
  }

  public Command getAutonomousCommand() {
    // No autonomous
    return null;
  }

}
