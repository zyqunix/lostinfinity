package xol.lostinfinity.mob.entity.deviant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantSquid.class */
public class EntityDeviantSquid extends EntityFloatingDeviant implements IMaxAttack, IConditionalDamage {
    private int eatTimer;

    public EntityDeviantSquid(World worldIn) {
        super(worldIn);
        this.eatTimer = 0;
        func_70105_a(4.0f, 4.0f);
        this.rawFlySpeed = 0.95f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0d);
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
        if (!this.field_70170_p.field_72995_K) {
            if (this.eatTimer > 0) {
                this.eatTimer--;
            }
            if (func_70638_az() != null) {
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack held = player.func_184586_b(hand);
        if (held.func_77973_b().equals(ItemInit.acidbloodSolution)) {
            if (!this.field_70170_p.field_72995_K) {
                player.func_145747_a(new TextComponentString(TextFmt.Aqua + "The squid swallows the acid, temporarily weakening its husk."));
                this.eatTimer = 100;
            }
            func_184185_a(SoundInit.CREATURE_FEED, 1.5f, 1.0f);
            held.func_190918_g(1);
        }
        super.func_184645_a(player, hand);
        return true;
    }

    public boolean func_70648_aU() {
        return true;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187831_fR;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187833_fS;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187829_fQ;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTSQUID;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_SQUID;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return this.eatTimer > 0;
    }
}
