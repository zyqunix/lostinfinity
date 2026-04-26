package xol.lostinfinity.mob.entity.deviant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.projectile.entity.EntityDeviantFireball;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantBlaze.class */
public class EntityDeviantBlaze extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantBlaze(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 6.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0d));
        this.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(this, 1.0d, 0.0f));
        this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(600.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4 - (getMutation() > 0 ? 2 : 0));
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % (80 - (10 * getMutation())) <= 20 && this.field_70173_aa % 10 == 0) {
            boolean did_shot = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(30.0d, 30.0d, 30.0d))) {
                if (!near_pl.func_184812_l_() && !this.field_70170_p.field_72995_K) {
                    func_70676_i(1.0f);
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityDeviantFireball shot = new EntityDeviantFireball(this.field_70170_p, this, d2, d3, d4, 4, 0.5f);
                    shot.field_70165_t = makeX;
                    shot.field_70163_u = makeY;
                    shot.field_70161_v = makeZ;
                    this.field_70170_p.func_72838_d(shot);
                    did_shot = true;
                }
            }
            if (did_shot) {
                func_184185_a(SoundEvents.field_187606_E, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187600_C;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187603_D;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187594_A;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.organicCalcium;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.prismaticCalcium;
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

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTBLAZE;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_BLAZE;
    }
}
