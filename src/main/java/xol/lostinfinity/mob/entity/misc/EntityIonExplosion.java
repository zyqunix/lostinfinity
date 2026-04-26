package xol.lostinfinity.mob.entity.misc;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityIonExplosion.class */
public class EntityIonExplosion extends EntityImmaterial implements IMaxAttack {
    private UUID creator_UUID;
    private boolean hasExploded;

    public EntityIonExplosion(World worldIn) {
        super(worldIn);
        this.hasExploded = false;
        func_70105_a(0.001f, 0.001f);
        func_184224_h(true);
        func_82142_c(true);
        func_189654_d(true);
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_186854_a("CreatorUUID", this.creator_UUID);
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.creator_UUID = tag.func_186857_a("CreatorUUID");
    }

    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            double radius = 5 + this.field_70173_aa;
            float f = 0.0f;
            while (true) {
                float angle = f;
                if (angle > 6.283185307179586d) {
                    break;
                }
                double velocity_x = radius * Math.cos(angle);
                double velocity_z = radius * Math.sin(angle);
                this.field_70170_p.func_175682_a(ParticleInit.ION_BLAST, true, this.field_70165_t + velocity_x, this.field_70163_u + 3.0d, this.field_70161_v + velocity_z, 0.0d, 0.0d, 0.0d, new int[0]);
                this.field_70170_p.func_175682_a(ParticleInit.NUCLEAR_BLAST, true, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u + 6.0d, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                f = (float) (((double) angle) + 0.39269908169872414d);
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            if (!this.hasExploded && this.field_70173_aa >= 1 && this.creator_UUID != null) {
                explosion();
            }
            if (this.field_70173_aa >= 20) {
                func_70106_y();
            }
        }
    }

    private void explosion() {
        this.hasExploded = true;
        EntityIonExplosion entityIonExplosion = this;
        EntityIonExplosion entityIonExplosionFunc_177451_a = this.field_70170_p.func_73046_m().func_184103_al().func_177451_a(this.creator_UUID);
        if (entityIonExplosionFunc_177451_a != null) {
            entityIonExplosion = entityIonExplosionFunc_177451_a;
        }
        for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(24.0d, 24.0d, 24.0d))) {
            if (!target.func_110124_au().equals(this.creator_UUID) && !target.func_110124_au().equals(func_110124_au())) {
                IMaxAttack.dealMaxHealth((Entity) entityIonExplosion, target, 1, 2.0f);
            }
        }
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(70.0d, 70.0d, 70.0d))) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, near_pl.func_180425_c(), SoundInit.GENERIC_EXPLOSION, SoundCategory.MASTER, 1.5f, 0.75f + (this.field_70146_Z.nextFloat() * 0.5f));
        }
    }
}
