package xol.lostinfinity.util.animation.client.keyframe;
import net.minecraft.util.math.Vec3d;
public class ScaleKeyframe extends AbstractKeyframe<Vec3d> {
    public ScaleKeyframe(Vec3d value) {
        super(value);
    }
    public ScaleKeyframe(Vec3d value, KeyframeType type) {
        super(value, type);
    }
}
