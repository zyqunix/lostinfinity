package xol.lostinfinity.util.animation.client;

import com.google.common.collect.Maps;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.util.animation.client.blueprint.Animation;
import xol.lostinfinity.util.animation.client.blueprint.AnimationBlueprint;
import xol.lostinfinity.util.animation.model.IXolModel;
import xol.lostinfinity.util.animation.model.ModelRenderer;
import xol.lostinfinity.util.math.EulerAngle;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/AnimationHandler.class */
public class AnimationHandler {
    private final Map<String, AnimationProperty> animations = Maps.newConcurrentMap();
    private final Set<Runnable> queue = new HashSet();
    private AnimationBlueprint blueprint;

    public AnimationBlueprint getBlueprint() {
        return this.blueprint;
    }

    public void setBlueprint(AnimationBlueprint blueprint) {
        if (this.blueprint == blueprint) {
            return;
        }
        this.blueprint = blueprint;
        this.animations.values().forEach((v0) -> {
            v0.refresh();
        });
    }

    public Map<String, AnimationProperty> getAnimations() {
        return this.animations;
    }

    public void update(IXolModel model, float partialTick) {
        this.queue.forEach((v0) -> {
            v0.run();
        });
        this.queue.clear();
        for (String id : this.animations.keySet()) {
            AnimationProperty property = this.animations.get(id);
            if (property == null || property.stopped || !this.blueprint.getAnimations().containsKey(id)) {
                this.animations.remove(id);
            } else {
                property.update(partialTick);
            }
        }
        for (String bone : model.getIndex().keySet()) {
            ModelRenderer renderer = model.getIndex().get(bone);
            renderer.reset();
            Iterator<String> it = this.blueprint.getAnimations().keySet().iterator();
            while (it.hasNext()) {
                AnimationProperty property2 = this.animations.get(it.next());
                if (property2 != null) {
                    Vec3d position = property2.getPosition(bone);
                    EulerAngle rotation = property2.getRotation(bone);
                    Vec3d scale = property2.getScale(bone);
                    if (position != null) {
                        if (property2.isOverride) {
                            renderer.resetPosition();
                        }
                        renderer.field_82906_o = (float) (((double) renderer.field_82906_o) + (position.field_72450_a * 0.0625d));
                        renderer.field_82908_p = (float) (((double) renderer.field_82908_p) + ((-position.field_72448_b) * 0.0625d));
                        renderer.field_82907_q = (float) (((double) renderer.field_82907_q) + (position.field_72449_c * 0.0625d));
                    }
                    if (rotation != null) {
                        if (property2.isOverride) {
                            renderer.resetRotation();
                        }
                        renderer.field_78795_f += rotation.getX() * 0.017453292f;
                        renderer.field_78796_g += rotation.getY() * 0.017453292f;
                        renderer.field_78808_h += rotation.getZ() * 0.017453292f;
                    }
                    if (scale != null) {
                        if (property2.isOverride) {
                            renderer.resetScale();
                        }
                        renderer.scaleX = (float) (((double) renderer.scaleX) * scale.field_72450_a);
                        renderer.scaleY = (float) (((double) renderer.scaleY) * scale.field_72448_b);
                        renderer.scaleZ = (float) (((double) renderer.scaleZ) * scale.field_72449_c);
                    }
                }
            }
        }
    }

    public void play(String id) {
        play(id, null);
    }

    public void play(String id, Consumer<AnimationProperty> consumer) {
        if (this.blueprint == null) {
            this.queue.add(() -> {
                playInternal(id, consumer);
            });
        } else {
            playInternal(id, consumer);
        }
    }

    private void playInternal(String id, Consumer<AnimationProperty> consumer) {
        String animationId = id.toLowerCase(Locale.ROOT);
        Animation animation = this.blueprint.getAnimation(animationId);
        if (animation == null) {
            return;
        }
        AnimationProperty property = new AnimationProperty(() -> {
            return getBlueprint().getAnimation(animationId);
        });
        property.refresh();
        property.speedModifier = 1.0f;
        if (consumer != null) {
            consumer.accept(property);
        }
        this.animations.put(animationId, property);
    }

    public void stop(String id) {
        AnimationProperty property = this.animations.get(id.toLowerCase(Locale.ROOT));
        if (property != null) {
            property.stop();
        }
    }
}
