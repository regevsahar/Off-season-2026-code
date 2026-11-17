// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.commands.shooterCmds.ShooterCMD;
import frc.robot.subsystems.shooterSub.ShooterSUB;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private ShooterSUB shooter = new ShooterSUB();
  private ShooterCMD shooterCmd = new ShooterCMD(shooter); 

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SignalLogger.enableAutoLogging(false);
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {    
    m_driverController.a().whileTrue(shooterCmd); 

    //m_driverController.rightBumper().onTrue(Commands.runOnce(SignalLogger::start)); 
    //m_driverController.leftBumper().onTrue(Commands.runOnce(SignalLogger::stop)); 

    //m_driverController.y().whileTrue(shooter.sysIdQuasistatic(SysIdRoutine.Direction.kForward)); 
    //m_driverController.a().whileTrue(shooter.sysIdQuasistatic(SysIdRoutine.Direction.kReverse)); 
    //m_driverController.b().whileTrue(shooter.sysIdDynamic(SysIdRoutine.Direction.kForward)); 
    //m_driverController.x().whileTrue(shooter.sysIdDynamic(SysIdRoutine.Direction.kReverse)); 
  }

  public Command getAutonomousCommand() {
    // No autonomous
    return null;
  }
}