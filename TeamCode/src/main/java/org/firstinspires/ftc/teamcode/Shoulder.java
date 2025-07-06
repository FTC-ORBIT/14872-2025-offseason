package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shoulder {
    private static final PID shoulderPID = new PID(0.5,0,0,0.1,0);
    private DcMotor shoulderMotor;
    private ShoulderStates state = ShoulderStates.TRAVEL;
    public void init(final HardwareMap hardwareMap){
        shoulderMotor = hardwareMap.get(DcMotor.class,"shoulderMotor");
    }

    public void updateState(Gamepad gamepad){
        if (gamepad.b){
            state = ShoulderStates.TRAVEL;
        } else if (gamepad.a) {
            state = ShoulderStates.INTAKE;
        } else if (gamepad.x) {
            state = ShoulderStates.HIGH_CHAMBER;
        } else if (gamepad.y) {
            state = ShoulderStates.LOW_BASKET;
        }
    }

    public void operate(){
        shoulderPID.setWanted(state.wantedAngle);
        shoulderMotor.setPower(shoulderPID.update(getAngle()));
    }

    public float getAngle(){
        return shoulderMotor.getCurrentPosition() / ShoulderConstants.tickPerRad;
    }

    public void tune(Telemetry telemetry){
        telemetry.addData("pos",shoulderMotor.getCurrentPosition());
        telemetry.addData("angle",getAngle());
        telemetry.addData("power", shoulderMotor.getPower());
        telemetry.addData("state",state);
        telemetry.update();
    }
}
