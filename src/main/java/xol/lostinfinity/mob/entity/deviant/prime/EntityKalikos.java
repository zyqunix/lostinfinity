package xol.lostinfinity.mob.entity.deviant.prime;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantPrime;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/prime/EntityKalikos.class */
public class EntityKalikos extends EntityDeviantPrime implements IMaxAttack, IConditionalDamage {
    public EntityKalikos(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 4.5f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.15f);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            int tickOffset = this.field_70173_aa % 300;
            if (func_70638_az() != null) {
                if (tickOffset == 100) {
                    if (this.field_70170_p.func_175623_d(func_70638_az().func_180425_c())) {
                        this.field_70170_p.func_175656_a(func_70638_az().func_180425_c(), Blocks.field_150358_i.func_176223_P());
                    }
                    if (this.field_70170_p.func_175623_d(func_180425_c())) {
                        this.field_70170_p.func_175656_a(func_180425_c(), Blocks.field_150358_i.func_176223_P());
                    }
                } else if (tickOffset == 150) {
                    func_70634_a(func_70638_az().field_70165_t, func_70638_az().field_70163_u, func_70638_az().field_70161_v);
                    func_184185_a(SoundInit.RAPID_TELEPORT, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.3f));
                }
            }
            if (tickOffset <= 60) {
                if (tickOffset == 60) {
                    for (EntityLivingBase near_pl : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(12.0d))) {
                        if (!near_pl.equals(this)) {
                            IMaxAttack.dealTrueDamage(this, near_pl, near_pl.func_110138_aP());
                        }
                    }
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.LASER_WEAPON_11, SoundCategory.HOSTILE, 2.0f, 1.0f);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.POWER_FIELD).setIgnoreRange(true);
                    CustomParticleConfig config2 = new CustomParticleConfig();
                    config2.setCount(12);
                    config2.createInstance().setParticle(ParticleInit.EXPLOSION_BLUE).setSpread(18.0d, 2.0d, 18.0d).setIgnoreRange(true);
                    config2.createInstance().setParticle(ParticleInit.EXPLOSION_LAVENDER).setSpread(18.0d, 2.0d, 18.0d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + 1.5d, this.field_70161_v);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u + 1.5d, this.field_70161_v);
                    return;
                }
                if (tickOffset % 10 == 0) {
                    func_184185_a(SoundInit.CHARGING_POWER, 1.2f + (0.1f * MathHelper.func_76141_d(tickOffset / 10)), 0.6f + (0.2f * MathHelper.func_76141_d(tickOffset / 10)));
                    CustomParticleConfig config12 = new CustomParticleConfig();
                    config12.createInstance().setParticle(ParticleInit.PLASMA_RIFT).setSpread(3.0d, 1.0d, 3.0d).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config12, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                }
            }
        }
    }

    protected float func_189749_co() {
        return 1.25f;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.KALIKOS_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.KALIKOS_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.KALIKOS_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime
    protected String primeName() {
        return "Kalikos";
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime
    protected Item primeDrop() {
        return ItemInit.deviantFragmentTL;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return !func_70090_H();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 40;
    }
}
