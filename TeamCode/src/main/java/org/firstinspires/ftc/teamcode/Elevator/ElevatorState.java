package org.firstinspires.ftc.teamcode.Elevator;

public enum ElevatorState {
    TRAVEL(0),
    INTAKE(0),
    BASKET(0),
    MANUAL(0),
    CHAMBER(0);

    public int wantedLength;

    private ElevatorState(final int wantedLength) {
        this.wantedLength = wantedLength;
    }

}
