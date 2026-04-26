package xol.lostinfinity.mob.entity.misc;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityStormBomb extends EntityImmaterial implements IMaxAttack {
    private UUID creator_UUID;
    public EntityStormBomb(World worldIn) {
        super(worldIn);
        this.creator_UUID = null;
        func_70105_a(0.25f, 0.25f);
        func_189654_d(false);
    }
    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa >= 3 && !this.field_70128_L) {
            explosionEffect();
        }
    }
    public void explosionEffect() {
        if (this.creator_UUID != null) {
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.setCount(3);
            config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_BLUE).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
            config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_YELLOW).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.GENERIC_WEAPON_13, 1.0f, 0.7f + (0.6f * this.field_70146_Z.nextFloat()));
            for (IEntityOwnable iEntityOwnable : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(5.0d, 3.0d, 5.0d))) {
                if (!iEntityOwnable.func_110124_au().equals(this.creator_UUID) && (!(iEntityOwnable instanceof IEntityOwnable) || iEntityOwnable.func_184753_b() == null || !iEntityOwnable.func_184753_b().equals(this.creator_UUID))) {
                    IMaxAttack.dealTrueDamage(this, iEntityOwnable, iEntityOwnable.func_110138_aP() * 0.5f);
                }
            }
        }
        func_70106_y();
    }
}
