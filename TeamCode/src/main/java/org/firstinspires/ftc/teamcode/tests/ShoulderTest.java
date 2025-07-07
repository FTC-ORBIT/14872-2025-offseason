package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.Shoulder;

@TeleOp
public class ShoulderTest extends LinearOpMode {
    private Shoulder shoulder = new Shoulder();
    @Override
    public void runOpMode() throws InterruptedException {
        shoulder.init(hardwareMap);

        waitForStart();


        while (!isStopRequested()){
            shoulder.updateStateFromRobot();
            shoulder.operate();
            shoulder.tune(telemetry);
        }
    }
}
