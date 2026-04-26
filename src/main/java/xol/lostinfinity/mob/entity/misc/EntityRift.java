package xol.lostinfinity.mob.entity.misc;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityRift.class */
public class EntityRift extends EntityBaseRift implements IMaxAttack {
    private Entity owner;

    public EntityRift(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.misc.EntityBaseRift, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70181_x = 0.0d;
        this.field_70179_y = 0.0d;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 10 == 0) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(20.0d))) {
                    if (!near_pl.equals(this.owner) && !near_pl.func_184812_l_()) {
                        near_pl.func_70690_d(new PotionEffect(PotionInit.DIMENSIONAL_TEAR, 40, 2));
                    }
                }
                return;
            }
            return;
        }
        if (this.field_70146_Z.nextInt(2) == 0) {
            this.field_70170_p.func_175682_a(ParticleInit.PLASMA, true, this.field_70165_t + getROD(8), this.field_70163_u + 2.0d, this.field_70161_v + getROD(8), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }

    public Entity getOwner() {
        return this.owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        if (this.owner != null) {
            compound.func_74778_a("owner", this.owner.func_110124_au().toString());
        }
    }

    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_74764_b("owner")) {
            this.owner = this.field_70170_p.func_152378_a(UUID.fromString(compound.func_74779_i("owner")));
        }
    }
}
