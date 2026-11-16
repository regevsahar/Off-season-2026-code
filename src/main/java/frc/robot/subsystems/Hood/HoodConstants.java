package frc.robot.subsystems.Hood;

import frc.lib.util.TunableNumber;

public class HoodConstants {
    public final static int HOOD_MOTOR_PORT = 2;
    public final static TunableNumber targetArc = new TunableNumber("targetArc", 0.0);
    public static final TunableNumber kHoodP = new TunableNumber("Shooter/Real/kP", 0.0);
    public static final TunableNumber kHoodI = new TunableNumber("Shooter/Real/kI", 0.0);
    public static final TunableNumber kHoodD = new TunableNumber("Shooter/Real/kD", 0.0);
    public static final TunableNumber kHoodS = new TunableNumber("Shooter/Real/kS", 0.0);
    public static final TunableNumber kHoodV = new TunableNumber("Shooter/Real/kV", 0.0);
    public static final TunableNumber kHoodA = new TunableNumber("Shooter/Real/kA", 0.0);
    public static final double kHoodStallThreshold = 40.0;

    public static final double kArcTolerance = 3;
}
