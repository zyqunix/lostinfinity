package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityBallOfContainedGluons.class */
public class EntityBallOfContainedGluons extends EntityBaseThrowable {
    public EntityBallOfContainedGluons(World par1World) {
        super(par1World);
    }

    public EntityBallOfContainedGluons(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityBallOfContainedGluons(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    protected float func_70185_h() {
        return 0.05f;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.BLOCK) {
            func_70106_y();
        }
    }
}
