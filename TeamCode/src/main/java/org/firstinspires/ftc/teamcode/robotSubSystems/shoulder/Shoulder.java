package org.firstinspires.ftc.teamcode.robotSubSystems.shoulder;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GlobalData;
import org.firstinspires.ftc.teamcode.robotSubSystems.endeffector.EndEffectorStates;
import org.firstinspires.ftc.teamcode.utils.PID;

public class Shoulder {
    private static final PID shoulderPID = new PID(0.5,0,0,0.1,0);
    private DcMotor shoulderMotor;
    private ShoulderStates state = ShoulderStates.TRAVEL ;
    public void init(final HardwareMap hardwareMap){
        shoulderMotor = hardwareMap.get(DcMotor.class,"shoulderMotor");
    }

    public  ShoulderStates updateStateFromRobot(){
        switch (GlobalData.robotState) {
            case TRAVEL:
                return ShoulderStates.TRAVEL;
            case HIGH_CHAMBER:
                return ShoulderStates.HIGH_CHAMBER;
            case LOW_BASKET:
                return ShoulderStates.LOW_BASKET;
            case INTAKE:
                return ShoulderStates.INTAKE;
        }

        return state;
    }

    public void operate(){
        state = updateStateFromRobot();
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
