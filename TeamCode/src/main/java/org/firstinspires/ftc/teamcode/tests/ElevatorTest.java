package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.Elevator;

@TeleOp(name = "ElevatorTest")
public class ElevatorTest extends LinearOpMode {
    private static Elevator elevator = new Elevator();

    @Override
    public void runOpMode() throws InterruptedException {
        // init
        elevator.init(hardwareMap);

        waitForStart();

        //loop
        while (!isStopRequested()) {
            elevator.test(gamepad1);
            elevator.tune(telemetry);
        }
    }
}
