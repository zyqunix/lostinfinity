package xol.lostinfinity.util.animation.client.keyframe;
import net.minecraft.util.math.Vec3d;
public class PositionKeyframe extends AbstractKeyframe<Vec3d> {
    public PositionKeyframe(Vec3d value) {
        super(value);
    }
    public PositionKeyframe(Vec3d value, KeyframeType type) {
        super(value, type);
    }
}
