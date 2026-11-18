package frc.robot.subsystems.coneveyanceSub;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyanceSUB extends SubsystemBase {
  private final TalonFX conveyanceMotor;
  
  public ConveyanceSUB() {
    conveyanceMotor = new TalonFX(ConveyanceConstants.CONVEYANCE_MOTOR_ID, new CANBus(Constants.canivoreName));
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
  }
}
