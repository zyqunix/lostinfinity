package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityFirePellet extends EntityBaseThrowable {
    public EntityFirePellet(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityFirePellet(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityFirePellet(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.FLAME_MEDIUM).setSpread(1.0d, 1.0d, 1.0d).setCount(9).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v);
            func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(2.0d, 2.0d, 2.0d))) {
                if (IMaxAttack.dealMaxHealth(this, target, 2).didSuccessfulHit()) {
                    target.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200));
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.2f;
    }
}
