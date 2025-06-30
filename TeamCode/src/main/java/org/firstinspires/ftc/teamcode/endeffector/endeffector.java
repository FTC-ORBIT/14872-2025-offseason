public class EndEffector{
    Servo pitchServo;
    Servo intakeServo;
    public void init(){
        //TODO
        pitchServo = new SimpleServo(
                hardwareMap, "end_effector_pitch_servo", MIN_ANGLE, MAX_ANGLE
        );
        intakeServo = new SimpleServo(
                hardwareMap, "intake_servo", MIN_ANGLE, MAX_ANGLE
        );
    }
    public void operate(SystemModes currentMode){
        switch (currentMode){
            case COLLECT:
                // lower pitch, rotate intake inwards
                pitchServo =
                break;
            case HOLD:
                break;
            case PLACE:
                // lower pitch by a lot, rotate intake out
                break;
        }
    }
    public enum SystemModes{
        COLLECT,
        HOLD,
        PLACE,

    }

}