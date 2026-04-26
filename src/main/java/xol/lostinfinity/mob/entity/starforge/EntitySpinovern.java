package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityRockshot;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntitySpinovern extends EntityFloatingBase implements IMaxAttack {
    public EntitySpinovern(World worldIn) {
        super(worldIn);
        func_70105_a(6.0f, 3.25f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_SPINOVERN_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_SPINOVERN_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_SPINOVERN_AMBIENT;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_SPINOVERN;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return new EntityAIFloatAttack(this, (world1, parent, x, y, z, fireballStrength) -> {
            return new EntityRockshot(this.field_70170_p, parent, x, y, z, 5.0f);
        }, SoundEvents.field_187527_aQ, 20);
    }
}
