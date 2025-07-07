package org.firstinspires.ftc.teamcode.robotSubSystems.Elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GlobalData;
import org.firstinspires.ftc.teamcode.utils.MathFuncs;
import org.firstinspires.ftc.teamcode.utils.PID;

public class Elevator {
    private DcMotor elevatorMotor;
    private ElevatorState state = ElevatorState.TRAVEL;
    private final PID elevatorPID = new PID(0.2, 0,0,0,0);
    private int posA = 0;

    public void init(final HardwareMap hardwareMap) {
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevatorMotor");

        elevatorMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        elevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void update(final Telemetry telemetry) {
        telemetry.clear();
        posA = elevatorMotor.getCurrentPosition();

        telemetry.addData("elevatorPos", posA);
        telemetry.update();
    }

    public void operate() {
//        state = updateFromRobotState();
        final float wantedLength = MathFuncs.limit(ElevatorConstants.maxLength,state.wantedLength);
        elevatorPID.setWanted(wantedLength);
        final float power = MathFuncs.limit(0.3f, (float) elevatorPID.update(getLength()));
        elevatorMotor.setPower(power);
    }

    public float getLength(){
        return elevatorMotor.getCurrentPosition() / ElevatorConstants.tickPerCm;
    }
    private void updateFromJoystick(final Gamepad gamepad) {
       if (gamepad.a){
           state = ElevatorState.INTAKE;
       }else if (gamepad.b){
           state = ElevatorState.TRAVEL;
       } else if (gamepad.y) {
           state = ElevatorState.BASKET;
       }
    }

    private ElevatorState updateFromRobotState() {
       switch (GlobalData.robotState){
           case INTAKE:
               return ElevatorState.INTAKE;
           case LOW_BASKET:
               return ElevatorState.BASKET;
           case HIGH_CHAMBER:
               return ElevatorState.CHAMBER;
           case TRAVEL:
               return ElevatorState.TRAVEL;
       }

       return state;
    }

    public void test(final Gamepad gamepad){
        updateFromJoystick(gamepad);
        operate();
    }

    public void tune(Telemetry telemetry){
        telemetry.addData("pos",elevatorMotor.getCurrentPosition());
        telemetry.addData("length",getLength());
        telemetry.update();
    }
}
