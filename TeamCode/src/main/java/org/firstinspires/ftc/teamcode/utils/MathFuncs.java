package org.firstinspires.ftc.teamcode.utils;

public class MathFuncs {
    public static float limit(final float bound, final float val){
        if (val > bound){
            return bound;
        }
        return Math.max(val, -bound);

    }
}
