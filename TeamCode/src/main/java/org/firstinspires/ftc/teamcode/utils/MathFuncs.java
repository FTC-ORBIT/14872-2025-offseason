package org.firstinspires.ftc.teamcode.utils;

public class MathFuncs {
    public static float limit(final float bound, final float val){
        if (val > bound){
            return bound;
        }
        return Math.max(val, -bound);

    }

    public static boolean inTolerance(final float current,final float wanted,final float tolerance){
        return Math.abs(wanted) - Math.abs(current) <= tolerance;
    }


    public static float deadBand(final float val, final float xAtZero, final float xAtOne) {
        final float returnVal = (Math.abs(val) - xAtZero) / (xAtOne - xAtZero);
        return range(0, 1, returnVal) * Math.signum(val);
    }

    public static float range(final float lowerBound, final float upperBound, final float value) {
        return Math.min(Math.max(lowerBound, value), upperBound);
    }
}
