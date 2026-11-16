// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Hood;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HoodSUB extends SubsystemBase {
  private final DutyCycleOut dutyCycleControl = new DutyCycleOut(0);
  private final PositionVoltage positionControl = new PositionVoltage(0);

  private final TalonFX hoodMotor;
  

  public HoodSUB() {
    hoodMotor = new TalonFX(HoodConstants.HOOD_MOTOR_PORT, new CANBus(Constants.canivoreName));
    configMotor();
    resetPosition();
  }

  public void configMotor(){
    TalonFXConfiguration talonFXConfig = new TalonFXConfiguration();
    
    hoodMotor.setNeutralMode(NeutralModeValue.Brake);
    talonFXConfig.Slot0.kP = HoodConstants.kHoodP.get();
    talonFXConfig.Slot0.kI = HoodConstants.kHoodI.get();
    talonFXConfig.Slot0.kD = HoodConstants.kHoodD.get();
    talonFXConfig.Slot0.kS = HoodConstants.kHoodS.get();
    talonFXConfig.Slot0.kA = HoodConstants.kHoodA.get();
    talonFXConfig.Slot0.kV = HoodConstants.kHoodV.get();

    StatusCode status = hoodMotor.getConfigurator().apply(talonFXConfig);
    for (int i = 0; i < 5; i++){
      if (status.isOK()) {
        return;
      }
    }
  }

  public void setSpeed(double speed) {
    hoodMotor.setControl(dutyCycleControl.withOutput(speed));
  }

  public void setTargetArc(double arcDegrees) {
    hoodMotor.setControl(positionControl.withPosition(arcToRotations(arcDegrees)));
  }

  public double getCurrentPosition() {
    return hoodMotor.getPosition().getValueAsDouble();
  }

  public boolean isAtTarget() {
    return Math.abs(HoodConstants.targetArc.get() - rotationsToArc(getCurrentPosition())) < HoodConstants.kArcTolerance;
  }

  public void resetPosition() {
    hoodMotor.setPosition(0);
  }

  public boolean isStalling() {
    return hoodMotor.getStatorCurrent().getValueAsDouble() > HoodConstants.kHoodStallThreshold;
  }

  public void stop() {
    hoodMotor.setControl(new DutyCycleOut(0));
  }


  public double arcToRotations(double arc) {
    return (arc - 30) / 43.33;
  }

  public double rotationsToArc(double rotations) {
    return 43.33 * rotations + 30;
  }


  @Override
  public void periodic() {
    if (HoodConstants.kHoodP.hasChanged()
        || HoodConstants.kHoodI.hasChanged()
        || HoodConstants.kHoodD.hasChanged()
        || HoodConstants.kHoodS.hasChanged()
        || HoodConstants.kHoodV.hasChanged()
        || HoodConstants.kHoodA.hasChanged()) {
      configMotor();
    }

    SmartDashboard.putNumber("Hood/Rotation", getCurrentPosition());
    SmartDashboard.putNumber("Hood/Arc", rotationsToArc(getCurrentPosition()));
    SmartDashboard.putBoolean("Hood/isTarget", isAtTarget());

  }
}
