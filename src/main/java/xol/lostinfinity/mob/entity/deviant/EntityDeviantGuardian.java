package xol.lostinfinity.mob.entity.deviant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantGuardian.class */
public class EntityDeviantGuardian extends EntityFloatingDeviant implements IMaxAttack, IConditionalDamage {
    public EntityDeviantGuardian(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 2.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4 - getMutation());
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
        }
    }

    public boolean func_70648_aU() {
        return true;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187678_ce;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187687_ch;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187670_cb;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTGUARDIAN;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_GUARDIAN;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return func_70090_H() && attacker.func_70090_H();
    }
}
