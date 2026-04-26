package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityBaseThrowable.class */
public class EntityBaseThrowable extends EntityThrowable implements IMaxAttack {
    private EntityLivingBase secondarThrower;

    public EntityBaseThrowable(World worldIn) {
        super(worldIn);
    }

    public EntityBaseThrowable(World worldIn, EntityLivingBase entityIn) {
        super(worldIn, entityIn);
    }

    public EntityBaseThrowable(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityBaseThrowable(World par1World, EntityLivingBase thrower, double par2, double par4, double par6, float speedMulti) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
        setThrower(thrower);
        calculateTrajectory(thrower, par2, par4, par6, speedMulti);
    }

    public void setThrower(EntityLivingBase throwset) {
        this.field_70192_c = throwset;
    }

    public void setSecondaryThrower(EntityLivingBase entity) {
        this.secondarThrower = entity;
    }

    public EntityLivingBase getSecondaryThrower() {
        return this.secondarThrower;
    }

    protected void calculateTrajectory(EntityLivingBase shooter, double accelX, double accelY, double accelZ, float speedMulti) {
        func_70012_b(shooter.field_70165_t, shooter.field_70163_u, shooter.field_70161_v, shooter.field_70177_z, shooter.field_70125_A);
        func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70159_w = 0.0d;
        this.field_70181_x = 0.0d;
        this.field_70179_y = 0.0d;
        double accelX2 = accelX + (this.field_70146_Z.nextGaussian() * 0.4d);
        double accelY2 = accelY + (this.field_70146_Z.nextGaussian() * 0.4d);
        double accelZ2 = accelZ + (this.field_70146_Z.nextGaussian() * 0.4d);
        double d0 = MathHelper.func_76133_a((accelX2 * accelX2) + (accelY2 * accelY2) + (accelZ2 * accelZ2));
        this.field_70159_w = ((((double) speedMulti) * accelX2) / d0) * 0.1d;
        this.field_70181_x = ((((double) speedMulti) * accelY2) / d0) * 0.1d;
        this.field_70179_y = ((((double) speedMulti) * accelZ2) / d0) * 0.1d;
        this.field_70133_I = true;
    }

    protected void func_70184_a(RayTraceResult result) {
        func_70106_y();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa == 300 && willDespawn()) {
            func_70106_y();
        }
    }

    protected boolean willDespawn() {
        return true;
    }

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

    public void shootNoVel(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
        float f = (-MathHelper.func_76126_a(rotationYawIn * 0.017453292f)) * MathHelper.func_76134_b(rotationPitchIn * 0.017453292f);
        float f1 = -MathHelper.func_76126_a((rotationPitchIn + pitchOffset) * 0.017453292f);
        float f2 = MathHelper.func_76134_b(rotationYawIn * 0.017453292f) * MathHelper.func_76134_b(rotationPitchIn * 0.017453292f);
        func_70186_c(f, f1, f2, velocity, inaccuracy);
    }

    protected double getROD(int multi) {
        return ((-0.5d) + this.field_70146_Z.nextDouble()) * ((double) multi);
    }
}
