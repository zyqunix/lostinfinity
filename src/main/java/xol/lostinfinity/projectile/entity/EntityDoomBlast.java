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
public class EntityDoomBlast extends EntityBaseThrowable {
    private static final double amplitude = 0.1d;
    public EntityDoomBlast(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityDoomBlast(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityDoomBlast(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && !result.field_72308_g.equals(func_85052_h()) && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth((Entity) this, result.field_72308_g, 2, (List<String>) Arrays.asList("Darkborn"));
            }
            func_70106_y();
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70170_p.func_175688_a(ParticleInit.GOLDEN_MAGIC, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 200 < 150) {
            func_70024_g(0.0d, amplitude * Math.cos(((double) (this.field_70173_aa % 200)) / 1.5d), 0.0d);
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
