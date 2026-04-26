package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.activate.ItemGeoCorrelator;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityUnstableMerchant.class */
public class EntityUnstableMerchant extends EntityLiving {
    private int mode;

    public EntityUnstableMerchant(World worldIn) {
        super(worldIn);
        this.mode = 0;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int b) {
        this.mode = b;
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("FindState", getMode());
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setMode(tag.func_74762_e("FindState"));
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack held = player.func_184614_ca();
        if (!this.field_70170_p.field_72995_K && (held.func_77973_b() instanceof ItemGeoCorrelator)) {
            int gameState = getMode();
            if (gameState == 0) {
                if (held.func_77942_o() && held.func_77978_p().func_74762_e("GameState") == 0) {
                    held.func_77978_p().func_74768_a("GameState", 1);
                    doTeleport(held, player);
                    return true;
                }
                return true;
            }
            if (held.func_77942_o() && held.func_77978_p().func_74762_e("GameState") > 0) {
                func_70106_y();
                func_82142_c(true);
                EntityFeralMerchant newMerchant = new EntityFeralMerchant(this.field_70170_p);
                newMerchant.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                int aggromode = held.func_77978_p().func_74762_e("GameState") - 1;
                newMerchant.setMode(aggromode);
                newMerchant.func_70624_b(player);
                this.field_70170_p.func_72838_d(newMerchant);
                func_184185_a(SoundInit.CINEMATIC_WARNING, 1.0f, 1.0f);
                player.func_184611_a(hand, ItemStack.field_190927_a);
                return true;
            }
            return true;
        }
        return true;
    }

    private void doTeleport(ItemStack held, EntityPlayer player) {
        BlockPos telepos = findTeleport();
        held.func_77978_p().func_74780_a("FindX", telepos.func_177958_n());
        held.func_77978_p().func_74780_a("FindY", telepos.func_177956_o());
        held.func_77978_p().func_74780_a("FindZ", telepos.func_177952_p());
        this.field_70170_p.func_175739_a(EnumParticleTypes.PORTAL, this.field_70165_t, this.field_70163_u, this.field_70161_v, 12, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
        player.func_145747_a(new TextComponentString(TextFmt.Gold + "Unstable Merchant: " + TextFmt.Reset + TextFmt.Italic + "Meet me at the new location!"));
        func_184185_a(SoundEvents.field_187791_eX, 1.0f, 1.0f);
        func_70106_y();
        func_82142_c(true);
    }

    private BlockPos findTeleport() {
        double d0 = this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * 400.0d);
        double d2 = this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * 400.0d);
        double d1 = 5 + this.field_70146_Z.nextInt(95);
        return new BlockPos(d0, d1, d2);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187910_gj;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187912_gl;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187911_gk;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0d);
    }
}
