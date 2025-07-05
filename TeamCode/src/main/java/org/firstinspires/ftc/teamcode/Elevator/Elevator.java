package org.firstinspires.ftc.teamcode.Elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elevator {
    private DcMotor elevatorMotor;
    private ElevatorState state = ElevatorState.TRAVEL;
    private PID elevatorPID = new PID(0.005, 0,0,0,0);
    private int posA = 0;

    public void init(final HardwareMap hardwareMap) {
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevatorMotor");

        elevatorMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
        elevatorPID.setWanted(state.wantedLength);
        elevatorMotor.setPower(elevatorPID.update(getLength()));
    }

    public float getLength(){
        return elevatorMotor.getCurrentPosition() / ElevatorConstants.tickPerCm;
    }
    private void updateFromJoystick(final Gamepad gamepad) {
        if(gamepad.a) {
            state = ElevatorState.INTAKE;
        } else if (gamepad.b) {
            state = ElevatorState.TRAVEL;
        } else if (gamepad.y) {
            state = ElevatorState.BASKET;
        } else if (gamepad.x) {
            state = ElevatorState.CHAMBER;
        }
    }

    public void test(final Gamepad gamepad){
        updateFromJoystick(gamepad);
        operate();
    }
}
