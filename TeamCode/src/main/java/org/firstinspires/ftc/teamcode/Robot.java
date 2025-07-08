package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.Elevator;
import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.ElevatorConstants;
import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.robotSubSystems.RobotState;
import org.firstinspires.ftc.teamcode.robotSubSystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.robotSubSystems.drivetrain.Gyro;
import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.Shoulder;
import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.ShoulderConstants;
import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.ShoulderStates;
import org.firstinspires.ftc.teamcode.utils.MathFuncs;
import org.firstinspires.ftc.teamcode.utils.Vector;

@TeleOp(name = "main")
public class Robot extends LinearOpMode {
    public static final Shoulder shoulder = new Shoulder();
    public static final Elevator elevator = new Elevator();
//    public static final EndEffector endEffector = new EndEffector();

    public static boolean prevRightBumper = false;



    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain.init(hardwareMap);
        shoulder.init(hardwareMap);
        elevator.init(hardwareMap);
//        endEffector.init(hardwareMap);

        waitForStart();

        while (!isStopRequested()) {
            GlobalData.robotState = RobotState.getRobotStateFromDriver(gamepad1);

            if (!GlobalData.robotState.equals(GlobalData.lastRobotState)) {
                GlobalData.deplete = false;
                GlobalData.waitForTelecsop =
                        (GlobalData.lastRobotState.equals(RobotState.LOW_BASKET)
                                || GlobalData.lastRobotState.equals(RobotState.HIGH_CHAMBER))
                        && GlobalData.robotState.equals(RobotState.TRAVEL);
            }

            if (gamepad1.right_bumper && !prevRightBumper) {
                GlobalData.deplete = !GlobalData.deplete;
            }

            if (gamepad1.dpad_down) {
                Gyro.resetGyro();
            }

            final float omega = gamepad1.right_trigger - gamepad1.left_trigger;
            final Vector joystick = new Vector(gamepad1.left_stick_x, -gamepad1.left_stick_y);



            if (!GlobalData.robotState.equals(GlobalData.lastRobotState)){
                GlobalData.wantedIntakeLength = ElevatorState.INTAKE.wantedLength;
                GlobalData.wantedIntakeAngle = ShoulderStates.INTAKE.wantedAngle;
            }

            GlobalData.wantedIntakeLength += MathFuncs.deadBand(gamepad1.right_stick_x, 0.3f, 1f) * ElevatorConstants.overrideFactor;
            GlobalData.wantedIntakeAngle += MathFuncs.deadBand(-gamepad1.right_stick_y, 0.3f, 1f) * ShoulderConstants.overrideFactor;

            Drivetrain.operate(joystick, omega);
            shoulder.operate();
            elevator.operate();
//            endEffector.operate();

            prevRightBumper = gamepad1.right_bumper;
            GlobalData.lastRobotState = GlobalData.robotState;

            telemetry.addData("robot state", GlobalData.robotState);
            telemetry.addData("deplete", GlobalData.deplete);
            telemetry.addData("stick y",gamepad1.right_stick_y);
            telemetry.addData("stick x",gamepad1.right_stick_x);
            telemetry.addData("length",GlobalData.wantedIntakeLength);
            telemetry.addData("angle ",GlobalData.wantedIntakeAngle);
            telemetry.update();
        }
    }
}
