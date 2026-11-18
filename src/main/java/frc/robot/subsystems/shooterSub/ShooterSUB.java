// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooterSub;
import static edu.wpi.first.units.Units.Volts;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants;

public class ShooterSUB extends SubsystemBase {
  private final DutyCycleOut dutyCycleControl = new DutyCycleOut(0);
  private final VelocityVoltage velocityControl = new VelocityVoltage(0);
  private final VoltageOut voltageControl = new VoltageOut(0);
  private final SysIdRoutine m_sysIdRoutine;
  
  private final TalonFX rightUpMotorMaster;
  private final TalonFX rightDownMotorSlave;
  private final TalonFX leftMotorSlave;

  public ShooterSUB() {
    rightUpMotorMaster = new TalonFX(ShooterConstants.RIGHT_UP_MASTER_MOTOR_PORT, new CANBus(Constants.canivoreName));
    rightDownMotorSlave = new TalonFX(ShooterConstants.RIGHT_DOWN_SLAVE_MOTOR_PORT, new CANBus(Constants.canivoreName));
    leftMotorSlave = new TalonFX(ShooterConstants.LEFT_UP_SLAVE_MOTOR_PORT, new CANBus(Constants.canivoreName));

    configMotors();

    leftMotorSlave.setControl(new Follower(ShooterConstants.RIGHT_UP_MASTER_MOTOR_PORT, MotorAlignmentValue.Opposed));
    rightDownMotorSlave.setControl(new Follower(ShooterConstants.RIGHT_UP_MASTER_MOTOR_PORT, MotorAlignmentValue.Aligned));

    m_sysIdRoutine = new SysIdRoutine(
      new SysIdRoutine.Config(
        null,
        Volts.of(6),
        null,
        (state) -> SignalLogger.writeString("SysIdShooter_State", state.toString())
      ), 
      new SysIdRoutine.Mechanism(
        (volts) -> rightUpMotorMaster.setControl(voltageControl.withOutput(volts.in(Volts))),
        null,
        this
      )
    );
  }

  public void configMotors() {
    TalonFXConfiguration talonFXConfig = new TalonFXConfiguration();

    talonFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    talonFXConfig.Slot0.kP = ShooterConstants.kShooterP.get();
    talonFXConfig.Slot0.kI = ShooterConstants.kShooterI.get();
    talonFXConfig.Slot0.kD = ShooterConstants.kShooterD.get();
    talonFXConfig.Slot0.kS = ShooterConstants.kShooterS.get();
    talonFXConfig.Slot0.kV = ShooterConstants.kShooterV.get();
    talonFXConfig.Slot0.kA = ShooterConstants.kShooterA.get();
    
    for (int i = 0; i < 5; i++) {
    StatusCode statusMaster = rightUpMotorMaster.getConfigurator().apply(talonFXConfig);
    StatusCode statusRightSlave = rightDownMotorSlave.getConfigurator().apply(talonFXConfig);
    StatusCode statusLeftSlave = leftMotorSlave.getConfigurator().apply(talonFXConfig);
      if (statusMaster.isOK() && statusLeftSlave.isOK() && statusRightSlave.isOK()) {
        return;
      }
    }
  }

  public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
    return m_sysIdRoutine.quasistatic(direction);
  }

  public Command sysIdDynamic(SysIdRoutine.Direction direction) {
    return m_sysIdRoutine.dynamic(direction);
  }

  public void setSpeed(double speed) {
    rightUpMotorMaster.setControl(dutyCycleControl.withOutput(speed));
  }

  public void setTargetRpm(double rpmRotations) {
    rightUpMotorMaster.setControl(velocityControl.withVelocity(rpmRotations / 60));
  }

  public double getCurrentRpm() {
    return rightUpMotorMaster.getVelocity().getValueAsDouble() * 60;
  }

  public boolean isAtTarget() {
    return Math.abs(ShooterConstants.targetRpm.get() - getCurrentRpm()) < ShooterConstants.kRpmTolerance;
  }

  public void resetRpm() {
    rightUpMotorMaster.setPosition(0);
  }

  public void stop() {
    rightUpMotorMaster.setControl(new DutyCycleOut(0));
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