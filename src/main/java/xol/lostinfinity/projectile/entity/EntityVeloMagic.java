package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityVeloMagic extends EntityBaseThrowable {
    private static final DataParameter<Float> PROJWEIGHT = EntityDataManager.func_187226_a(EntityVeloMagic.class, DataSerializers.field_187193_c);
    private int denom;
    public EntityVeloMagic(World par1World) {
        super(par1World);
        this.denom = 8;
        func_70105_a(0.4f, 0.4f);
    }
    public EntityVeloMagic(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.denom = 8;
    }
    public EntityVeloMagic(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.denom = 8;
    }
    public void setGravity(Float g) {
        this.field_70180_af.func_187227_b(PROJWEIGHT, g);
    }
    public void setDenom(int d) {
        this.denom = d;
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(PROJWEIGHT, Float.valueOf(0.06f));
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void calculateVelocity(double x, double y, double z) {
        this.field_70159_w = x;
        this.field_70181_x = y;
        this.field_70179_y = z;
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float f = MathHelper.func_76133_a((x * x) + (z * z));
            this.field_70177_z = (float) (MathHelper.func_181159_b(x, z) * 57.29577951308232d);
            this.field_70125_A = (float) (MathHelper.func_181159_b(y, f) * 57.29577951308232d);
            this.field_70126_B = this.field_70177_z;
            this.field_70127_C = this.field_70125_A;
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, this.denom);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return ((Float) this.field_70180_af.func_187225_a(PROJWEIGHT)).floatValue();
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
