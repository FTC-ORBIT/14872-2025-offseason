package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.Shoulder;

@TeleOp
public class ShoulderServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Shoulder shoulder = new Shoulder();

        shoulder.init(hardwareMap);

        waitForStart();
        while (!isStopRequested()) {
            shoulder.testServo(gamepad1, telemetry);
        }
    }
}
