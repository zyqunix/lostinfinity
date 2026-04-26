package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantSlimeStrider extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantSlimeStrider(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 0.5f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.378d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187874_fm;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187880_fp;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187886_fs;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.deviantMilk;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.superStimulant;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -0.5f;
        float scl = 1.8f + (0.5f * MathHelper.func_76126_a(this.field_70173_aa * 0.05f));
        func_70105_a(1.1f + scl, 0.2f + scl);
        if (this.field_70171_ac && func_70638_az() != null) {
            if (func_70638_az().field_70163_u > this.field_70163_u) {
                this.field_70181_x = 0.25d;
                this.field_70133_I = true;
            }
            float motionCap = 1.5f + (getMutation() * 0.4f);
            if (this.field_70159_w > (-motionCap) && this.field_70159_w < motionCap) {
                this.field_70159_w *= 1.2999999523162842d;
                this.field_70133_I = true;
            }
            if (this.field_70179_y > (-motionCap) && this.field_70179_y < motionCap) {
                this.field_70179_y *= 1.2999999523162842d;
                this.field_70133_I = true;
            }
        }
    }
    public boolean func_70648_aU() {
        return true;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTSLIMESTRIDER;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_SLIMESTRIDER;
    }
}
