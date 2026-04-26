package xol.lostinfinity.mob.entity.misc;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityPlasmaExplosion.class */
public class EntityPlasmaExplosion extends EntityImmaterial implements IMaxAttack {
    private int timer;
    private UUID creator_UUID;
    private static final DataParameter<Integer> EXPL_SIZE = EntityDataManager.func_187226_a(EntityPlasmaExplosion.class, DataSerializers.field_187192_b);

    public EntityPlasmaExplosion(World worldIn) {
        super(worldIn);
        this.timer = 30;
        func_70105_a(4.0f, 4.0f);
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("ExplosionSize", getExplScale());
        tag.func_186854_a("CreatorUUID", this.creator_UUID);
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setExplScale(tag.func_74762_e("ExplosionSize"));
        this.creator_UUID = tag.func_186857_a("CreatorUUID");
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(EXPL_SIZE, 0);
    }

    public int getExplScale() {
        return ((Integer) this.field_70180_af.func_187225_a(EXPL_SIZE)).intValue();
    }

    public void setExplScale(int scl) {
        this.field_70180_af.func_187227_b(EXPL_SIZE, Integer.valueOf(scl));
        if (scl >= maxExpSize()) {
            explosionEffect();
        }
    }

    public void upExplScale() {
        int new_scl = Math.min(getExplScale() + 1, maxExpSize());
        this.field_70180_af.func_187227_b(EXPL_SIZE, Integer.valueOf(new_scl));
        this.timer = 20;
    }

    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }

    public int maxExpSize() {
        return 15;
    }

    public void setBoom() {
        this.timer = 0;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        int scl = getExplScale() / 2;
        func_70105_a(4 + scl, 4 + scl);
        if (!this.field_70170_p.field_72995_K) {
            if (this.timer <= 0 && !this.field_70128_L) {
                explosionEffect();
            }
            this.timer--;
        }
    }

    private void explosionEffect() {
        IParticleSpawner.spawnParticle(this.field_70170_p, 2, 20, this.field_70165_t, this.field_70163_u + 0.5d, this.field_70161_v);
        func_184185_a(SoundInit.GENERIC_WEAPON_4, 4.0f, 1.0f);
        int data = getExplScale();
        int growScale = data + 2 + Math.round(data / 2);
        for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(growScale, growScale, growScale))) {
            if (!target.func_110124_au().equals(this.creator_UUID) && !target.func_110124_au().equals(func_110124_au())) {
                CustomParticleConfig config = new CustomParticleConfig();
                config.createInstance().setParticle(ParticleInit.PLASMA_EXPLOSION).setCount(4).setIgnoreRange(true).setArgs(2);
                IParticleSpawner.spawnParticle(this.field_70170_p, config, target.field_70165_t, target.field_70163_u + ((double) target.field_70131_O), target.field_70161_v);
                IMaxAttack.dealMaxHealth((Entity) this, target, 10, 4 + Math.round(data / 2));
            }
        }
        func_70106_y();
    }

    protected void func_82167_n(Entity entityIn) {
    }

    public boolean func_70075_an() {
        return false;
    }
}
