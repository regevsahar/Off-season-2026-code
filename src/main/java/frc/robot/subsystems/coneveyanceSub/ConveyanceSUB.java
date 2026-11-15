// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.coneveyanceSub;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyanceSUB extends SubsystemBase {
  private final TalonFX conveyanceMotor;
  
  /** Creates a new coveyanceSUB. */
  public ConveyanceSUB() {
    conveyanceMotor = new TalonFX(ConveyanceConstants.CONVEYANCE_MOTOR_ID, Constants.canivoreName);
  }

  public void setSpeed(double speedVolts) {
    conveyanceMotor.setVoltage(speedVolts);
  }

  public void ballsToThroat(double forwardSpeedVolts) {
    conveyanceMotor.setVoltage(forwardSpeedVolts);
  }

  public void ballsToIntake(double forwardSpeedVolts) {
    conveyanceMotor.setVoltage(forwardSpeedVolts * -1);
  }

  public void stop() {
    conveyanceMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
