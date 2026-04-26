package xol.lostinfinity.util.animation.client.blueprint;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.util.math.EulerAngle;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/blueprint/XLerp.class */
public class XLerp {
    public static float lerp(float a, float b, double aT, double bT) {
        return (float) ((aT * ((double) a)) + (bT * ((double) b)));
    }

    public static double lerp(double a, double b, double aT, double bT) {
        return (aT * a) + (bT * b);
    }

    public static Vec3d lerp(Vec3d start, Vec3d end, double ratio) {
        return new Vec3d(MathHelper.func_151238_b(start.field_72450_a, end.field_72450_a, ratio), MathHelper.func_151238_b(start.field_72448_b, end.field_72448_b, ratio), MathHelper.func_151238_b(start.field_72449_c, end.field_72449_c, ratio));
    }

    public static Vec3d lerp(Vec3d a, Vec3d b, double aT, double bT) {
        return new Vec3d(lerp(a.field_72450_a, b.field_72450_a, aT, bT), lerp(a.field_72448_b, b.field_72448_b, aT, bT), lerp(a.field_72449_c, b.field_72449_c, aT, bT));
    }

    public static Vec3d smoothLerp(Vec3d a, Vec3d b, Vec3d c, Vec3d d, double t) {
        double t2 = ((2.0d - 1.0d) * t) + 1.0d;
        Vec3d a1 = lerp(a, b, (1.0d - t2) / (1.0d - 0.0d), (t2 - 0.0d) / (1.0d - 0.0d));
        Vec3d a2 = lerp(b, c, (2.0d - t2) / (2.0d - 1.0d), (t2 - 1.0d) / (2.0d - 1.0d));
        Vec3d a3 = lerp(c, d, (3.0d - t2) / (3.0d - 2.0d), (t2 - 2.0d) / (3.0d - 2.0d));
        Vec3d b1 = lerp(a1, a2, (2.0d - t2) / (2.0d - 0.0d), (t2 - 0.0d) / (2.0d - 0.0d));
        Vec3d b2 = lerp(a2, a3, (3.0d - t2) / (3.0d - 1.0d), (t2 - 1.0d) / (3.0d - 1.0d));
        return lerp(b1, b2, (2.0d - t2) / (2.0d - 1.0d), (t2 - 1.0d) / (2.0d - 1.0d));
    }

    public static EulerAngle lerp(EulerAngle start, EulerAngle end, double ratio) {
        return new EulerAngle((float) MathHelper.func_151238_b(start.getX(), end.getX(), ratio), (float) MathHelper.func_151238_b(start.getY(), end.getY(), ratio), (float) MathHelper.func_151238_b(start.getZ(), end.getZ(), ratio));
    }

    public static EulerAngle lerp(EulerAngle a, EulerAngle b, double aT, double bT) {
        return new EulerAngle(lerp(a.getX(), b.getX(), aT, bT), lerp(a.getY(), b.getY(), aT, bT), lerp(a.getZ(), b.getZ(), aT, bT));
    }

    public static EulerAngle smoothLerp(EulerAngle a, EulerAngle b, EulerAngle c, EulerAngle d, double t) {
        double t2 = ((2.0d - 1.0d) * t) + 1.0d;
        EulerAngle a1 = lerp(a, b, (1.0d - t2) / (1.0d - 0.0d), (t2 - 0.0d) / (1.0d - 0.0d));
        EulerAngle a2 = lerp(b, c, (2.0d - t2) / (2.0d - 1.0d), (t2 - 1.0d) / (2.0d - 1.0d));
        EulerAngle a3 = lerp(c, d, (3.0d - t2) / (3.0d - 2.0d), (t2 - 2.0d) / (3.0d - 2.0d));
        EulerAngle b1 = lerp(a1, a2, (2.0d - t2) / (2.0d - 0.0d), (t2 - 0.0d) / (2.0d - 0.0d));
        EulerAngle b2 = lerp(a2, a3, (3.0d - t2) / (3.0d - 1.0d), (t2 - 1.0d) / (3.0d - 1.0d));
        return lerp(b1, b2, (2.0d - t2) / (2.0d - 1.0d), (t2 - 1.0d) / (2.0d - 1.0d));
    }
}
