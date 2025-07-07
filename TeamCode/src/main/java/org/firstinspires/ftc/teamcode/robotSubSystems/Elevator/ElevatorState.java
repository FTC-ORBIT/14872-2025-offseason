package org.firstinspires.ftc.teamcode.robotSubSystems.Elevator;

public enum ElevatorState {
    TRAVEL(0),
    INTAKE(0) /* TODO need to implement override */,
    BASKET(21),
    CHAMBER(16);

    public final int wantedLength;
    ElevatorState(final int wantedLength) {
        this.wantedLength = wantedLength;
    }

}
