package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantWitherSkeleton extends EntityDeviantMob implements IConditionalDamage {
    public EntityDeviantWitherSkeleton(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 4.0f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 10, 8 + (1 * getMutation())).didSuccessfulHit()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 100 + (getMutation() * 200)));
                return true;
            }
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187856_fd;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187864_fh;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187854_fc;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTWITHERSKELETON;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_WITHERSKELETON;
    }
    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return (attacker instanceof EntityPlayer) && func_70032_d(attacker) < 3.0f;
    }
}
