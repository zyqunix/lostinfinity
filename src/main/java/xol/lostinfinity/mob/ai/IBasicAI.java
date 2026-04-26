package xol.lostinfinity.mob.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/ai/IBasicAI.class */
public interface IBasicAI {
    default void initBasicTasks(EntityCreature entityIn) {
        entityIn.field_70714_bg.func_75776_a(0, new EntityAISwimming(entityIn));
        entityIn.field_70714_bg.func_75776_a(2, new EntityAIAttackMelee(entityIn, 1.0d, true));
        entityIn.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(entityIn, 1.0d));
        entityIn.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(entityIn, 1.0d, false));
        entityIn.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(entityIn, 1.0d));
        entityIn.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(entityIn, EntityPlayer.class, 8.0f));
        entityIn.field_70714_bg.func_75776_a(8, new EntityAILookIdle(entityIn));
        entityIn.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(entityIn, true, new Class[]{EntityPigZombie.class}));
        entityIn.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(entityIn, EntityPlayer.class, true));
        entityIn.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(entityIn, EntityVillager.class, false));
        entityIn.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(entityIn, EntityIronGolem.class, true));
    }
}
