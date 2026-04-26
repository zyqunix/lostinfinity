package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.deviant.prime.EntityLivorax;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityLivoraxBullet extends EntityShulkerBullet implements IMaxAttack {
    public EntityLivoraxBullet(World worldIn) {
        super(worldIn);
    }
    public EntityLivoraxBullet(World worldIn, EntityLivingBase ownerIn, Entity targetIn) {
        super(worldIn, ownerIn, targetIn, EnumFacing.Axis.X);
    }
    protected void func_184567_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 1);
            }
            func_70106_y();
        }
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70159_w *= 1.2000000476837158d;
        this.field_70181_x *= 1.2000000476837158d;
        this.field_70179_y *= 1.2000000476837158d;
        this.field_70133_I = true;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa >= 100 && !this.field_70128_L) {
                for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(2.0d, 2.0d, 2.0d))) {
                    if (!(target instanceof EntityLivorax)) {
                        IMaxAttack.dealMaxHealth(this, target, 1);
                    }
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                func_70106_y();
                return;
            }
            return;
        }
        this.field_70170_p.func_175688_a(ParticleInit.COMET_BLUE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
    }
}
