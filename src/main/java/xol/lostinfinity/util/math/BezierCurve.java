package xol.lostinfinity.util.math;

import net.minecraft.util.math.Vec3d;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/BezierCurve.class */
public class BezierCurve {
    public static Vec3d lerpNodes(Node start, Node end, double ratio) {
        Vec3d a1 = LMath.lerp(start.node, start.handleEnd, ratio);
        Vec3d a2 = LMath.lerp(start.handleEnd, end.handleStart, ratio);
        Vec3d a3 = LMath.lerp(end.handleStart, end.node, ratio);
        Vec3d b1 = LMath.lerp(a1, a2, ratio);
        Vec3d b2 = LMath.lerp(a2, a3, ratio);
        return LMath.lerp(b1, b2, ratio);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/BezierCurve$Node.class */
    public static class Node {
        public final Vec3d node;
        public final Vec3d handleStart;
        public final Vec3d handleEnd;

        public Node(Vec3d node, Vec3d handleStart, Vec3d handleEnd) {
            this.node = node;
            this.handleStart = handleStart;
            this.handleEnd = handleEnd;
        }
    }
}
