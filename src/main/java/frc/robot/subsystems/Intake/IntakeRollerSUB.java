package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeRollerSUB extends SubsystemBase {
  private final TalonFX intakeRollerMotor;

  public IntakeRollerSUB() {
    intakeRollerMotor = new TalonFX(IntakeRollerConstants.INTAKE_ROLLER_MOTOR_PORT, new CANBus(Constants.canivoreName));
  }

  public void setSpeed(double speedVoltage) {
    intakeRollerMotor.setVoltage(speedVoltage);
  }

  public void intakeBall(double speedVoltage) {
    intakeRollerMotor.setVoltage(speedVoltage);
  }

  public void outtakeBall(double speedVoltage) {
    intakeRollerMotor.setVoltage(-speedVoltage);
  }
  
  public void stop() {
    intakeRollerMotor.setVoltage(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake/RollerSpeed", IntakeRollerConstants.INTAKE_ROLLER_SPEED.get());

  }
}
