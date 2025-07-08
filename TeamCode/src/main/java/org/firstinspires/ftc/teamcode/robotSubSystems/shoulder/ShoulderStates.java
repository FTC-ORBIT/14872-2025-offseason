package org.firstinspires.ftc.teamcode.robotSubSystems.shoulder;

public enum ShoulderStates {
    TRAVEL((float) Math.toRadians(30), 1),
    LOW_BASKET((float) Math.toRadians(115), 1),
    HIGH_CHAMBER((float) Math.toRadians(115), 1),
    INTAKE((float) Math.toRadians(30), 0.5f); // need to implement intake Override

    public final float wantedAngle;
    public final float wantedPos;

    ShoulderStates(final float wantedAngle, float wantedPos) {
        this.wantedAngle = wantedAngle;
        this.wantedPos = wantedPos;
    }
}
