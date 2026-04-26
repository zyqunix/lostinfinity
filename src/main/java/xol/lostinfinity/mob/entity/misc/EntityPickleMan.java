package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityPickleMan.class */
public class EntityPickleMan extends EntityMultiLivesTameable implements IMaxAttack {
    private static final DataParameter<Float> SCALE = EntityDataManager.func_187226_a(EntityPickleMan.class, DataSerializers.field_187193_c);
    private int myLives;
    private float currentStepScale;
    private float addedTrueDamage;

    public EntityPickleMan(World worldIn) {
        super(worldIn);
        this.myLives = 5;
        this.currentStepScale = 1.0f;
        this.addedTrueDamage = 0.0f;
        func_70105_a(1.2f, 2.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SCALE, Float.valueOf(1.0f));
    }

    public float getMyScale() {
        return ((Float) this.field_70180_af.func_187225_a(SCALE)).floatValue();
    }

    public void setMyScale(float f) {
        if (f > getMyScale()) {
            this.field_70180_af.func_187227_b(SCALE, Float.valueOf(f));
        }
    }

    public float getMyStepScale() {
        return this.currentStepScale;
    }

    public void addExtraLives(int lifeSet) {
        this.myLives += lifeSet;
    }

    public void addTrueDamageToAttacks(float damage) {
        this.addedTrueDamage += damage;
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
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.45d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (this.addedTrueDamage > 0.0f) {
                IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * this.addedTrueDamage);
            }
            if (func_70638_az().func_110143_aJ() > 0.0f && func_70638_az() != null) {
                IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            }
            this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u + 1.5d, this.field_70161_v, 2, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d * ((-0.5d) + this.field_70146_Z.nextDouble()), (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WHACK, SoundCategory.HOSTILE, 1.0f, 0.7f + (0.6f * this.field_70146_Z.nextFloat()));
            return false;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa > 20 && this.currentStepScale < getMyScale()) {
            this.currentStepScale += 0.1f;
            func_70105_a(1.2f * this.currentStepScale, 2.5f * this.currentStepScale);
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
                func_70106_y();
            }
            if (this.field_70173_aa % 30 == 0) {
                float lowest = 100000.0f;
                for (EntityLivingBase li : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                    if (!li.func_110124_au().equals(func_184753_b()) && !(li instanceof EntityPickleMan)) {
                        float newdist = func_70032_d(li);
                        if (newdist < lowest) {
                            func_70624_b(li);
                            lowest = newdist;
                        }
                    }
                }
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187943_hq;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187944_hr;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    protected boolean func_70692_ba() {
        return func_70902_q() == null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    protected int numberOfLives() {
        return this.myLives;
    }
}
