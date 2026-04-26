package xol.lostinfinity.mob.entity.classify;

import net.minecraft.entity.Entity;
import xol.lostinfinity.util.data.CustomDamageResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/classify/IOwnerReactive.class */
public interface IOwnerReactive {
    default void maxHealthDamageEffect(Entity attacker, CustomDamageResult result) {
    }

    default void trueDamageEffect(Entity attacker, CustomDamageResult result) {
    }
}
