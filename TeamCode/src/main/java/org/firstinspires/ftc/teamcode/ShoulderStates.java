package org.firstinspires.ftc.teamcode;

public enum ShoulderStates {
    TRAVEL(0),
    LOW_BASKET((float) Math.toRadians(115)),
    HIGH_CHAMBER((float) Math.toRadians(115)),
    INTAKE(0); // need to implement intake Override

    public final float wantedAngle;

    ShoulderStates(final float wantedAngle) {
        this.wantedAngle = wantedAngle;
    }
}
