package frc.robot.subsystems.Intake;

import frc.lib.util.TunableNumber;

public final class IntakeConstants {
    public static final int INTAKE_MOTOR_PORT = 0;
    public static final int CANCODER_ID = 0;

    public static final TunableNumber kIntakeP = new TunableNumber("Intake/Real/kP", 0.0);
    public static final TunableNumber kIntakeI = new TunableNumber("Intake/Real/kD", 0.0);
    public static final TunableNumber kIntakeD = new TunableNumber("Intake/Real/kI", 0.0);
    public static final TunableNumber kIntakeS = new TunableNumber("Intake/Real/kS", 0.0);
    public static final TunableNumber kIntakeA = new TunableNumber("Intake/Real/kA", 0.0);
    public static final TunableNumber kIntakeV = new TunableNumber("Intake/Real/kV", 0.0);

    public static final TunableNumber closeTargetPosition = new TunableNumber("Intake/closeTargetPosition", 0.0);
    public static final TunableNumber openTargetPosition = new TunableNumber("Intake/openTargetPosition", 0.0);

    public static final TunableNumber kAcceleration = new TunableNumber("Intake/acceleration", 0.0);
    public static final TunableNumber kCruiseVelocity = new TunableNumber("Intake/cruiseVelocity", 0.0);
    public static final TunableNumber kJerk = new TunableNumber("Intake/gearRatio", 0.0);
    public static final double kGearRatio = 0.0;

    public static final double kIntakeStallThreshold = 40.0;
    public static final double kTolerance = 3.0;


}
