package org.firstinspires.ftc.teamcode.robotSubSystems.Elevator;

public enum ElevatorState {
    TRAVEL(3f),
    INTAKE(3f) /* TODO need to implement override */,
    BASKET(28),
    CHAMBER(21);

    public final float wantedLength;

    ElevatorState(final float wantedLength) {
        this.wantedLength = wantedLength;
    }

}
