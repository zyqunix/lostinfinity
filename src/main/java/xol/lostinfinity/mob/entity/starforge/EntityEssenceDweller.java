package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityEssenceDweller extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    public EntityEssenceDweller(World worldIn) {
        super(worldIn);
        func_70105_a(1.2f, 2.2f);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.ESSENCE_DWELLER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.ESSENCE_DWELLER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.ESSENCE_DWELLER_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
