package org.firstinspires.ftc.teamcode.robotSubSystems.endeffector;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.GlobalData;

public class EndEffector {
    Servo intakeServo;
    private EndEffectorStates state = EndEffectorStates.HOLD;

    public void init(final HardwareMap hardwareMap) {
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");
    }

    public void operate() {
        state = getStateFromRobotState();
        intakeServo.setPosition(state.power);
    }

    public EndEffectorStates getStateFromRobotState(){
        if (GlobalData.deplete){
            return EndEffectorStates.DEPLETE;
        }

        switch (GlobalData.robotState){
            case TRAVEL:
            case LOW_BASKET:
            case HIGH_CHAMBER:
                return EndEffectorStates.HOLD;
            case INTAKE:
                return EndEffectorStates.COLLECT;

        }

        return state;
    }


}