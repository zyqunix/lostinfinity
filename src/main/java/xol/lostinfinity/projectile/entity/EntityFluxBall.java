package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.dimension.util.BasicTeleporter;
public class EntityFluxBall extends EntityBaseThrowable {
    private int dim;
    private double telex;
    private double teley;
    private double telez;
    public EntityFluxBall(World par1World) {
        super(par1World);
    }
    public EntityFluxBall(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityFluxBall(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    public void setFlux(int d, double x, double y, double z) {
        this.dim = d;
        this.telex = x;
        this.teley = y;
        this.telez = z;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityPlayer)) {
                if (result.field_72308_g.field_71093_bK != this.dim) {
                    result.field_72308_g.changeDimension(this.dim, new BasicTeleporter(result.field_72308_g.func_184102_h().func_71218_a(this.dim), this.telex, this.teley, this.telez));
                } else {
                    result.field_72308_g.func_70634_a(this.telex, this.teley, this.telez);
                }
            }
            func_70106_y();
        }
        if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
            func_184185_a(SoundEvents.field_187534_aX, 3.0f, 1.0f);
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_WITCH, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
