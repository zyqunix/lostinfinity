package xol.lostinfinity.projectile.entity;

import java.util.Arrays;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityAstralShot.class */
public class EntityAstralShot extends EntityBaseThrowable {
    private double speed;
    private EntityLivingBase target;
    private static final int homingRadius = 20;

    public EntityAstralShot(World par1World) {
        super(par1World);
        this.speed = 0.1d;
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityAstralShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.speed = 0.1d;
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityAstralShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.speed = 0.1d;
        this.target = null;
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(3.0d))) {
                    if (!target.func_110124_au().equals(func_85052_h().func_110124_au()) && IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP(), Arrays.asList("Darkborn")).didSuccessfulHit()) {
                        target.func_70690_d(new PotionEffect(PotionInit.DISTORTION, 300));
                    }
                }
            }
            CustomParticleConfig config = new CustomParticleConfig();
            config.setCount(5);
            config.createInstance().setParticle(ParticleInit.PRISMATIC_EXPLOSION_TYPE1).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
            config.createInstance().setParticle(ParticleInit.PRISMATIC_EXPLOSION_TYPE2).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
            config.createInstance().setParticle(ParticleInit.PRISMATIC_EXPLOSION_TYPE3).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_6, SoundCategory.PLAYERS, 1.5f, 0.6f + (0.4f * this.field_70146_Z.nextFloat()));
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 2) {
                if (this.target == null || this.target.field_70128_L) {
                    EntityLivingBase closest = null;
                    double minDist = 9999.0d;
                    for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(func_180425_c().func_177982_a(-20, -20, -20), func_180425_c().func_177982_a(homingRadius, homingRadius, homingRadius)))) {
                        if (func_85052_h() == null || !func_85052_h().func_110124_au().equals(entity.func_110124_au())) {
                            double dist = func_70032_d(entity);
                            if (dist < minDist) {
                                minDist = dist;
                                closest = entity;
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
                    this.field_70159_w += diff.field_72450_a * this.speed;
                    this.field_70181_x += diff.field_72448_b * this.speed;
                    this.field_70179_y += diff.field_72449_c * this.speed;
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
        for (int i = 0; i < 3; i++) {
            switch (MathHelper.func_76141_d((this.field_70173_aa % 30) / 3)) {
                case 0:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_ACID, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 1:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_GREEN, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 2:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_AQUA, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 3:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_BLUE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_PURPLE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 5:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_PINK, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_RED, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 7:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_TANGERINE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 8:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_ORANGE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 9:
                    this.field_70170_p.func_175682_a(ParticleInit.GENERIC_DOT_YELLOW, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
            }
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
