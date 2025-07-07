package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ShoulderTest extends LinearOpMode {
    private Shoulder shoulder = new Shoulder();
    @Override
    public void runOpMode() throws InterruptedException {
        shoulder.init(hardwareMap);

        waitForStart();


        while (!isStopRequested()){
            shoulder.updateState(gamepad1);
            shoulder.operate();
            shoulder.tune(telemetry);
        }
    }
}
