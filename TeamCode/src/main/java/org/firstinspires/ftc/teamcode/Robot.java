package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.Elevator;
import org.firstinspires.ftc.teamcode.robotSubSystems.RobotState;
import org.firstinspires.ftc.teamcode.robotSubSystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.robotSubSystems.endeffector.EndEffector;
import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.Shoulder;
import org.firstinspires.ftc.teamcode.utils.Vector;

@TeleOp(name = "main")
public class Robot extends LinearOpMode {
    private final Shoulder shoulder = new Shoulder();
    private final Elevator elevator = new Elevator();
    private final EndEffector endEffector = new EndEffector();

    private boolean prevRightBumper = false;

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain.init(hardwareMap);
        shoulder.init(hardwareMap);
        elevator.init(hardwareMap);
        endEffector.init(hardwareMap);

        waitForStart();

        while (!isStopRequested()) {
           GlobalData.robotState = RobotState.getRobotStateFromDriver(gamepad1);

           if (!GlobalData.robotState.equals(GlobalData.lastRobotState)){
               GlobalData.deplete = false;
           }

           if (gamepad1.right_bumper && !prevRightBumper){
               GlobalData.deplete = !GlobalData.deplete;
           }

            final float omega = -gamepad1.right_trigger + gamepad1.left_trigger;
            final Vector joystick = new Vector(gamepad1.left_stick_x,-gamepad1.left_stick_y);
            Drivetrain.operate(joystick,omega);

            shoulder.operate();
            elevator.operate();
            endEffector.operate();

            prevRightBumper = gamepad1.right_bumper;
            GlobalData.lastRobotState = GlobalData.robotState;
        }
    }
}
