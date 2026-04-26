package xol.lostinfinity.mob.entity.misc;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityCosmicBomb.class */
public class EntityCosmicBomb extends Entity implements IMaxAttack {
    private UUID creator_UUID;
    private int timer;
    private int repeats;
    private int splits;
    private Vec3d dir;

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public void setDir(Vec3d dir) {
        this.dir = dir;
    }

    public EntityCosmicBomb(World worldIn) {
        super(worldIn);
        this.creator_UUID = null;
        this.timer = 0;
        this.repeats = 0;
        this.splits = 0;
        this.dir = null;
        func_189654_d(true);
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }

    public void setSplits(int s) {
        this.splits = s;
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
            config1.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE1).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
            config1.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE2).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
            config1.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE3).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
            config1.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE4).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
            CustomParticleConfig config2 = new CustomParticleConfig();
            config2.setCount(4);
            config2.createInstance().setParticle(ParticleInit.BASIC_STAR_TYPE1).setSpread(5.0d, 3.0d, 5.0d).setIgnoreRange(true);
            config2.createInstance().setParticle(ParticleInit.BASIC_STAR_TYPE2).setSpread(5.0d, 3.0d, 5.0d).setIgnoreRange(true);
            config2.createInstance().setParticle(ParticleInit.BASIC_STAR_TYPE3).setSpread(5.0d, 3.0d, 5.0d).setIgnoreRange(true);
            config2.createInstance().setParticle(ParticleInit.BASIC_STAR_TYPE4).setSpread(5.0d, 3.0d, 5.0d).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + 0.25d, this.field_70161_v);
            IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u + 0.25d, this.field_70161_v);
            if (this.repeats % 4 == 0) {
                func_184185_a(SoundInit.COSMIC_EXPLOSION, 2.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
            }
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos checkPos = this.field_70170_p.func_175645_m(func_180425_c().func_177982_a(x, 0, z));
                    if (this.field_70170_p.func_175623_d(checkPos)) {
                        this.field_70170_p.func_175656_a(checkPos, BlockInit.cosmicFracture.func_176223_P());
                    }
                }
            }
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(3.0d))) {
                if (!target.func_110124_au().equals(this.creator_UUID)) {
                    IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 2.0f);
                }
            }
            if (this.repeats % 6 == 0) {
                for (EntityLivingBase target2 : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(12.0d))) {
                    if (!target2.func_110124_au().equals(this.creator_UUID)) {
                        target2.func_70690_d(new PotionEffect(PotionInit.TETHERED, 200, 2));
                    }
                }
            }
            if (this.repeats > 0) {
                EntityCosmicBomb bomb = new EntityCosmicBomb(this.field_70170_p);
                bomb.setCreator(this.creator_UUID);
                int height = this.field_70170_p.func_189649_b(func_180425_c().func_177958_n() + (((int) this.dir.field_72450_a) * 3), func_180425_c().func_177952_p() + (((int) this.dir.field_72449_c) * 3));
                bomb.func_70107_b(((double) func_180425_c().func_177958_n()) + (this.dir.field_72450_a * 3.0d), height + 1, ((double) func_180425_c().func_177952_p()) + (this.dir.field_72449_c * 3.0d));
                bomb.setTimer(5);
                bomb.setDir(this.dir);
                bomb.setRepeats(this.repeats - 1);
                this.field_70170_p.func_72838_d(bomb);
                if (this.repeats > 2 && this.splits <= 1 && this.field_70146_Z.nextInt(20) == 0) {
                    this.splits++;
                    EntityCosmicBomb splitbomb = new EntityCosmicBomb(this.field_70170_p);
                    splitbomb.setCreator(this.creator_UUID);
                    int height2 = this.field_70170_p.func_189649_b(func_180425_c().func_177958_n() + (((int) this.dir.field_72450_a) * 3), func_180425_c().func_177952_p() + (((int) this.dir.field_72449_c) * 3));
                    splitbomb.func_70107_b(((double) func_180425_c().func_177958_n()) + (this.dir.field_72450_a * 3.0d), height2 + 1, ((double) func_180425_c().func_177952_p()) + (this.dir.field_72449_c * 3.0d));
                    splitbomb.setTimer(5);
                    splitbomb.setSplits(this.splits);
                    splitbomb.setDir(this.dir.func_178785_b((-1.0f) + (this.field_70146_Z.nextFloat() * 2.0f)));
                    splitbomb.setRepeats(this.repeats - 1);
                    this.field_70170_p.func_72838_d(splitbomb);
                }
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
