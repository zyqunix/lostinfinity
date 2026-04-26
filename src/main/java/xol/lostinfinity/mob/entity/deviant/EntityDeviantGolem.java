package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantGolem extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantGolem(World worldIn) {
        super(worldIn);
        func_70105_a(2.5f, 4.75f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (IMaxAttack.dealMaxHealth(this, func_70638_az(), 2).didSuccessfulHit()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 200, 1 + getMutation()));
                return true;
            }
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.shadowHide;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.ironHide;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187599_cE;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187602_cF;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected void func_180429_a(BlockPos pos, Block blockIn) {
        func_184185_a(SoundEvents.field_187605_cG, 1.0f, 1.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTGOLEM;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_GOLEM;
    }
}
