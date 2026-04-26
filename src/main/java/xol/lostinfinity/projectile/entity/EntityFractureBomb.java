package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityFractureBomb.class */
public class EntityFractureBomb extends EntityBaseThrowable {
    public EntityFractureBomb(World par1World) {
        super(par1World);
    }

    public EntityFractureBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityFractureBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                if (result.field_72308_g == null) {
                    double x0 = this.field_70165_t;
                    double y0 = this.field_70163_u + 0.5d;
                    double z0 = this.field_70161_v;
                    float f = 0.0f;
                    while (true) {
                        float angle = f;
                        if (angle > 6.283185307179586d) {
                            break;
                        }
                        EntityGenericBomb shot = new EntityGenericBomb(this.field_70170_p);
                        shot.func_70107_b(x0, y0, z0);
                        double velocity_x = 0.05999999865889549d * 5.0d * Math.cos(angle);
                        double velocity_z = 0.05999999865889549d * 5.0d * Math.sin(angle);
                        shot.setThrower(func_85052_h());
                        shot.calculateVelocity(velocity_x, 0.800000011920929d, velocity_z);
                        this.field_70170_p.func_72838_d(shot);
                        f = (float) (((double) angle) + 0.39269908169872414d);
                    }
                    func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 1.0f);
                } else if (result.field_72308_g instanceof EntityLivingBase) {
                    IMaxAttack.dealMaxHealth(func_85052_h(), result.field_72308_g, 4);
                }
            }
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int k = 0; k < 2; k++) {
                this.field_70170_p.func_175688_a(ParticleInit.CRESCENT_MOON, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
        }
    }

    protected float func_70185_h() {
        return 0.05f;
    }
}
