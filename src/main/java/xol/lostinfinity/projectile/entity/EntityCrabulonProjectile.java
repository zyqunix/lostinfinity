package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCrabulonProjectile.class */
public class EntityCrabulonProjectile extends EntityBaseThrowable {
    private static final double SPEED = 0.5d;
    private static final int HOMING_RADIUS = 20;
    private EntityLivingBase target;

    public EntityCrabulonProjectile(World par1World) {
        super(par1World);
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityCrabulonProjectile(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityCrabulonProjectile(World worldIn, double par2, double par4, double par6) {
        super(worldIn, par2, par4, par6);
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (func_85052_h() != null) {
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(1.0d))) {
                if (!target.func_110124_au().equals(func_85052_h().func_110124_au())) {
                    IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() / 2.0f);
                }
            }
        }
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.TESLA_RING_BLUE).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.COSMIC_EXPLOSION, SoundCategory.PLAYERS, 1.5f, 0.6f + (0.4f * this.field_70146_Z.nextFloat()));
        func_70106_y();
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 2) {
                if (this.target == null || this.target.field_70128_L) {
                    EntityLivingBase closest = null;
                    double minDist = 9999.0d;
                    for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(func_180425_c().func_177982_a(-20, -20, -20), func_180425_c().func_177982_a(HOMING_RADIUS, HOMING_RADIUS, HOMING_RADIUS)))) {
                        if (func_85052_h() == null || !func_85052_h().func_110124_au().equals(entity.func_110124_au())) {
                            if (entity instanceof EntityPlayer) {
                                double dist = func_70032_d(entity);
                                if (dist < minDist) {
                                    minDist = dist;
                                    closest = entity;
                                }
                            }
                        }
                    }
                    this.target = closest;
                }
                if (this.target != null) {
                    Vec3d dir = this.target.func_174791_d().func_178787_e(new Vec3d(0.0d, ((double) this.target.field_70131_O) / 1.5d, 0.0d)).func_178788_d(func_174791_d());
                    Vec3d dir2 = dir.func_72432_b();
                    Vec3d motionVec = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                    Vec3d diff = dir2.func_178788_d(motionVec.func_72432_b());
                    this.field_70159_w += diff.field_72450_a * SPEED;
                    this.field_70181_x += diff.field_72448_b * SPEED;
                    this.field_70179_y += diff.field_72449_c * SPEED;
                    this.field_70133_I = true;
                }
                if (this.field_70173_aa > 100) {
                    func_70106_y();
                    return;
                }
                return;
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            this.field_70170_p.func_175682_a(ParticleInit.LARGE_BUBBLE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
