package xol.lostinfinity.mob.entity.misc;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.boss.EntityThundyron;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityThunderBomb extends EntityImmaterial implements IMaxAttack {
    private UUID creator_UUID;
    public EntityThunderBomb(World worldIn) {
        super(worldIn);
        func_70105_a(0.5f, 0.5f);
        func_189654_d(false);
    }
    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa >= 100 && !this.field_70128_L) {
            explosionEffect();
        }
    }
    public void explosionEffect() {
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.setCount(3);
        config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_BLUE).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
        config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_YELLOW).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        func_184185_a(SoundInit.GENERIC_WEAPON_5, 4.0f, 1.0f);
        for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(3.0d, 3.0d, 3.0d))) {
            if (!(target instanceof EntityThunderBomb) && !(target instanceof EntityThundyron)) {
                IMaxAttack.dealMaxHealth(this, target, 2);
            }
        }
        func_70106_y();
    }
}
