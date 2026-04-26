package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntitySunstoneRock.class */
public class EntitySunstoneRock extends EntityBaseThrowable {
    public EntitySunstoneRock(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntitySunstoneRock(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    public EntitySunstoneRock(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g == null) {
                BlockPos resultPos = result.func_178782_a();
                if (this.field_70170_p.func_180495_p(resultPos).func_177230_c() != BlockInit.sunstoneOre) {
                    BlockPos prev = func_180425_c();
                    if (this.field_70170_p.func_175623_d(prev)) {
                        this.field_70170_p.func_175656_a(prev, BlockInit.sunstoneOre.func_176223_P());
                        if (func_85052_h() != null) {
                            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(20.0d))) {
                                if (!target.func_110124_au().equals(this.field_70192_c.func_110124_au())) {
                                    IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.75f);
                                }
                            }
                            CustomParticleConfig config1 = new CustomParticleConfig();
                            config1.setCount(5);
                            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RED).setSpread(5.0d, 2.0d, 5.0d).setIgnoreRange(true);
                            config1.createInstance().setParticle(ParticleInit.EXPLOSION_ORANGE).setSpread(5.0d, 2.0d, 5.0d).setIgnoreRange(true);
                            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                        }
                    }
                }
            } else if (result.field_72308_g instanceof EntityLivingBase) {
                EntityLivingBase targetHit = result.field_72308_g;
                IMaxAttack.dealMaxHealth(this, targetHit, 1);
                if (func_85052_h() != null) {
                    for (EntityLivingBase target2 : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(20.0d))) {
                        if (!target2.func_110124_au().equals(this.field_70192_c.func_110124_au())) {
                            IMaxAttack.dealTrueDamage(this, target2, target2.func_110138_aP() * 0.75f);
                        }
                    }
                    CustomParticleConfig config12 = new CustomParticleConfig();
                    config12.setCount(5);
                    config12.createInstance().setParticle(ParticleInit.EXPLOSION_RED).setSpread(5.0d, 2.0d, 5.0d).setIgnoreRange(true);
                    config12.createInstance().setParticle(ParticleInit.EXPLOSION_ORANGE).setSpread(5.0d, 2.0d, 5.0d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config12, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
