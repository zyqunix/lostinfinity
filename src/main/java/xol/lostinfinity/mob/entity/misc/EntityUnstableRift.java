package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityUnstableRift.class */
public class EntityUnstableRift extends EntityBaseRift implements IMaxAttack {
    private EntityPlayer owner;

    public EntityUnstableRift(World worldIn) {
        super(worldIn);
        this.owner = null;
    }

    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }

    @Override // xol.lostinfinity.mob.entity.misc.EntityBaseRift, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70181_x = 0.0d;
        this.field_70179_y = 0.0d;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa >= 120) {
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.ION_BLAST).setSpread(6.0d, 4.0d, 6.0d).setCount(10).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.EXPLOSION_LAVENDER).setSpread(5.0d, 1.0d, 5.0d).setCount(10).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_4, SoundCategory.PLAYERS, 1.0f, 0.9f + (this.field_70146_Z.nextFloat() * 0.2f));
                for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(20.0d, 20.0d, 20.0d))) {
                    if (!target.equals(this.owner) && !target.func_110124_au().equals(func_110124_au())) {
                        IMaxAttack.dealMaxHealth((Entity) this, target, 1, 1.0f);
                    }
                }
                func_70106_y();
                return;
            }
            return;
        }
        if (this.field_70146_Z.nextInt(2) == 0) {
            this.field_70170_p.func_175682_a(ParticleInit.FLAME_MEDIUM, true, this.field_70165_t + getROD(8), this.field_70163_u + 2.0d, this.field_70161_v + getROD(8), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }
}
