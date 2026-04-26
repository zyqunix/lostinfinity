package xol.lostinfinity.mob.entity.sea;

import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAILookAround;
import xol.lostinfinity.mob.ai.EntityAIRandomFly;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/EntityRibshark.class */
public class EntityRibshark extends EntitySeaCreature {
    public EntityRibshark(World worldIn) {
        super(worldIn);
        this.rawFlySpeed = 0.95f;
        func_70105_a(1.5f, 1.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(5, new EntityAIRandomFly(this));
        this.field_70714_bg.func_75776_a(7, new EntityAILookAround(this));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(10, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.4f, Arrays.asList("Aquatic"));
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.RIB_SHARK_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.RIB_SHARK_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.RIB_SHARK_AMBIENT;
    }
}
