package xol.lostinfinity.projectile.cthulhu;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/cthulhu/EntityCthulhuTurretBullet.class */
public class EntityCthulhuTurretBullet extends EntityBaseThrowable {
    public EntityCthulhuTurretBullet(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (result.field_72313_a == RayTraceResult.Type.ENTITY && result.field_72308_g != this.field_70192_c && result.field_72308_g != getSecondaryThrower() && (result.field_72308_g instanceof EntityLivingBase) && !(result.field_72308_g instanceof ICthulhuMinion)) {
            EntityLivingBase living = result.field_72308_g;
            IMaxAttack.dealTrueDamage(this.field_70192_c, living, living.func_110138_aP() * 0.25f);
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 100) {
            func_70106_y();
        } else {
            super.func_70071_h_();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
