package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityGravityWarper extends EntityBaseThrowable {
    public EntityGravityWarper(World par1World) {
        super(par1World);
    }
    public EntityGravityWarper(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityGravityWarper(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 3; i++) {
                this.field_70170_p.func_175682_a(ParticleInit.GRAVITY_RING, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
            }
            return;
        }
        if (func_85052_h() != null) {
            for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                if (!entity.func_110124_au().equals(func_85052_h().func_110124_au())) {
                    entity.func_70024_g(Math.signum(this.field_70165_t - entity.field_70165_t) * 0.5d, Math.signum(this.field_70163_u - entity.field_70163_u) * 0.6d, Math.signum(this.field_70161_v - entity.field_70161_v) * 0.5d);
                    entity.field_70133_I = true;
                    if (this.field_70173_aa % 5 == 0 && func_70032_d(entity) < 3.0f) {
                        IMaxAttack.dealMaxHealth(func_85052_h(), entity, 6);
                    }
                }
            }
        }
    }
}
