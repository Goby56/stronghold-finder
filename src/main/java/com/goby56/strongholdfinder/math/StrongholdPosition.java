package com.goby56.strongholdfinder.math;

public class StrongholdPosition {
    public static int[] triangulatePosition(double[] pos1, double[] pos2) {
        double x1 = pos1[0];
        double z1 = pos1[1];
        double yaw1 = pos1[2];
        double x2 = pos2[0];
        double z2 = pos2[1];
        double yaw2 = pos2[2];

        double angle1 = correct_angle(yaw1);
        double angle2 = correct_angle(yaw2);

        double k1 = Math.sin(angle1)/Math.cos(angle1);
        double k2 = Math.sin(angle2)/Math.cos(angle2);
        double m1 = z1 - k1*x1;
        double m2 = z2 - k2*x2;

        double x = (m2-m1)/(k1-k2);
        double z = k1*x + m1;

        return new int[] {(int)x, (int)z};
    }

    public static double correct_angle(double angle) {
        return Math.toRadians(angle + 90);
    }

}
