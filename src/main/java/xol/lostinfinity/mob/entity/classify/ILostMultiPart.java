package xol.lostinfinity.mob.entity.classify;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.DamageSource;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/classify/ILostMultiPart.class */
public interface ILostMultiPart extends IEntityMultiPart {
    boolean attackEntityFromPart(EntityLivingBase entityLivingBase, DamageSource damageSource, float f);

    default boolean func_70965_a(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
        return false;
    }
}
