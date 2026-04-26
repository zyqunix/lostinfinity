package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityExplosiveGoo;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGiantFyreweed.class */
public class EntityGiantFyreweed extends EntityMultipleLives implements IMaxAttack {
    public EntityGiantFyreweed(World worldIn) {
        super(worldIn);
        func_70105_a(2.5f, 3.9f);
    }

    public boolean func_180427_aV() {
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 3, 2.0f);
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70179_y = 0.0d;
        this.field_70181_x += 0.10000000149011612d;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa <= 2) {
            for (EntityGiantFyreweed near_weed : this.field_70170_p.func_72872_a(EntityGiantFyreweed.class, func_174813_aQ().func_72314_b(35.0d, 35.0d, 35.0d))) {
                if (!near_weed.func_110124_au().equals(func_110124_au())) {
                    func_70106_y();
                }
            }
        }
        if (this.field_70173_aa % 90 < 40 && this.field_70173_aa % 5 == 0) {
            boolean did_shot = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(45.0d, 45.0d, 45.0d))) {
                if (!near_pl.func_184812_l_() && !this.field_70170_p.field_72995_K) {
                    func_70676_i(1.0f);
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + 0.3d;
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityExplosiveGoo shot = new EntityExplosiveGoo(this.field_70170_p, makeX, makeY, makeZ);
                    shot.setThrower(this);
                    shot.setDenomAndSize(5, 3);
                    shot.func_70186_c(d2, d3, d4, 1.5f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                    did_shot = true;
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.FIREGOO).setSpread(1.0d, 1.0d, 1.0d).setSpeed(1.0d, 1.0d, 1.0d).setVelSpread(1.0d, 1.0d, 1.0d).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config1, makeX, makeY, makeZ);
                }
            }
            if (did_shot) {
                func_184185_a(SoundInit.GALAXYFIRE, 2.0f, 1.0f);
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }

    protected SoundEvent func_184615_bR() {
        return null;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_GIANTFYREWEED;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }

    public int func_70641_bl() {
        return 1;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
