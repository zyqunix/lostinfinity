package xol.lostinfinity.projectile.entity;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityBlightBomb extends EntityBaseThrowable {
    public EntityBlightBomb(World par1World) {
        super(par1World);
        func_70105_a(0.25f, 0.25f);
    }
    public EntityBlightBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityBlightBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        CustomDamageResult dr;
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                float count = 0.0f;
                Iterator it = this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(12.0d)).iterator();
                while (it.hasNext()) {
                    if (((EntityLivingBase) it.next()).func_70644_a(PotionInit.BLIGHTED)) {
                        count += 1.0f;
                    }
                }
                for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(12.0d))) {
                    if (!target.func_110124_au().equals(this.field_70192_c.func_110124_au()) && !target.equals(getSecondaryThrower())) {
                        if (count >= 5.0f) {
                            dr = IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.5f);
                        } else {
                            dr = IMaxAttack.dealMaxHealth(this, target, 2);
                        }
                        if (!dr.wasTargetKilled() && dr.didSuccessfulHit()) {
                            target.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 300, 9));
                        }
                    }
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.VENOM_RING).setSpread(15.0d, 2.0d, 15.0d).setCount(4).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.EXPLOSION_RING_DARK).setSpread(15.0d, 2.0d, 15.0d).setCount(4).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                func_184185_a(SoundInit.GENERIC_WEAPON_10, 2.0f, 0.5f + (0.7f * this.field_70146_Z.nextFloat()));
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.03f;
    }
}
