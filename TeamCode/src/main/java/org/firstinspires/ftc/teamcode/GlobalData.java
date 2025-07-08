package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.ElevatorConstants;
import org.firstinspires.ftc.teamcode.robotSubSystems.Elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.robotSubSystems.RobotState;
import org.firstinspires.ftc.teamcode.robotSubSystems.shoulder.ShoulderStates;

public class GlobalData {
    public static RobotState robotState = RobotState.TRAVEL;
    public static RobotState lastRobotState = RobotState.TRAVEL;
    public static boolean deplete = false;
    public static float wantedIntakeAngle = ShoulderStates.INTAKE.wantedAngle;
    public static float wantedIntakeLength = ElevatorState.INTAKE.wantedLength;
}
