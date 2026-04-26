package xol.lostinfinity.projectile.cthulhu;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/cthulhu/EntityCthulhuMissile.class */
public class EntityCthulhuMissile extends EntityBaseThrowable {
    private EntityLivingBase target;

    public EntityCthulhuMissile(World worldIn) {
        super(worldIn);
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
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
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K || this.target == null || this.target.field_70128_L) {
            return;
        }
        Vec3d mot = LMath.fastNormalize(new Vec3d(this.target.field_70165_t - this.field_70165_t, this.target.field_70163_u - this.field_70163_u, this.target.field_70161_v - this.field_70161_v)).func_186678_a(0.5d);
        this.field_70159_w *= 0.8999999761581421d;
        this.field_70181_x *= 0.8999999761581421d;
        this.field_70179_y *= 0.8999999761581421d;
        this.field_70159_w += mot.field_72450_a;
        this.field_70181_x += mot.field_72448_b;
        this.field_70179_y += mot.field_72449_c;
        this.field_70133_I = true;
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
