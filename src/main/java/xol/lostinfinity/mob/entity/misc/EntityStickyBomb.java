package xol.lostinfinity.mob.entity.misc;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityStickyBomb extends EntityImmaterial implements IMaxAttack {
    private UUID creator_UUID;
    private EntityLivingBase holdEntity;
    private BlockPos holdPos;
    public EntityStickyBomb(World worldIn) {
        super(worldIn);
        this.holdEntity = null;
        this.holdPos = null;
        func_70105_a(0.25f, 0.25f);
    }
    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }
    public void setHoldPos(BlockPos pos) {
        this.holdPos = pos;
    }
    public void setHoldPos(EntityLivingBase entity) {
        this.holdEntity = entity;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 1) {
            if (this.creator_UUID == null) {
                func_70106_y();
                return;
            }
            if (this.holdEntity != null) {
                if (this.holdEntity.field_70128_L) {
                    explosionEffect();
                    return;
                } else {
                    func_70634_a(this.holdEntity.field_70165_t, this.holdEntity.field_70163_u + ((double) this.holdEntity.field_70131_O), this.holdEntity.field_70161_v);
                    return;
                }
            }
            if (this.field_70170_p.func_175623_d(this.holdPos)) {
                explosionEffect();
            }
        }
    }
    public void explosionEffect() {
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.EXPLOSION).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        func_184185_a(SoundInit.GENERIC_WEAPON_5, 4.0f, 1.0f);
        for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(6.0d, 6.0d, 6.0d))) {
            if (!target.func_110124_au().equals(this.creator_UUID) && !target.func_110124_au().equals(func_110124_au())) {
                IMaxAttack.dealMaxHealth(this, target, 2);
            }
        }
        func_70106_y();
    }
}
