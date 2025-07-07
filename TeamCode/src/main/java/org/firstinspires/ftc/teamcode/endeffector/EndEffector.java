package org.firstinspires.ftc.teamcode.endeffector;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class EndEffector {
    Servo intakeServo;

    public void init(final HardwareMap hardwareMap) {
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");
    }

    public void operate(final EndEffectorStates endEffectorState) {
        intakeServo.setPosition(endEffectorState.power);
    }


}