package org.firstinspires.ftc.teamcode.Elevator;

public enum ElevatorState {
    TRAVEL(0),
    INTAKE(0),
    BASKET(0),
    MANUAL(0),
    CHAMBER(0);

    public int wantedPosition;

    private ElevatorState(final int wantedPosition) {
        this.wantedPosition = wantedPosition;
    }

}
