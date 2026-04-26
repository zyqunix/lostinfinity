package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.boss.EntityAlestria;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityMobComet extends EntityBaseThrowable {
    public EntityMobComet(World par1World) {
        super(par1World);
        func_70105_a(1.0f, 1.0f);
    }
    public EntityMobComet(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityMobComet(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (this.field_70173_aa > 3 && !this.field_70170_p.field_72995_K) {
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                if (!(target instanceof EntityAlestria)) {
                    IMaxAttack.dealMaxHealth(this, target, 3);
                }
            }
            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, false);
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
            this.field_70170_p.func_175688_a(ParticleInit.COMET_BLUE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            this.field_70170_p.func_175688_a(ParticleInit.COMET_WHITE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
