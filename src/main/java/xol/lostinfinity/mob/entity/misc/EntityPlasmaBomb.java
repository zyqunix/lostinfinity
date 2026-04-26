package xol.lostinfinity.mob.entity.misc;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityPlasmaBomb.class */
public class EntityPlasmaBomb extends Entity implements IMaxAttack {
    private UUID creator_UUID;
    private int timer;
    private int repeats;
    private Vec3d dir;

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public void setDir(Vec3d dir) {
        this.dir = dir;
    }

    public EntityPlasmaBomb(World worldIn) {
        super(worldIn);
        this.creator_UUID = null;
        this.timer = 0;
        this.repeats = 0;
        this.dir = null;
        func_189654_d(true);
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L && this.field_70173_aa >= this.timer) {
            explosionEffect();
        }
    }

    public void explosionEffect() {
        if (this.creator_UUID != null && this.dir != null) {
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.PLASMA_RIFT).setSpread(3.0d, 1.0d, 3.0d).setCount(8).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + 0.25d, this.field_70161_v);
            CustomParticleConfig config2 = new CustomParticleConfig();
            config2.createInstance().setParticle(ParticleInit.PLASMA_EXPLOSION).setCount(4).setIgnoreRange(true).setArgs(2);
            IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u + 0.5d, this.field_70161_v);
            CustomParticleConfig config3 = new CustomParticleConfig();
            config3.createInstance().setParticle(ParticleInit.NUCLEAR_BLAST).setSpread(1.0d, 1.0d, 1.0d).setCount(5).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config3, this.field_70165_t, this.field_70163_u + 0.5d, this.field_70161_v);
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_14, SoundCategory.PLAYERS, 1.5f, 0.7f + (0.6f * this.field_70146_Z.nextFloat()));
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(5.0d))) {
                if (!target.func_110124_au().equals(this.creator_UUID)) {
                    IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP());
                }
            }
            if (this.repeats > 0) {
                EntityPlasmaBomb bomb = new EntityPlasmaBomb(this.field_70170_p);
                bomb.setCreator(this.creator_UUID);
                int height = this.field_70170_p.func_189649_b(func_180425_c().func_177958_n() + (((int) this.dir.field_72450_a) * 3), func_180425_c().func_177952_p() + (((int) this.dir.field_72449_c) * 3));
                bomb.func_70107_b(((double) func_180425_c().func_177958_n()) + (this.dir.field_72450_a * 3.0d), height + 1, ((double) func_180425_c().func_177952_p()) + (this.dir.field_72449_c * 3.0d));
                bomb.setTimer(5);
                bomb.setDir(this.dir);
                bomb.setRepeats(this.repeats - 1);
                this.field_70170_p.func_72838_d(bomb);
            }
        }
        func_70106_y();
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }
}
