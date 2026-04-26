package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityAsteroid.class */
public class EntityAsteroid extends EntityBaseThrowable {
    public EntityAsteroid(World par1World) {
        super(par1World);
        func_70105_a(6.0f, 6.0f);
    }

    public EntityAsteroid(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(6.0f, 6.0f);
    }

    public EntityAsteroid(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(6.0f, 6.0f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 12 == 0) {
                for (int i = 0; i < 2; i++) {
                    double randYaw = ((this.field_70146_Z.nextDouble() * 3.141592653589793d) / 2.0d) - 0.7853981633974483d;
                    double randPitch = ((this.field_70146_Z.nextDouble() * 3.141592653589793d) / 2.0d) - 0.7853981633974483d;
                    Vec3d dir = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72432_b().func_178789_a((float) randPitch).func_178785_b((float) randYaw);
                    if (this.field_70192_c != null) {
                        EntityMeteor meteor = new EntityMeteor(this.field_70170_p, this.field_70192_c);
                        meteor.setThrower(this.field_70192_c);
                        meteor.func_70107_b(((this.field_70165_t + (dir.field_72450_a * 1.3d)) + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d, (this.field_70163_u + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d, ((this.field_70161_v + (dir.field_72449_c * 1.3d)) + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d);
                        meteor.func_70186_c(dir.field_72450_a, dir.field_72448_b, dir.field_72449_c, 2.8f, 1.0f);
                        this.field_70170_p.func_72838_d(meteor);
                    }
                }
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < 3; i2++) {
            this.field_70170_p.func_175688_a(this.field_70146_Z.nextBoolean() ? ParticleInit.EXPLOSION_RED : ParticleInit.EXPLOSION_ORANGE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            this.field_70170_p.func_175688_a(ParticleInit.FLAME_LARGE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        CustomDamageResult dr;
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                for (EntityMultipleLives entityMultipleLives : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(20.0d))) {
                    if (!entityMultipleLives.func_110124_au().equals(this.field_70192_c.func_110124_au())) {
                        if (func_70032_d(entityMultipleLives) < 6.0f) {
                            dr = IMaxAttack.dealTrueDamage(this, entityMultipleLives, entityMultipleLives.func_110138_aP() * 1.25f);
                        } else {
                            dr = IMaxAttack.dealTrueDamage(this, entityMultipleLives, entityMultipleLives.func_110138_aP() * 0.75f);
                        }
                        if (!dr.wasTargetKilled() && (entityMultipleLives instanceof EntityMultipleLives)) {
                            entityMultipleLives.takeawayNumLives(8);
                        }
                    }
                }
                func_184185_a(SoundInit.ASTEROID_IMPACT, 2.0f, 0.5f + (0.7f * this.field_70146_Z.nextFloat()));
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.setCount(15);
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_RED).setSpread(25.0d, 4.0d, 25.0d).setIgnoreRange(true);
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_ORANGE).setSpread(25.0d, 4.0d, 25.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
