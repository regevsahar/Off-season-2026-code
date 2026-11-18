package frc.robot.subsystems.shooterSub;

import frc.lib.util.TunableNumber;

public final class ShooterConstants {
    public final static int RIGHT_UP_MASTER_MOTOR_PORT = 17;
    public final static int RIGHT_DOWN_SLAVE_MOTOR_PORT = 16;
    public final static int LEFT_UP_SLAVE_MOTOR_PORT = 3;
    public final static TunableNumber targetRpm = new TunableNumber("Shooter/targetRpm", 0.0);
    public final static TunableNumber kShooterP = new TunableNumber("Shooter/Real/kP", 0.1);
    public final static TunableNumber kShooterI = new TunableNumber("Shooter/Real/kI", 0.0);
    public final static TunableNumber kShooterD = new TunableNumber("Shooter/Real/kD", 0.1);
    public final static TunableNumber kShooterS = new TunableNumber("Shooter/Real/kS", 0.25997);
    public final static TunableNumber kShooterV = new TunableNumber("Shooter/Real/kV", 0.12497);
    public final static TunableNumber kShooterA = new TunableNumber("Shooter/Real/kA", 0.0073799);

    public static final double kRpmTolerance = 200;
}
