package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSUB extends SubsystemBase {

  private TalonFX intakeMotor;
  private CANcoder cancoder;

  private DutyCycleOut dutyCycleControl = new DutyCycleOut(0);
  private PositionVoltage positionControl = new PositionVoltage(0);

  public IntakeSUB() {
    intakeMotor = 
      new TalonFX(IntakeConstants.INTAKE_MOTOR_PORT, new CANBus(Constants.CanivoreName)
      );
    cancoder = 
      new CANcoder(IntakeConstants.CANCODER_ID, new CANBus(Constants.CanivoreName)
      );
    
    configureTalonFX();
    
  }

  private void configureTalonFX() {
    TalonFXConfiguration talonFXConfig = new TalonFXConfiguration();
    MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs();

    intakeMotor.setNeutralMode(NeutralModeValue.Brake);

    talonFXConfig.Slot0.kP = IntakeConstants.kIntakeP.get();
    talonFXConfig.Slot0.kI = IntakeConstants.kIntakeI.get();
    talonFXConfig.Slot0.kD = IntakeConstants.kIntakeD.get();
    talonFXConfig.Slot0.kS = IntakeConstants.kIntakeS.get();
    talonFXConfig.Slot0.kV = IntakeConstants.kIntakeA.get();
    talonFXConfig.Slot0.kA = IntakeConstants.kIntakeV.get();
    
    motionMagicConfigs.MotionMagicAcceleration = IntakeConstants.kAcceleration.get();
    motionMagicConfigs.MotionMagicCruiseVelocity = IntakeConstants.kCruiseVelocity.get();
    motionMagicConfigs.MotionMagicJerk = IntakeConstants.kJerk.get();

    talonFXConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
    talonFXConfig.Feedback.FeedbackRemoteSensorID = IntakeConstants.CANCODER_ID;
    talonFXConfig.Feedback.RotorToSensorRatio = IntakeConstants.kGearRatio; 
    talonFXConfig.Feedback.SensorToMechanismRatio = 1.0; 

    talonFXConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    StatusCode status = intakeMotor.getConfigurator().apply(talonFXConfig);
    if (status.isOK()) {
      System.out.println("Failed to configure TalonFX: " + status);
    }
  }

  public void SetMotor(double value) {
    intakeMotor.setControl(dutyCycleControl.withOutput(value));
  }

  public void setTargetPosition(double target) {
    intakeMotor.setControl(positionControl.withPosition(target));
  }

  public double getCurrentPosition() {
    return intakeMotor.getPosition().getValueAsDouble();
  }

  public boolean isAtTargetPosition(double targetPosition) {
    double error = Math.abs(targetPosition - getCurrentPosition());
    return error < IntakeConstants.kTolerance;
  }

  public boolean isStalling() {
    return intakeMotor.getStatorCurrent().getValueAsDouble() > IntakeConstants.kIntakeStallThreshold;
  }

  public void stop() {
    intakeMotor.stopMotor();
  }


  @Override
  public void periodic() {
      if (IntakeConstants.kIntakeP.hasChanged() 
        || IntakeConstants.kIntakeI.hasChanged() 
        || IntakeConstants.kIntakeD.hasChanged() 
        || IntakeConstants.kIntakeS.hasChanged() 
        || IntakeConstants.kIntakeA.hasChanged() 
        || IntakeConstants.kIntakeV.hasChanged() 
        || IntakeConstants.kAcceleration.hasChanged() 
        || IntakeConstants.kCruiseVelocity.hasChanged()
        || IntakeConstants.kJerk.hasChanged()) {
        configureTalonFX();
      }

      SmartDashboard.putNumber("/Intake/Real/kP", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/Real/kI", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/Real/kD", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/Real/kS", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/Real/kA", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/Real/kV", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/acceleration", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/cruiseVelocity", getCurrentPosition());
      SmartDashboard.putNumber("/Intake/gearRatio", getCurrentPosition());
      
      SmartDashboard.putNumber("/Intake/targetPosition", getCurrentPosition());
      SmartDashboard.putBoolean("/Intake/isAtTargetOpen", isAtTargetPosition(IntakeConstants.openTargetPosition.get()));
      SmartDashboard.putBoolean("/Intake/isAtTargetClose", isAtTargetPosition(IntakeConstants.closeTargetPosition.get()));
  }
}
