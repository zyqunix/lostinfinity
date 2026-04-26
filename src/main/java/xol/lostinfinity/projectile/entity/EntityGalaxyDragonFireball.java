package xol.lostinfinity.projectile.entity;

import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.client.fx.ClientParticleRenderer;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityGalaxyDragonFireball.class */
public class EntityGalaxyDragonFireball extends EntityBaseThrowable {
    public EntityGalaxyDragonFireball(World worldIn) {
        super(worldIn);
        func_70105_a(0.25f, 0.25f);
    }

    public EntityGalaxyDragonFireball(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        func_70105_a(0.25f, 0.25f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type[result.field_72313_a.ordinal()]) {
            case 1:
                func_70106_y();
                break;
            case 2:
                EntityLivingBase entityLivingBase = result.field_72308_g;
                if (entityLivingBase != null) {
                    if (entityLivingBase == this.field_70192_c || entityLivingBase == getSecondaryThrower()) {
                        return;
                    }
                    func_70106_y();
                }
                break;
        }
        List<Entity> entityList = this.field_70170_p.func_175647_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(5.0d), input -> {
            return !(input instanceof EntityImmaterial);
        });
        for (Entity entity : entityList) {
            if (entity != this.field_70192_c && entity != getSecondaryThrower()) {
                IMaxAttack.dealTrueDamage(this, (EntityLivingBase) entity, ((EntityLivingBase) entity).func_110138_aP() * 0.5f, Collections.singletonList("Aquatic"));
            }
        }
        CustomParticleConfig config = new CustomParticleConfig();
        config.setCount(10);
        config.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE1).setSpread(10.0d, 10.0d, 10.0d).setIgnoreRange(true);
        config.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE2).setSpread(10.0d, 10.0d, 10.0d).setIgnoreRange(true);
        config.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE3).setSpread(10.0d, 10.0d, 10.0d).setIgnoreRange(true);
        config.createInstance().setParticle(ParticleInit.COSMIC_EXPLOSION_TYPE4).setSpread(10.0d, 10.0d, 10.0d).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config, func_174791_d());
    }

    /* JADX INFO: renamed from: xol.lostinfinity.projectile.entity.EntityGalaxyDragonFireball$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityGalaxyDragonFireball$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$math$RayTraceResult$Type = new int[RayTraceResult.Type.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$util$math$RayTraceResult$Type[RayTraceResult.Type.BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$math$RayTraceResult$Type[RayTraceResult.Type.ENTITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        double mX = this.field_70159_w;
        double mY = this.field_70181_x;
        double mZ = this.field_70179_y;
        super.func_70071_h_();
        this.field_70159_w = mX;
        this.field_70181_x = mY;
        this.field_70179_y = mZ;
        if (this.field_70170_p.field_72995_K) {
            CustomParticleConfig config = new CustomParticleConfig();
            config.setOrigin(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            config.createInstance().setParticle(ParticleInit.FLAME_LARGE).setCount(10).setSpread(0.5d, 0.5d, 0.5d).setIgnoreRange(true);
            ClientParticleRenderer.renderComplex(config);
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
