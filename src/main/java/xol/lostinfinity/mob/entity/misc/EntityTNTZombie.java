package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityTNTZombie.class */
public class EntityTNTZombie extends EntityTameable implements IMaxAttack {
    private boolean exploded;
    private float damageMulti;

    public EntityTNTZombie(World worldIn) {
        super(worldIn);
        this.exploded = false;
        this.damageMulti = 1.0f;
        func_70105_a(1.0f, 1.95f);
    }

    public void changeDamage(float multi) {
        this.damageMulti = multi;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4f));
        this.field_70714_bg.func_75776_a(4, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(this, 1.0d));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
        this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
        this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true, new Class[0]));
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(750.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null && this.field_70173_aa >= 60) {
            explode();
            return false;
        }
        IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
        return false;
    }

    private void explode() {
        if (func_70902_q() != null && !this.exploded) {
            this.exploded = true;
            for (EntityMinion entityMinion : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d))) {
                if (!entityMinion.func_110124_au().equals(func_184753_b()) && !entityMinion.func_110124_au().equals(func_110124_au()) && (!(entityMinion instanceof IEntityOwnable) || entityMinion.func_184753_b() != func_70902_q().func_110124_au())) {
                    if (!(entityMinion instanceof EntityMinion) || entityMinion.func_70902_q() != func_70902_q()) {
                        IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) entityMinion, 1, this.damageMulti);
                    }
                }
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            func_70106_y();
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        removeOwnerTamedTargets();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
                func_70106_y();
            }
            if (this.field_70173_aa == 300) {
                explode();
            }
            if (this.field_70173_aa % 30 == 0 && func_70902_q() != null) {
                float lowest = 100000.0f;
                for (EntityMinion entityMinion : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                    if (!entityMinion.func_110124_au().equals(func_184753_b()) && !(entityMinion instanceof EntityTNTZombie) && (!(entityMinion instanceof IEntityOwnable) || entityMinion.func_184753_b() != func_70902_q().func_110124_au())) {
                        if (!(entityMinion instanceof EntityMinion) || entityMinion.func_70902_q() != func_70902_q()) {
                            float newdist = func_70032_d(entityMinion);
                            if (newdist < lowest) {
                                func_70624_b(entityMinion);
                                lowest = newdist;
                            }
                        }
                    }
                }
            }
        }
    }

    private void removeOwnerTamedTargets() {
        if (func_70902_q() == null || func_70638_az() == null) {
            return;
        }
        EntityMinion entityMinionFunc_70638_az = func_70638_az();
        if ((entityMinionFunc_70638_az instanceof IEntityOwnable) && entityMinionFunc_70638_az.func_184753_b() == func_70902_q().func_110124_au()) {
            func_70624_b(null);
        }
        if ((entityMinionFunc_70638_az instanceof EntityMinion) && entityMinionFunc_70638_az.func_70902_q() == func_70902_q()) {
            func_70624_b(null);
        }
        if (func_70643_av() == null) {
            return;
        }
        EntityMinion entityMinionFunc_70643_av = func_70643_av();
        if ((entityMinionFunc_70643_av instanceof IEntityOwnable) && entityMinionFunc_70643_av.func_184753_b() == func_70902_q().func_110124_au()) {
            func_70604_c(null);
        }
        if ((entityMinionFunc_70643_av instanceof EntityMinion) && entityMinionFunc_70643_av.func_70902_q() == func_70902_q()) {
            func_70604_c(null);
        }
    }

    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            explode();
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187930_hd;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187934_hh;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    protected boolean func_70692_ba() {
        return func_70902_q() == null;
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }
}
