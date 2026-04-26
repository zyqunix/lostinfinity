package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantSpit extends EntityBaseThrowable {
    public EntityDeviantSpit(World par1World) {
        super(par1World);
    }
    public EntityDeviantSpit(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityDeviantSpit(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                EntityLivingBase hit_entity = result.field_72308_g;
                IMaxAttack.dealMaxHealth(this, hit_entity, 3);
                hit_entity.field_70159_w = 0.0d;
                hit_entity.field_70181_x = 0.0d;
                hit_entity.field_70179_y = 0.0d;
                hit_entity.func_70024_g(Math.signum(func_85052_h().field_70165_t - hit_entity.field_70165_t) * 1.1d, Math.signum(func_85052_h().field_70163_u - hit_entity.field_70163_u) * 0.5d, Math.signum(func_85052_h().field_70161_v - hit_entity.field_70161_v) * 1.1d);
                hit_entity.field_70133_I = true;
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        for (int i = 0; i < 4; i++) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPIT, this.field_70165_t, this.field_70163_u, this.field_70161_v, (-0.5d) + this.field_70146_Z.nextDouble(), (-0.5d) + this.field_70146_Z.nextDouble(), (-0.5d) + this.field_70146_Z.nextDouble(), new int[0]);
        }
    }
}
