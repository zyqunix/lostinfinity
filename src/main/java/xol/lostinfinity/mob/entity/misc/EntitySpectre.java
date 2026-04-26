package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.mob.entity.base.EntityFloatingTameable;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntitySpectre.class */
public class EntitySpectre extends EntityFloatingTameable implements IMaxAttack {
    private static final DataParameter<Boolean> PRIME = EntityDataManager.func_187226_a(EntitySpectre.class, DataSerializers.field_187198_h);

    public EntitySpectre(World worldIn) {
        super(worldIn);
        func_70105_a(1.2f, 1.75f);
        this.rawFlySpeed = 0.85f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(750.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingTameable, xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(PRIME, false);
    }

    public boolean isPrime() {
        return ((Boolean) this.field_70180_af.func_187225_a(PRIME)).booleanValue();
    }

    public void setPrime(boolean p) {
        if (p) {
            this.rawFlySpeed = 0.95f;
        } else {
            this.rawFlySpeed = 0.85f;
        }
        this.field_70180_af.func_187227_b(PRIME, Boolean.valueOf(p));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingTameable, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("SpectreQual", isPrime());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingTameable, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setPrime(tag.func_74767_n("SpectreQual"));
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (isPrime()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.SHATTERED, 200, 0));
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200, 0));
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 200, 0));
                if (func_70902_q() != null) {
                    func_70902_q().func_70691_i(func_70902_q().func_110138_aP() * 0.15f);
                }
            }
            IMaxAttack.dealMaxHealth(this, func_70638_az(), isPrime() ? 2 : 4);
            this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v, 2, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d * ((-0.5d) + this.field_70146_Z.nextDouble()), (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
                func_70106_y();
            }
            func_70606_j(func_110143_aJ() - 1.0f);
            if (func_70638_az() != null) {
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
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

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return isPrime() ? 4 : 1;
    }
}
