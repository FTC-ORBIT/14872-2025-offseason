package org.firstinspires.ftc.teamcode.robotSubSystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.GlobalData;

public enum RobotState {
    TRAVEL(), INTAKE(), LOW_BASKET(), HIGH_CHAMBER();

    public static RobotState getRobotStateFromDriver(final Gamepad gamepad) {

        if (gamepad.b) {
            GlobalData.robotState = TRAVEL;
        }

        if (gamepad.a) {
            GlobalData.robotState =  INTAKE;
        }

        if (gamepad.x) {
            GlobalData.robotState =  HIGH_CHAMBER;
        }

        if (gamepad.y) {
            GlobalData.robotState =  LOW_BASKET;
        }


        return GlobalData.robotState;
    }
}
