package xol.lostinfinity.projectile.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityParticleTrojan;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityFearBomb.class */
public class EntityFearBomb extends EntityBaseThrowable {
    private boolean splitting;

    public EntityFearBomb(World par1World) {
        super(par1World);
        this.splitting = false;
    }

    public EntityFearBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.splitting = false;
    }

    public EntityFearBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.splitting = false;
    }

    public void setSplitting() {
        this.splitting = true;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            UUID thrower_uuid = null;
            if (func_85052_h() != null) {
                thrower_uuid = func_85052_h().func_110124_au();
            }
            List<EntityLivingBase> caughtCreatures = this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d));
            List<EntityLivingBase> damageableCreatures = new ArrayList<>();
            for (EntityLivingBase creature : caughtCreatures) {
                if (!(creature instanceof EntityParticleTrojan) && !creature.equals(func_85052_h())) {
                    damageableCreatures.add(creature);
                }
            }
            for (EntityLivingBase target : damageableCreatures) {
                if (!target.func_110124_au().equals(thrower_uuid)) {
                    IMaxAttack.dealMaxHealth((Entity) this, target, 10, 4 + (3 * (damageableCreatures.size() - 1)));
                }
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.05f;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 2; i++) {
                this.field_70170_p.func_175688_a(ParticleInit.FLAME_LARGE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
            return;
        }
        if (!this.field_70128_L && this.splitting && this.field_70173_aa >= 15 && func_85052_h() != null) {
            for (int i2 = 0; i2 < 12; i2++) {
                EntityFearBomb split = new EntityFearBomb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                split.setThrower(func_85052_h());
                split.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5f, 20.0f);
                this.field_70170_p.func_72838_d(split);
            }
            func_184185_a(SoundInit.GENERIC_POP, 1.5f, 1.0f);
            func_70106_y();
        }
    }
}
