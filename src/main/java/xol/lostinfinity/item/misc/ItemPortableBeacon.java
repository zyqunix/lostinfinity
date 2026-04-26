package xol.lostinfinity.item.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHeldTick;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemPortableBeacon.class */
public class ItemPortableBeacon extends Item implements IHeldTick {
    public Collection<PotionEffect> getPotionEffects(ItemStack stack) {
        Collection<PotionEffect> potionEffects = new ArrayList<>();
        if (stack.func_77942_o() && stack.func_77978_p().func_150297_b("PotionEffects", 9)) {
            NBTTagList nbttaglist = stack.func_77978_p().func_150295_c("PotionEffects", 10);
            for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
                NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
                PotionEffect potioneffect = PotionEffect.func_82722_b(nbttagcompound);
                if (potioneffect != null && !potionEffects.contains(potioneffect)) {
                    potionEffects.add(potioneffect);
                }
            }
        }
        return potionEffects;
    }

    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K && hand == EnumHand.OFF_HAND && player.field_70173_aa % 40 == 0) {
            Collection<PotionEffect> effects = getPotionEffects(stack);
            if (!effects.isEmpty()) {
                AxisAlignedBB axisAlignedBB = player.func_174813_aQ().func_72314_b(64.0d, 64.0d, 64.0d);
                player.field_70170_p.func_72872_a(EntityLivingBase.class, axisAlignedBB).forEach(p -> {
                    Iterator it = effects.iterator();
                    while (it.hasNext()) {
                        PotionEffect effect = (PotionEffect) it.next();
                        p.func_70690_d(new PotionEffect(effect.func_188419_a(), 400, effect.func_76458_c(), effect.func_82720_e(), effect.func_188418_e()));
                    }
                });
            }
        }
    }

    public void updatePotionEffects(ItemStack input, ItemStack beacon, PotionEffect effect, boolean remove) {
        if (!beacon.func_77942_o()) {
            beacon.func_77982_d(new NBTTagCompound());
        }
        if (!beacon.func_77978_p().func_74764_b("Capacity")) {
            beacon.func_77978_p().func_74768_a("Capacity", 5);
        }
        NBTTagList tagList = new NBTTagList();
        for (PotionEffect effect1 : getPotionEffects(beacon)) {
            if (!remove || effect1 != effect) {
                tagList.func_74742_a(effect1.func_82719_a(new NBTTagCompound()));
            }
        }
        if (!remove) {
            tagList.func_74742_a(effect.func_82719_a(new NBTTagCompound()));
        }
        beacon.func_77978_p().func_74782_a("PotionEffects", tagList);
    }

    public void clearEffects(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("Capacity", 5);
        }
        stack.func_77978_p().func_82580_o("PotionEffects");
    }

    public int getLimit(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!stack.func_77978_p().func_74764_b("Capacity")) {
            stack.func_77978_p().func_74768_a("Capacity", 5);
        }
        return stack.func_77978_p().func_74762_e("Capacity");
    }

    public void setNewLimit(ItemStack stack, int newLimit) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74768_a("Capacity", newLimit);
    }

    public ItemPortableBeacon(String regName) {
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.func_184614_ca().func_77973_b().equals(ItemInit.portableBeacon)) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.PORTABLE_BEACON.getId(), worldIn, 0, 0, 0);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A portable beacon.");
        if (stack.func_77942_o()) {
            Collection<PotionEffect> effects = getPotionEffects(stack);
            tooltip.add(TextFmt.Green + "Effect Capacity " + effects.size() + " / " + stack.func_77978_p().func_74762_e("Capacity"));
            int num = 0;
            if (!effects.isEmpty()) {
                for (PotionEffect effect : effects) {
                    num++;
                    String s1 = I18n.func_74838_a(effect.func_76453_d()).trim();
                    if (effect.func_76458_c() > 0) {
                        s1 = s1 + " " + I18n.func_74838_a("potion.potency." + effect.func_76458_c()).trim();
                    }
                    tooltip.add(num + ". " + (effect.func_188419_a().func_76398_f() ? TextFmt.Red : TextFmt.Blue) + s1);
                }
            }
        }
    }
}
