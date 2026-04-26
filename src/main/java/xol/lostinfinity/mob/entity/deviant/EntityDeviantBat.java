package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
import xol.lostinfinity.projectile.entity.EntityEchoBlast;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantBat extends EntityFloatingDeviant implements IMaxAttack {
    public EntityDeviantBat(World worldIn) {
        super(worldIn);
        func_70105_a(1.6f, 1.8f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(800.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4 - getMutation());
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.heavyCrystal;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.nightglazedCrystal;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187742_x;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187743_y;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187740_w;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected boolean func_70692_ba() {
        int time = (int) (this.field_70170_p.func_72820_D() % 24000);
        return time > 13000 && time < 18000;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public int func_70641_bl() {
        return 1;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTBAT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_BAT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    protected EntityAIFloatAttack createShootAI() {
        return new EntityAIFloatAttack(this, (world1, parent, x, y, z, fireballStrength) -> {
            return new EntityEchoBlast(this.field_70170_p, parent, x, y, z, 5.0f, 2 + (2 * getMutation()));
        }, SoundEvents.field_187744_z, 20);
    }
}
