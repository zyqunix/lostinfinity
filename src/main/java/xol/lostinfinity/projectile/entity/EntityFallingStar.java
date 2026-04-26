package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityFallingStar extends EntityBaseThrowable {
    private int count;
    private BlockPos starting;
    public EntityFallingStar(World par1World) {
        super(par1World);
        this.count = 0;
        func_70105_a(1.0f, 1.0f);
    }
    public EntityFallingStar(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.count = 0;
    }
    public EntityFallingStar(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.count = 0;
    }
    public void setCount(int newc) {
        this.count = newc;
    }
    public void setStartLoc(double sx, double sy, double sz) {
        this.starting = new BlockPos(sx, sy, sz);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 3);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.010000001f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.CRIT, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            return;
        }
        if (this.field_70173_aa == 2 && this.count < 49 && this.starting != null) {
            EntityFallingStar star = new EntityFallingStar(this.field_70170_p);
            star.func_70107_b(this.starting.func_177958_n(), this.starting.func_177956_o(), this.starting.func_177952_p());
            star.setStartLoc(this.starting.func_177958_n(), this.starting.func_177956_o(), this.starting.func_177952_p());
            star.setCount(this.count + 1);
            star.func_70186_c((this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, -0.1d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, 0.4f, 0.0f);
            star.setThrower(func_85052_h());
            this.field_70170_p.func_72838_d(star);
        }
    }
}
