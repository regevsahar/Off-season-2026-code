// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooterSub;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSUB extends SubsystemBase {
  private final DutyCycleOut dutyCycleControl = new DutyCycleOut(0);
  private final VelocityVoltage velocityControl = new VelocityVoltage(0);
  
  private final TalonFX rightMotorMaster;
  private final TalonFX leftMotorSlave;

  public ShooterSUB() {
    rightMotorMaster = new TalonFX(ShooterConstants.MASTER_MOTOR_PORT, new CANBus(Constants.canivoreName));
    leftMotorSlave = new TalonFX(ShooterConstants.SLAVE_MOTOR_PORT, new CANBus(Constants.canivoreName));

    configMotors();

    leftMotorSlave.setControl(new Follower(ShooterConstants.MASTER_MOTOR_PORT, MotorAlignmentValue.Opposed));
  }

  public void configMotors() {
    TalonFXConfiguration talonFXConfig = new TalonFXConfiguration();

    rightMotorMaster.setNeutralMode(NeutralModeValue.Coast);
    leftMotorSlave.setNeutralMode(NeutralModeValue.Coast);

    talonFXConfig.Slot0.kP = ShooterConstants.kShooterP.get();
    talonFXConfig.Slot0.kI = ShooterConstants.kShooterI.get();
    talonFXConfig.Slot0.kD = ShooterConstants.kShooterD.get();
    talonFXConfig.Slot0.kS = ShooterConstants.kShooterS.get();
    talonFXConfig.Slot0.kA = ShooterConstants.kShooterA.get();
    talonFXConfig.Slot0.kV = ShooterConstants.kShooterV.get();

    StatusCode statusMaster = rightMotorMaster.getConfigurator().apply(talonFXConfig);
    StatusCode statusSlave = leftMotorSlave.getConfigurator().apply(talonFXConfig);
    for (int i = 0; i < 5; i++) {
      if (statusMaster.isOK() && statusSlave.isOK()) {
        return;
      }
    }
  }

  public void setSpeed(double speed) {
    rightMotorMaster.setControl(dutyCycleControl.withOutput(speed));
  }

  public void setTargetRpm(double rpmRotations) {
    rightMotorMaster.setControl(velocityControl.withVelocity(rpmRotations / 60));
  }

  public double getCurrentRpm() {
    return rightMotorMaster.getVelocity().getValueAsDouble() * 60;
  }

  public boolean isAtTarget() {
    return Math.abs(ShooterConstants.targetRpm.get() - getCurrentRpm()) < ShooterConstants.kRpmTolerance;
  }

  public void resetRpm() {
    rightMotorMaster.setPosition(0);
  }

  public void stop() {
    rightMotorMaster.setControl(new DutyCycleOut(0));
  }

  @Override
  public void periodic() {
    if (ShooterConstants.kShooterP.hasChanged()
        || ShooterConstants.kShooterI.hasChanged()
        || ShooterConstants.kShooterD.hasChanged()
        || ShooterConstants.kShooterS.hasChanged()
        || ShooterConstants.kShooterV.hasChanged()
        || ShooterConstants.kShooterA.hasChanged()) {
      configMotors();
    }

    SmartDashboard.putNumber("Shooter/RPM", getCurrentRpm());
    SmartDashboard.putBoolean("Shooter/isTarget", isAtTarget());

  }
}
