package frc.robot.subsystems;

import frc.lib.util.TunableNumber;

public final class ShooterConstants {
    public final static int MASTER_MOTOR_PORT = 17;
    public final static int SLAVE_MOTOR_PORT = 3;
    public final static TunableNumber targetRpm = new TunableNumber("targetArc", 0.0);
    public final static TunableNumber kShooterP = new TunableNumber("Shooter/Real/kP", 0.0);
    public final static TunableNumber kShooterI = new TunableNumber("Shooter/Real/kI", 0.0);
    public final static TunableNumber kShooterD = new TunableNumber("Shooter/Real/kD", 0.0);
    public final static TunableNumber kShooterS = new TunableNumber("Shooter/Real/kS", 0.0);
    public final static TunableNumber kShooterA = new TunableNumber("Shooter/Real/kV", 0.0);
    public final static TunableNumber kShooterV = new TunableNumber("Shooter/Real/kV", 0.0);

    public static final double kRpmTolerance = 300;
}
