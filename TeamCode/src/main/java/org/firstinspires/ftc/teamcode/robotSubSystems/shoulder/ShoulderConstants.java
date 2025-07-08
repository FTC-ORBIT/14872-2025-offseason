package org.firstinspires.ftc.teamcode.robotSubSystems.shoulder;

import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.ElevatorConstants;

public class ShoulderConstants {
    public static final float tickPerRad = (float) ((float) 819 / Math.toRadians(105)); // TODO tune!
    public static final float minLengthToChangeAngleFromHighBasket = 4f;
    public static final float armPivotHeight = 14.2f;
    public static final float overrideFactor = (float) Math.toRadians(2f);
}


