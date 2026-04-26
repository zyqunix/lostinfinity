package xol.lostinfinity.util.math;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.util.vector.Quaternion;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/LMath.class */
public class LMath {
    public static final float DEG2RAD = 0.017453292f;
    public static final float RAD2DEG = 57.29578f;
    public static final float PI = 3.1415927f;

    public static float fastSqrt(float value) {
        return (float) (1.0d / MathHelper.func_181161_i(value));
    }

    public static double fastSqrt(double value) {
        return 1.0d / MathHelper.func_181161_i(value);
    }

    public static Vec3d getCenter(AxisAlignedBB boundingBox) {
        return new Vec3d(boundingBox.field_72340_a + ((boundingBox.field_72336_d - boundingBox.field_72340_a) * 0.5d), boundingBox.field_72338_b + ((boundingBox.field_72337_e - boundingBox.field_72338_b) * 0.5d), boundingBox.field_72339_c + ((boundingBox.field_72334_f - boundingBox.field_72339_c) * 0.5d));
    }

    public static double getDistanceSquaredToAABB(Vec3d start, AxisAlignedBB boundingBox) {
        Vec3d delta = start.func_178788_d(getCenter(boundingBox));
        double sizeX = boundingBox.field_72336_d - boundingBox.field_72340_a;
        double sizeY = boundingBox.field_72337_e - boundingBox.field_72338_b;
        double sizeZ = boundingBox.field_72334_f - boundingBox.field_72339_c;
        Vec3d q = new Vec3d(Math.max(Math.abs(delta.field_72450_a) - (sizeX / 2.0d), 0.0d), Math.max(Math.abs(delta.field_72448_b) - (sizeY / 2.0d), 0.0d), Math.max(Math.abs(delta.field_72449_c) - (sizeZ / 2.0d), 0.0d));
        return q.func_189985_c();
    }

    public static boolean isAABBWithinDistance(Vec3d start, AxisAlignedBB boundingBox, double maxDistance) {
        return getDistanceSquaredToAABB(start, boundingBox) < maxDistance * maxDistance;
    }

    public static boolean isAABBWithinDistance(Vec3d start, Vec3d direction, AxisAlignedBB boundingBox, double maxDistance) {
        double tMin;
        double tMax;
        double tyMin;
        double tyMax;
        double tzMin;
        double tzMax;
        double startX = start.field_72450_a;
        double startY = start.field_72448_b;
        double startZ = start.field_72449_c;
        Vec3d dir = fastNormalize(direction);
        double dirX = dir.field_72450_a;
        double dirY = dir.field_72448_b;
        double dirZ = dir.field_72449_c;
        double divX = 1.0d / dirX;
        double divY = 1.0d / dirY;
        double divZ = 1.0d / dirZ;
        if (dirX >= 0.0d) {
            tMin = (boundingBox.field_72340_a - startX) * divX;
            tMax = (boundingBox.field_72336_d - startX) * divX;
        } else {
            tMin = (boundingBox.field_72336_d - startX) * divX;
            tMax = (boundingBox.field_72340_a - startX) * divX;
        }
        if (dirY >= 0.0d) {
            tyMin = (boundingBox.field_72338_b - startY) * divY;
            tyMax = (boundingBox.field_72337_e - startY) * divY;
        } else {
            tyMin = (boundingBox.field_72337_e - startY) * divY;
            tyMax = (boundingBox.field_72338_b - startY) * divY;
        }
        if (tMin > tyMax || tMax < tyMin) {
            return false;
        }
        if (tyMin > tMin) {
            tMin = tyMin;
        }
        if (tyMax < tMax) {
            tMax = tyMax;
        }
        if (dirZ >= 0.0d) {
            tzMin = (boundingBox.field_72339_c - startZ) * divZ;
            tzMax = (boundingBox.field_72334_f - startZ) * divZ;
        } else {
            tzMin = (boundingBox.field_72334_f - startZ) * divZ;
            tzMax = (boundingBox.field_72339_c - startZ) * divZ;
        }
        if (tMin > tzMax || tMax < tzMin) {
            return false;
        }
        if (tzMin > tMin) {
            tMin = tzMin;
        }
        if (tzMax < tMax) {
            tMax = tzMax;
        }
        return tMax >= 0.0d && tMin <= maxDistance;
    }

    public static float degreeDifference(float a, float b) {
        return MathHelper.func_76142_g(b - a);
    }

    public static Quaternion fromRotations(Rotations rotations) {
        return fromEulerRadian(rotations.func_179415_b() * 0.017453292f, rotations.func_179416_c() * 0.017453292f, rotations.func_179413_d() * 0.017453292f);
    }

    public static Quaternion fromEulerDegree(float x, float y, float z) {
        return fromEulerRadian(x * 0.017453292f, y * 0.017453292f, z * 0.017453292f);
    }

    public static Quaternion fromEulerRadian(float rX, float rY, float rZ) {
        float sX = MathHelper.func_76126_a(0.5f * (-rX));
        float cX = MathHelper.func_76134_b(0.5f * (-rX));
        float sY = MathHelper.func_76126_a(0.5f * rY);
        float cY = MathHelper.func_76134_b(0.5f * rY);
        float sZ = MathHelper.func_76126_a(0.5f * rZ);
        float cZ = MathHelper.func_76134_b(0.5f * rZ);
        return new Quaternion((sX * cY * cZ) + (cX * sY * sZ), ((cX * sY) * cZ) - ((sX * cY) * sZ), (sX * sY * cZ) + (cX * cY * sZ), ((cX * cY) * cZ) - ((sX * sY) * sZ));
    }

    public static Rotations toEulerAngle(Quaternion quaternion) {
        double x;
        double z;
        double x2 = quaternion.x + quaternion.x;
        double y2 = quaternion.y + quaternion.y;
        double z2 = quaternion.z + quaternion.z;
        double xx = ((double) quaternion.x) * x2;
        double xy = ((double) quaternion.x) * y2;
        double xz = ((double) quaternion.x) * z2;
        double yy = ((double) quaternion.y) * y2;
        double yz = ((double) quaternion.y) * z2;
        double zz = ((double) quaternion.z) * z2;
        double wx = ((double) quaternion.w) * x2;
        double wy = ((double) quaternion.w) * y2;
        double wz = ((double) quaternion.w) * z2;
        double m11 = 1.0d - (yy + zz);
        double m12 = xy + wz;
        double m21 = xy - wz;
        double m22 = 1.0d - (xx + zz);
        double m31 = xz + wy;
        double m32 = yz - wx;
        double m33 = 1.0d - (xx + yy);
        double y = Math.asin(-MathHelper.func_151237_a(m31, -1.0d, 1.0d));
        if (Math.abs(m31) < 0.9999999d) {
            x = MathHelper.func_181159_b(m32, m33);
            z = MathHelper.func_181159_b(m21, m11);
        } else {
            x = 0.0d;
            z = MathHelper.func_181159_b(-m12, m22);
        }
        return new Rotations(((float) x) * 57.29578f, ((float) y) * 57.29578f, ((float) z) * 57.29578f);
    }

    public static Quaternion globalRotate(Quaternion origin, Quaternion delta) {
        return Quaternion.mul(origin, delta, (Quaternion) null);
    }

    public static Quaternion localRotate(Quaternion origin, Quaternion delta) {
        return Quaternion.mul(delta, origin, (Quaternion) null);
    }

    public static Rotations toPitchYaw(Vec3d dir) {
        double hMagnitude = fastSqrt((dir.field_72450_a * dir.field_72450_a) + (dir.field_72449_c * dir.field_72449_c));
        float pitch = ((float) MathHelper.func_181159_b(-dir.field_72448_b, hMagnitude)) * 57.29578f;
        float yaw = ((float) MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c)) * 57.29578f;
        return new Rotations(pitch, yaw, 0.0f);
    }

    public static Vec3d toLookVec(float pitch, float yaw) {
        float f = MathHelper.func_76134_b(((-yaw) * 0.017453292f) - 3.1415927f);
        float f1 = MathHelper.func_76126_a(((-yaw) * 0.017453292f) - 3.1415927f);
        float f2 = -MathHelper.func_76134_b((-pitch) * 0.017453292f);
        float f3 = MathHelper.func_76126_a((-pitch) * 0.017453292f);
        return new Vec3d(f1 * f2, f3, f * f2);
    }

    public static byte rotToByte(float rot) {
        return (byte) (rot * 0.7111111f);
    }

    public static float byteToRot(byte rot) {
        return rot * 1.40625f;
    }

    public static float degreeLerp(float start, float end, double ratio) {
        return (float) (((double) start) + (((double) degreeDifference(start, end)) * ratio));
    }

    public static Vec3d lerp(Vec3d start, Vec3d end, double ratio) {
        return new Vec3d(MathHelper.func_151238_b(start.field_72450_a, end.field_72450_a, ratio), MathHelper.func_151238_b(start.field_72448_b, end.field_72448_b, ratio), MathHelper.func_151238_b(start.field_72449_c, end.field_72449_c, ratio));
    }

    public static Rotations lerp(Rotations a, Rotations b, double t) {
        return new Rotations(degreeLerp(a.func_179415_b(), b.func_179415_b(), t), degreeLerp(a.func_179416_c(), b.func_179416_c(), t), degreeLerp(a.func_179413_d(), b.func_179413_d(), t));
    }

    public static float lerp(float a, float b, double aT, double bT) {
        return (float) ((aT * ((double) a)) + (bT * ((double) b)));
    }

    public static Quaternion lerp(Quaternion a, Quaternion b, double aT, double bT) {
        return new Quaternion(lerp(a.x, b.x, aT, bT), lerp(a.y, b.y, aT, bT), lerp(a.z, b.z, aT, bT), lerp(a.w, b.w, aT, bT)).normalise((Quaternion) null);
    }

    public static Quaternion onlerp(Quaternion a, Quaternion b, double t) {
        float ca = Quaternion.dot(a, b);
        double t2 = aprox(ca, (float) t);
        return lerp(a, b, 1.0d - t2, ca > 0.0f ? t2 : -t2);
    }

    public static Rotations slerp(Rotations a, Rotations b, double t) {
        return toEulerAngle(onlerp(fromRotations(a), fromRotations(b), t));
    }

    public static String toString(Rotations angle) {
        return String.format("[%s, %s, %s]", Float.valueOf(angle.func_179415_b()), Float.valueOf(angle.func_179416_c()), Float.valueOf(angle.func_179413_d()));
    }

    public static double fastLength(Vec3d vector) {
        double lSqr = vector.func_189985_c();
        return lSqr * MathHelper.func_181161_i(lSqr);
    }

    public static double fastLength(double x, double y, double z) {
        double lSqr = (x * x) + (y * y) + (z * z);
        return lSqr * MathHelper.func_181161_i(lSqr);
    }

    public static Vec3d fastNormalize(Vec3d vector) {
        return vector.func_186678_a(MathHelper.func_181161_i(vector.func_189985_c()));
    }

    public static Vec3d getEntityMiddle(Entity entity) {
        return new Vec3d(entity.field_70165_t, entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)), entity.field_70161_v);
    }

    public static double trig01(double value) {
        return (value + 1.0d) * 0.5d;
    }

    private static float aprox(float ca, float r) {
        float diff = Math.abs(ca);
        float a = 1.0904f + (diff * ((-3.2452f) + (diff * (3.55645f - (diff * 1.43519f)))));
        float b = 0.848013f + (diff * ((-1.06021f) + (diff * 0.215638f)));
        float k = (a * (r - 0.5f) * (r - 0.5f)) + b;
        return r + (r * (r - 0.5f) * (r - 1.0f) * k);
    }
}
