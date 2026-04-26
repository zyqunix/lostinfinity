package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityCelestialFire extends EntityBaseThrowable {
    public EntityCelestialFire(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityCelestialFire(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityCelestialFire(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 2);
            }
            func_70106_y();
            return;
        }
        if (this.field_70173_aa > 4) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.LAVA, this.field_70165_t, this.field_70163_u, this.field_70161_v, (this.field_70170_p.field_73012_v.nextDouble() - 0.5d) * 2.0d, -this.field_70170_p.field_73012_v.nextDouble(), (this.field_70170_p.field_73012_v.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
