package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntitySandAttack extends EntityImmaterial implements IMaxAttack {
    private float size;
    public EntitySandAttack(World worldIn) {
        super(worldIn);
        this.size = 0.1f;
        func_70105_a(0.1f, 0.1f);
    }
    public float getCubeSize() {
        return this.size;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70181_x -= 0.03d;
        if (this.size < 8.0f) {
            this.size += 0.1f;
            func_70105_a(this.size, this.size);
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 300) {
            func_70106_y();
        }
    }
    public void func_180430_e(float distance, float damageMultiplier) {
        if (!this.field_70170_p.field_72995_K) {
            func_70106_y();
            this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.size, false);
            func_184185_a(SoundInit.COSMIC_EXPLOSION, 2.0f, 0.5f + (0.7f * this.field_70146_Z.nextFloat()));
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.setCount(5 + (2 * MathHelper.func_76141_d(this.size)));
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_YELLOW).setSpread(this.size, this.size / 4.0f, this.size).setIgnoreRange(true);
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_ORANGE).setSpread(this.size, this.size / 4.0f, this.size).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(this.size))) {
                IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.5f);
            }
        }
    }
}
