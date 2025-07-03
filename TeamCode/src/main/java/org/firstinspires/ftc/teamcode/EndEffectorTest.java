package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.endeffector.EndEffector;
import org.firstinspires.ftc.teamcode.endeffector.EndEffectorStates;

@TeleOp
public class EndEffectorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        final EndEffector endEffector = new EndEffector();

        endEffector.init(hardwareMap);

        waitForStart();
        while (!isStopRequested()) {
            final EndEffectorStates endEffectorState = -gamepad1.left_stick_y > 0.5 ? EndEffectorStates.COLLECT
                    : -gamepad1.left_stick_y < -0.5 ? EndEffectorStates.DEPLETE : EndEffectorStates.HOLD;

            endEffector.operate(endEffectorState);
        }
    }
}
