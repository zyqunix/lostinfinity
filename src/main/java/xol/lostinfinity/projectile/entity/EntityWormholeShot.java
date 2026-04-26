package xol.lostinfinity.projectile.entity;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityWormholeShot extends EntityBaseThrowable {
    public EntityWormholeShot(World par1World) {
        super(par1World);
        func_70105_a(0.7f, 0.7f);
    }
    public EntityWormholeShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityWormholeShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                if (result.field_72308_g.func_110143_aJ() <= result.field_72308_g.func_110138_aP() * 0.25f) {
                    IMaxAttack.dealTrueDamage(this, result.field_72308_g, result.field_72308_g.func_110138_aP() * 0.5f, Arrays.asList("Darkborn"));
                } else {
                    IMaxAttack.dealMaxHealth((Entity) this, result.field_72308_g, 2, (List<String>) Arrays.asList("Darkborn"));
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.01f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(ParticleInit.ION_FUEL, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
