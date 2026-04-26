package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityFeralMerchant.class */
public class EntityFeralMerchant extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private int mode;

    public EntityFeralMerchant(World worldIn) {
        super(worldIn);
        this.mode = 0;
        func_70105_a(1.2f, 2.2f);
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int b) {
        this.mode = b;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("FindState", getMode());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setMode(tag.func_74762_e("FindState"));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % (80 - (15 * getMode())) == 0 && func_70638_az() != null) {
            func_70634_a(func_70638_az().field_70165_t, func_70638_az().field_70163_u, func_70638_az().field_70161_v);
            func_184185_a(SoundInit.BIG_WARP, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
        }
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 3, 1 + getMode());
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.FERAL_MERCHANT_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.FERAL_MERCHANT_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.FERAL_MERCHANT_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 4 + (4 * getMode());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            if (getMode() == 2) {
                func_145779_a(ItemInit.warpedDisc, 1);
                return;
            }
            ItemStack newCorrelator = new ItemStack(ItemInit.geocorrelator);
            newCorrelator.func_77982_d(new NBTTagCompound());
            newCorrelator.func_77978_p().func_74768_a("GameState", getMode() + 2);
            doTeleport(newCorrelator);
            EntityItem itemEntity = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, newCorrelator);
            itemEntity.field_70159_w = 0.0d;
            itemEntity.field_70181_x = 0.0d;
            itemEntity.field_70179_y = 0.0d;
            this.field_70170_p.func_72838_d(itemEntity);
        }
    }

    private BlockPos findTeleport() {
        double d0 = this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * 400.0d);
        double d2 = this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * 400.0d);
        double d1 = 5 + this.field_70146_Z.nextInt(95);
        return new BlockPos(d0, d1, d2);
    }

    private void doTeleport(ItemStack held) {
        BlockPos telepos = findTeleport();
        held.func_77978_p().func_74780_a("FindX", telepos.func_177958_n());
        held.func_77978_p().func_74780_a("FindY", telepos.func_177956_o());
        held.func_77978_p().func_74780_a("FindZ", telepos.func_177952_p());
    }
}
