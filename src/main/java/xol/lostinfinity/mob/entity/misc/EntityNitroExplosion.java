package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityParticleTrojan;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityNitroExplosion extends EntityImmaterial implements IMaxAttack {
    private boolean hasExploded;
    private static final int range = 5;
    private int numBlocks;
    private EntityPlayer thrower;
    public void setThrower(EntityPlayer thrower) {
        this.thrower = thrower;
    }
    public EntityNitroExplosion(World worldIn) {
        super(worldIn);
        this.hasExploded = false;
        this.numBlocks = 0;
        this.thrower = null;
        func_70105_a(0.001f, 0.001f);
        func_184224_h(true);
        func_82142_c(true);
        func_189654_d(true);
    }
    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.field_70173_aa >= 3 && this.field_70173_aa <= range) {
            for (int i = 0; i < 3; i++) {
                this.field_70170_p.func_175682_a(ParticleInit.NATURE_RING, true, (this.field_70165_t - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d), this.field_70163_u + this.field_70146_Z.nextDouble(), (this.field_70161_v - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            if (!this.hasExploded && this.field_70173_aa >= 2) {
                func_184185_a(SoundInit.GENERIC_WEAPON_5, 2.0f, 1.0f);
                explosion();
            }
            if (this.field_70173_aa >= 40) {
                func_70106_y();
            }
        }
    }
    private void explosion() {
        this.hasExploded = true;
        for (EntityPlayer entityPlayer : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d))) {
            if (!(entityPlayer instanceof EntityPlayer) || !entityPlayer.equals(this.thrower)) {
                if (!(entityPlayer instanceof EntityParticleTrojan)) {
                    IMaxAttack.dealTrueDamage(this, entityPlayer, (entityPlayer.func_110138_aP() / 20.0f) * this.numBlocks);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.CLAW_MARKS).setSpread(4.0d, 1.0d, 4.0d).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config1, ((EntityLivingBase) entityPlayer).field_70165_t, ((EntityLivingBase) entityPlayer).field_70163_u + ((double) (((EntityLivingBase) entityPlayer).field_70131_O / 2.0f)), ((EntityLivingBase) entityPlayer).field_70161_v);
                }
            }
        }
    }
}
