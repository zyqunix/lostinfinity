package xol.lostinfinity.util.animation.entity;
import net.minecraft.entity.Entity;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.util.animation.client.AnimationHandler;
import xol.lostinfinity.util.animation.client.blueprint.AnimationBlueprint;
import xol.lostinfinity.util.animation.client.blueprint.LoopMode;
import xol.lostinfinity.util.animation.packet.PacketAnimation;
public interface IXolAnimated {
    AnimationHandler getAnimationHandler();
    default void playAnimation(String id, float speed) {
        Entity entity = getEntity();
        if (entity.field_70170_p.field_72995_K) {
            getAnimationHandler().play(id, animationProperty -> {
                animationProperty.speedModifier = speed;
            });
        } else {
            lostinfinity.instance.packetHandler.sendToPlayerExcept(entity, new PacketAnimation(this, id, speed));
        }
    }
    default void playAnimation(String id, LoopMode mode, boolean isOverride, float speed) {
        Entity entity = getEntity();
        if (entity.field_70170_p.field_72995_K) {
            getAnimationHandler().play(id, animationProperty -> {
                animationProperty.loopMode = mode;
                animationProperty.isOverride = isOverride;
                animationProperty.speedModifier = speed;
            });
        } else {
            lostinfinity.instance.packetHandler.sendToPlayerExcept(entity, new PacketAnimation(this, id, mode, isOverride, speed));
        }
    }
    default void stopAnimation(String id) {
        Entity entity = getEntity();
        if (entity.field_70170_p.field_72995_K) {
            getAnimationHandler().stop(id);
        } else {
            lostinfinity.instance.packetHandler.sendToPlayerExcept(entity, new PacketAnimation(this, id));
        }
    }
    default void setAnimationBlueprint(AnimationBlueprint animationBlueprint) {
        getAnimationHandler().setBlueprint(animationBlueprint);
    }
    default Entity getEntity() {
        return (Entity) this;
    }
}
