// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.ObjectInputFilter.Config;
import java.lang.module.Configuration;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSUB extends SubsystemBase {
  private TalonFX masterMotor;
  private TalonFX slaveMotor;
  

  public ShooterSUB() {
    masterMotor = new TalonFX(ShooterConstants.MASTER_MOTOR_PORT, new CANBus(Constants.canivoreName));
    slaveMotor = new TalonFX(ShooterConstants.SLAVE_MOTOR_PORT, new CANBus(Constants.canivoreName));

    configMotors();

    slaveMotor.setControl(
      new Follower(ShooterConstants.MASTER_MOTOR_PORT, MotorAlignmentValue.Opposed)
    );
  }

  public void configMotors() {
    TalonFXConfiguration talonFXConfig = new TalonFXConfiguration();

    masterMotor.setNeutralMode(NeutralModeValue.Coast);
    slaveMotor.setNeutralMode(NeutralModeValue.Coast);

    talonFXConfig.Slot0.kP = ShooterConstants.kShooterP.get();
    talonFXConfig.Slot0.kI = ShooterConstants.kShooterI.get();
    talonFXConfig.Slot0.kD = ShooterConstants.kShooterD.get();
    talonFXConfig.Slot0.kS = ShooterConstants.kShooterS.get();
    talonFXConfig.Slot0.kA = ShooterConstants.kShooterA.get();
    talonFXConfig.Slot0.kV = ShooterConstants.kShooterV.get();

    StatusCode statusMaster = masterMotor.getConfigurator().apply(talonFXConfig);
    StatusCode statusSlave = slaveMotor.getConfigurator().apply(talonFXConfig);
    for (int i = 0; i < 5; i++) {
      if (statusMaster.isOK() && statusSlave.isOK()) {
        return;
      }
    }
  }

  



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
