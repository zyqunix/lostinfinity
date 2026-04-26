package xol.lostinfinity.mob.entity.deviant;

import javax.annotation.Nullable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
import xol.lostinfinity.projectile.entity.EntityDeviantShulkerBullet;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantShulker.class */
public class EntityDeviantShulker extends EntityFloatingDeviant {
    public EntityDeviantShulker(World worldIn) {
        super(worldIn);
        func_184644_a(PathNodeType.WATER, -1.0f);
        func_70105_a(2.5f, 4.5f);
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

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.superMutatedBrew;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.darkConcoction;
    }

    public float func_70047_e() {
        return 0.7f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    @Nullable
    protected EntityAIFloatAttack createShootAI() {
        return new EntityAIFloatAttack(this, (world1, parent, x, y, z, fireballStrength) -> {
            return new EntityDeviantShulkerBullet(this.field_70170_p, parent, func_70638_az());
        }, SoundEvents.field_187789_eW, 10);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public int func_70641_bl() {
        return 1;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187781_eS;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187783_eT;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187773_eO;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTSHULKER;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_SHULKER;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70146_Z.nextInt(20) == 0 && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && (Math.abs(func_180425_c().func_177958_n()) > 800 || Math.abs(func_180425_c().func_177952_p()) > 800);
    }
}
