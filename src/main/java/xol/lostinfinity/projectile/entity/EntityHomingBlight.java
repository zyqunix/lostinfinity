package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityHomingBlight.class */
public class EntityHomingBlight extends EntityBaseThrowable {
    private EntityLivingBase target;

    public EntityHomingBlight(World par1World) {
        super(par1World);
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityHomingBlight(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityHomingBlight(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && !result.field_72308_g.equals(func_85052_h()) && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 1);
            }
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && this.target != null) {
            Vec3d dir = this.target.func_174791_d().func_178788_d(func_174791_d()).func_72432_b();
            this.field_70159_w = dir.field_72450_a * 0.01d;
            this.field_70181_x = dir.field_72448_b * 0.01d;
            this.field_70179_y = dir.field_72449_c * 0.01d;
            this.field_70133_I = true;
        }
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
