package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
public class EntityPowerPulse extends EntityBaseThrowable {
    public EntityPowerPulse(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityPowerPulse(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && !result.field_72308_g.equals(func_85052_h()) && (result.field_72308_g instanceof EntityLivingBase)) {
                result.field_72308_g.func_70024_g(this.field_70159_w * 8.0d, this.field_70181_x * 8.0d, this.field_70179_y * 8.0d);
                result.field_72308_g.field_70133_I = true;
            }
            func_70106_y();
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 3; i++) {
                this.field_70170_p.func_175682_a(ParticleInit.POWER_PULSE, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
            }
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
