package xol.lostinfinity.mob.entity.misc;

import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityMortarShot;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityMortarCannon.class */
public class EntityMortarCannon extends EntityBaseCannon {
    public EntityMortarCannon(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.misc.EntityBaseCannon, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
            EntityMortarShot shot = new EntityMortarShot(this.field_70170_p, this);
            shot.func_70107_b(this.field_70165_t, this.field_70163_u + (((double) this.field_70131_O) / 1.6d), this.field_70161_v);
            shot.setSecondaryThrower(getOwner());
            double x = Math.cos(((double) getRotation()) + 1.5707963267948966d);
            double z = Math.sin(((double) getRotation()) + 1.5707963267948966d);
            shot.func_70186_c(x, 0.6d, z, 1.5f, 0.0f);
            this.field_70170_p.func_72838_d(shot);
            func_184185_a(SoundInit.GENERIC_WEAPON_7, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
        }
    }
}
