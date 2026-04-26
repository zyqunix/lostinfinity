package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityRockshot.class */
public class EntityRockshot extends EntityBaseThrowable {
    public EntityRockshot(World par1World) {
        super(par1World);
    }

    public EntityRockshot(World par1World, EntityLivingBase thrower, double par2, double par4, double par6, float speedMulti) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
        setThrower(thrower);
        calculateTrajectory(thrower, par2, par4, par6, speedMulti);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 4);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
