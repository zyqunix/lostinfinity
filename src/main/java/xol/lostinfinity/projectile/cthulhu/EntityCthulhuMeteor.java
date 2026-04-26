package xol.lostinfinity.projectile.cthulhu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.client.fx.ClientParticleRenderer;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityCthulhuMeteor extends EntityBaseThrowable {
    public EntityCthulhuMeteor(World worldIn) {
        super(worldIn);
        func_70105_a(6.0f, 6.0f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
            if (this.field_70170_p.field_72995_K) {
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.setOrigin(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                config1.setCount(15);
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_RED).setSpread(25.0d, 4.0d, 25.0d).setIgnoreRange(true);
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_ORANGE).setSpread(25.0d, 4.0d, 25.0d).setIgnoreRange(true);
                ClientParticleRenderer.renderComplex(config1);
                return;
            }
            func_184185_a(SoundInit.ASTEROID_IMPACT, 2.0f, 0.5f + (0.7f * this.field_70146_Z.nextFloat()));
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70163_u < 0.0d) {
            func_70106_y();
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 3; i++) {
                this.field_70170_p.func_175688_a(this.field_70146_Z.nextBoolean() ? ParticleInit.COSMIC_EXPLOSION_TYPE2 : ParticleInit.COSMIC_EXPLOSION_TYPE4, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
                this.field_70170_p.func_175688_a(this.field_70146_Z.nextBoolean() ? ParticleInit.GENERIC_DOT_GREEN : ParticleInit.GENERIC_DOT_PURPLE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
            return;
        }
        if (this.field_70173_aa % 5 == 0) {
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(4.0d))) {
                if (!(target instanceof ICthulhuMinion) && !(target instanceof EntityCthulhu) && target != func_85052_h() && target != getSecondaryThrower()) {
                    IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.1f);
                }
            }
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
