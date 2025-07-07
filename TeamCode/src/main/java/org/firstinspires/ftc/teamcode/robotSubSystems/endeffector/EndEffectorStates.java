package org.firstinspires.ftc.teamcode.robotSubSystems.endeffector;

public enum EndEffectorStates {
    COLLECT(1),
    HOLD(0.5f),
    DEPLETE(0);

    public final float power;
     EndEffectorStates(final float power){
        this.power = power;
    }
}
