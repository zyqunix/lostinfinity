package xol.lostinfinity.mob.entity.deviant;

import javax.annotation.Nullable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.init.Items;
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
import xol.lostinfinity.projectile.entity.EntityDeviantFireball;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantGhast.class */
public class EntityDeviantGhast extends EntityFloatingDeviant {
    public EntityDeviantGhast(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 4.8f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    @Nullable
    protected EntityAIFloatAttack createShootAI() {
        return new EntityAIFloatAttack(this, (world1, parent, x, y, z, fireballStrength) -> {
            return new EntityDeviantFireball(this.field_70170_p, parent, x, y, z, 8, 4.0f);
        }, SoundEvents.field_187606_E, 8);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public void updateSupermutationAI() {
        for (EntityAITasks.EntityAITaskEntry task : this.field_70714_bg.field_75782_a) {
            EntityAIBase ai = task.field_75733_a;
            if (ai instanceof EntityAIFloatAttack) {
                ((EntityAIFloatAttack) ai).updateDelay(10 - (2 * getMutation()));
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
    }

    public float func_70047_e() {
        return 1.5f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return Items.field_151131_as;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.smolderingGhastTear;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        if (source == DamageSource.field_76371_c || source == DamageSource.field_76370_b || source == DamageSource.field_76372_a) {
            return false;
        }
        return super.func_70097_a(source, amount);
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187553_bI;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187555_bJ;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187551_bH;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return super.func_70601_bi() && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTGHAST;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_GHAST;
    }
}
