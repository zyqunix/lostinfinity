package xol.lostinfinity.util.animation.client.blueprint;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.util.animation.client.keyframe.AbstractKeyframe;
import xol.lostinfinity.util.animation.client.keyframe.KeyframeType;
import xol.lostinfinity.util.animation.client.keyframe.PositionKeyframe;
import xol.lostinfinity.util.animation.client.keyframe.RotationKeyframe;
import xol.lostinfinity.util.animation.client.keyframe.ScaleKeyframe;
import xol.lostinfinity.util.math.ComplexInterpolator;
import xol.lostinfinity.util.math.EulerAngle;
public class Timeline {
    public final ComplexInterpolator<PositionKeyframe, Vec3d> position = new ComplexInterpolator().setParseFunc((v0) -> {
        return v0.getValue();
    });
    public final ComplexInterpolator<RotationKeyframe, EulerAngle> rotation = new ComplexInterpolator().setParseFunc((v0) -> {
        return v0.getValue();
    });
    public final ComplexInterpolator<ScaleKeyframe, Vec3d> scale = new ComplexInterpolator().setParseFunc((v0) -> {
        return v0.getValue();
    });
    public Timeline() {
        this.position.setInterpolateFunc((ctx, prev, next, ratio) -> {
            switch (getType(prev, next)) {
                case LINEAR:
                    return XLerp.lerp(prev.getValue(), next.getValue(), ratio);
                case SMOOTH:
                    PositionKeyframe pPrev = this.position.get(Float.valueOf(this.position.getLowerKey(ctx.prevKey)));
                    PositionKeyframe nNext = this.position.get(Float.valueOf(this.position.getHigherKey(ctx.nextKey)));
                    return XLerp.smoothLerp(pPrev.getValue(), prev.getValue(), next.getValue(), nNext.getValue(), ratio);
                case STEP:
                    return prev.getValue();
                default:
                    return Vec3d.field_186680_a;
            }
        });
        this.rotation.setInterpolateFunc((ctx2, prev2, next2, ratio2) -> {
            switch (getType(prev2, next2)) {
                case LINEAR:
                    return XLerp.lerp(prev2.getValue(), next2.getValue(), ratio2);
                case SMOOTH:
                    RotationKeyframe pPrev = this.rotation.get(Float.valueOf(this.rotation.getLowerKey(ctx2.prevKey)));
                    RotationKeyframe nNext = this.rotation.get(Float.valueOf(this.rotation.getHigherKey(ctx2.nextKey)));
                    return XLerp.smoothLerp(pPrev.getValue(), prev2.getValue(), next2.getValue(), nNext.getValue(), ratio2);
                case STEP:
                    return prev2.getValue();
                default:
                    return new EulerAngle(0.0f, 0.0f, 0.0f);
            }
        });
        this.scale.setInterpolateFunc((ctx3, prev3, next3, ratio3) -> {
            switch (getType(prev3, next3)) {
                case LINEAR:
                    return XLerp.lerp(prev3.getValue(), next3.getValue(), ratio3);
                case SMOOTH:
                    ScaleKeyframe pPrev = this.scale.get(Float.valueOf(this.scale.getLowerKey(ctx3.prevKey)));
                    ScaleKeyframe nNext = this.scale.get(Float.valueOf(this.scale.getHigherKey(ctx3.nextKey)));
                    return XLerp.smoothLerp(pPrev.getValue(), prev3.getValue(), next3.getValue(), nNext.getValue(), ratio3);
                case STEP:
                    return prev3.getValue();
                default:
                    return new Vec3d(1.0d, 1.0d, 1.0d);
            }
        });
    }
    public static KeyframeType getType(AbstractKeyframe<?> last, AbstractKeyframe<?> next) {
        if (last.getType() == KeyframeType.STEP) {
            return KeyframeType.STEP;
        }
        if (last.getType() == KeyframeType.SMOOTH || next.getType() == KeyframeType.SMOOTH) {
            return KeyframeType.SMOOTH;
        }
        return KeyframeType.LINEAR;
    }
}
