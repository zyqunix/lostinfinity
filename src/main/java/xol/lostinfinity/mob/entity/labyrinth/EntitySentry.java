package xol.lostinfinity.mob.entity.labyrinth;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/labyrinth/EntitySentry.class */
public class EntitySentry extends EntityMultipleLives implements IMaxAttack {
    public EntitySentry(World worldIn) {
        super(worldIn);
        func_70105_a(1.75f, 1.75f);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) entity;
                if (target.func_70644_a(PotionInit.SHOCKED)) {
                    IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.5f);
                    return true;
                }
                IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 120 == 0) {
            func_184185_a(SoundInit.SENTRY_ABILITY, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(5.0d))) {
                near_pl.func_70690_d(new PotionEffect(PotionInit.SHOCKED, 100));
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.ZAP).setSpread(3.0d, 1.0d, 3.0d).setCount(5).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
            }
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.SENTRY_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.SENTRY_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.SENTRY_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 5;
    }

    protected ResourceLocation func_184647_J() {
        if (this.field_70146_Z.nextInt(7) == 0) {
            return LootTableRegistry.LABYRINTH_BOTTOM;
        }
        return null;
    }
}
