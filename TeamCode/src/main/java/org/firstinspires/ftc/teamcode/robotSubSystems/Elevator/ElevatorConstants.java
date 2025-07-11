package org.firstinspires.ftc.teamcode.robotSubSystems.Elevator;

public class ElevatorConstants {
    public static final float tickPerCm = 238 / 3.5f; // TODO tune!
    public static final float maxLength = 33;
    public static final float closeLength = 0.5f;
    public static final float maxPower = 0.3f;
    public static final float tolerance = 0.5f; // cm
    public static final float overrideFactor = 1f;

    public static final float minAngleToOpenElevator = (float) Math.toRadians(15);
}
