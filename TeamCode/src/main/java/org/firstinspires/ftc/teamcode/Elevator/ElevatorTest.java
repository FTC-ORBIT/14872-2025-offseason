package org.firstinspires.ftc.teamcode.Elevator;

import androidx.annotation.Nullable;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
        }
    }
}
