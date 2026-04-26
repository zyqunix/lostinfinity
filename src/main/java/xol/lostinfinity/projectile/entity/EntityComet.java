package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityComet extends EntityBaseThrowable {
    private int count;
    private BlockPos starting;
    public EntityComet(World par1World) {
        super(par1World);
        this.count = 0;
        func_70105_a(1.0f, 1.0f);
    }
    public EntityComet(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.count = 0;
        func_70105_a(1.0f, 1.0f);
    }
    public EntityComet(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.count = 0;
        func_70105_a(1.0f, 1.0f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        this.field_70192_c = throwset;
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
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                int multi = 2;
                if (target instanceof EntityPlayer) {
                    multi = 3;
                }
                IMaxAttack.dealMaxHealth((Entity) this, target, 4, multi);
            }
            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, false);
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        if (func_189652_ae()) {
            return 0.0f;
        }
        return 0.0366f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(ParticleInit.COMET_BLUE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            this.field_70170_p.func_175688_a(ParticleInit.COMET_WHITE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            return;
        }
        if (this.field_70173_aa == 10 && this.count < 19 && this.starting != null) {
            EntityComet star = new EntityComet(this.field_70170_p);
            star.func_70107_b(this.starting.func_177958_n(), this.starting.func_177956_o(), this.starting.func_177952_p());
            star.setStartLoc(this.starting.func_177958_n(), this.starting.func_177956_o(), this.starting.func_177952_p());
            star.setCount(this.count + 1);
            star.func_70186_c((this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, -0.1d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, 0.4f, 0.0f);
            star.setThrower(func_85052_h());
            this.field_70170_p.func_72838_d(star);
            this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, star.field_70165_t, star.field_70163_u, star.field_70161_v, 2, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
        }
    }
}
