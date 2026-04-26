package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityTerrorFly extends EntityFloatingBase implements IMaxAttack {
    public EntityTerrorFly(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 1.0f);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 1);
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
            if (func_70685_l(func_70638_az())) {
                target.func_70024_g(Math.signum(this.field_70165_t - target.field_70165_t) * 0.02d, Math.signum(this.field_70163_u - target.field_70163_u) * 0.02d, Math.signum(this.field_70161_v - target.field_70161_v) * 0.02d);
                target.field_70133_I = true;
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.TERROR_FLY_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.TERROR_FLY_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.TERROR_FLY_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 4;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_TERRORFLY;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
