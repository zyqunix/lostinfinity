package xol.lostinfinity.mob.entity.deviant.titan;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.projectile.entity.EntityDeviantShulkerBullet;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityTitanShulker extends EntityDeviantTitan implements IMaxAttack {
    public EntityTitanShulker(World worldIn) {
        super(worldIn);
        func_70105_a(5.0f, 5.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
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
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % 35 <= 20 && this.field_70173_aa % 4 == 0) {
            boolean did_shot = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (!near_pl.func_184812_l_() && !this.field_70170_p.field_72995_K) {
                    EntityDeviantShulkerBullet shot = new EntityDeviantShulkerBullet(this.field_70170_p, this, func_70638_az());
                    this.field_70170_p.func_72838_d(shot);
                    did_shot = true;
                }
            }
            if (did_shot) {
                func_184185_a(SoundEvents.field_187789_eW, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
            }
        }
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
    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_SHULKER;
        }
        return null;
    }
}
