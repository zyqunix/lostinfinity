package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityLurcher extends EntityFloatingBase implements IMaxAttack {
    public EntityLurcher(World worldIn) {
        super(worldIn);
        func_70105_a(1.2f, 0.8f);
        this.rawFlySpeed = 0.85f;
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (IMaxAttack.dealMaxHealth(this, func_70638_az(), 2).didSuccessfulHit()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.SHATTERED, 120));
                return true;
            }
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
            if (this.field_70173_aa % 80 == 0) {
                func_70024_g((target.field_70165_t - this.field_70165_t) * 0.1d, (target.field_70163_u - this.field_70163_u) * 0.1d, (target.field_70161_v - this.field_70161_v) * 0.1d);
                this.field_70133_I = true;
                func_184185_a(SoundInit.LURCHER_ABILITY, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
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
        return SoundInit.LURCHER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LURCHER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.LURCHER_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 10;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_LURCHER;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
