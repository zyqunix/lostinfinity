package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemDrinkOfDuality.class */
public class ItemDrinkOfDuality extends ItemCooldown implements ISwitchModels, IModeSelect {
    public ItemDrinkOfDuality(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("drink", this, 2);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187664_bz, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            int drinkType = playerIn.func_184586_b(handIn).func_77978_p().func_74762_e("drink_data");
            if (drinkType == 0) {
                playerIn.func_70690_d(new PotionEffect(PotionInit.ADRENALINE, 400, 2));
                playerIn.func_70690_d(new PotionEffect(PotionInit.LAST_BREATH, 400, 2));
            } else {
                playerIn.func_70690_d(new PotionEffect(PotionInit.ULTRAHEAVY, 400, 2));
                playerIn.func_70690_d(new PotionEffect(PotionInit.GRAVITATIONAL, 400, 2));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 50000;
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int attack_style = stack.func_77978_p().func_74762_e("drink_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("drink_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("drink_data", 0);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "When drunk, grants one the following buffs depending on the potion drunk:");
        tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Red) + "Adrenaline III");
        tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Red) + "Last Breath III");
        tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Aqua) + "Ultraheavy III");
        tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Aqua) + "Gravitational III");
    }
}
