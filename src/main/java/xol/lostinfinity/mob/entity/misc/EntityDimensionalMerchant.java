package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityDimensionalMerchant.class */
public class EntityDimensionalMerchant extends EntityLiving {
    public EntityDimensionalMerchant(World worldIn) {
        super(worldIn);
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

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.func_184586_b(hand);
        if (itemstack.func_77973_b() == ItemInit.deviantEgg || itemstack.func_77973_b() == ItemInit.deviantBeef || itemstack.func_77973_b() == ItemInit.deviantMilk || itemstack.func_77973_b() == ItemInit.deviantPorkchop) {
            itemstack.func_190918_g(1);
            if (itemstack.func_190926_b()) {
                player.func_184611_a(hand, new ItemStack(getRandomTrade()));
            } else if (!player.field_70170_p.field_72995_K) {
                func_145779_a(getRandomTrade(), 1);
            }
            player.func_184185_a(SoundEvents.field_187915_go, 1.0f, 1.0f);
            return true;
        }
        return false;
    }

    private Item getMapFromForm() {
        return ItemInit.starlitGlobe;
    }

    private Item getRandomTrade() {
        int pick = this.field_70146_Z.nextInt(101);
        if (pick < 10) {
            return getMapFromForm();
        }
        if (pick < 30) {
            return Items.field_151045_i;
        }
        if (pick < 50) {
            return Items.field_151166_bC;
        }
        if (pick < 70) {
            return Items.field_151072_bj;
        }
        if (pick < 90) {
            return Items.field_151061_bv;
        }
        return Items.field_151153_ao;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0d);
    }

    public int func_70641_bl() {
        return 1;
    }

    public boolean func_70601_bi() {
        return this.field_70146_Z.nextInt(15) == 0 && (Math.abs(func_180425_c().func_177958_n()) > 800 || Math.abs(func_180425_c().func_177952_p()) > 800);
    }
}
