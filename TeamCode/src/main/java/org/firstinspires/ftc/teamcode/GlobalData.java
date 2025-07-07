package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.robotSubSystems.RobotState;

public class GlobalData {
    public static RobotState robotState = RobotState.TRAVEL;
    public static RobotState lastRobotState = RobotState.TRAVEL;
    public static boolean deplete = false;
}
