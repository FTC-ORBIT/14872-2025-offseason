package org.firstinspires.ftc.teamcode.robotSubSystems.shoulder;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GlobalData;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.utils.MathFuncs;
import org.firstinspires.ftc.teamcode.utils.PID;

public class Shoulder {
    private static final PID shoulderPID = new PID(0.5,0,0,0.1,0);
    private DcMotor shoulderMotor;
    private Servo servo;

    private boolean lastRightBumper = false;
    private boolean lastLeftBumper = false;
    private float valServoTest = 0;
    private ShoulderStates state = ShoulderStates.TRAVEL ;
    public void init(final HardwareMap hardwareMap){
        shoulderMotor = hardwareMap.get(DcMotor.class,"shoulderMotor");
        servo = hardwareMap.get(Servo.class,"shoulderServo");
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

        float wantedAngle = state.equals(ShoulderStates.INTAKE) ? GlobalData.wantedIntakeAngle : state.wantedAngle;

        if (GlobalData.waitForTelecsop){
            wantedAngle = Robot.elevator.getLength()
                            > ShoulderConstants.minLengthToChangeAngleFromHighBasket
                    ? ShoulderStates.LOW_BASKET.wantedAngle : state.wantedAngle;
      }

        shoulderPID.setWanted(wantedAngle);
        shoulderMotor.setPower(MathFuncs.limit(state.equals(ShoulderStates.TRAVEL) ? 0.2f : 0.5F, (float) shoulderPID.update(getAngle())));
        servo.setPosition(state.wantedPos);
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

    public void testServo(Gamepad gamepad1, Telemetry telemetry){
        if (gamepad1.left_bumper && !lastLeftBumper){
            valServoTest += 0.03f;
        } else if (gamepad1.right_bumper && !lastRightBumper) {
            valServoTest -= 0.03f;
        }

        valServoTest = Math.abs(gamepad1.left_stick_y);

        servo.setPosition(valServoTest);

        telemetry.addData("pos",servo.getPosition());
        telemetry.update();
        lastLeftBumper = gamepad1.left_bumper;
        lastRightBumper = gamepad1.right_bumper;
    }
}
