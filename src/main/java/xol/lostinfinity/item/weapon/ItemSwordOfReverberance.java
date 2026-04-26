package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IMaxReducible;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemSwordOfReverberance.class */
public class ItemSwordOfReverberance extends ItemSword implements IMaxAttack, IMaxReducible {
    public ItemSwordOfReverberance(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!(attacker instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer) attacker;
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("sword_charge", 0);
        }
        int charge = stack.func_77978_p().func_74762_e("sword_charge");
        if (charge >= 10) {
            int percent_multi = Math.floorDiv(charge, 10);
            int divBase = 10;
            if (!(target instanceof EntityPlayer)) {
                divBase = 30;
            }
            IMaxAttack.dealMaxHealth((Entity) player, target, divBase, percent_multi);
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GALAXYFIRE, SoundCategory.MASTER, 2.0f, 1.0f);
            stack.func_77978_p().func_74768_a("sword_charge", 0);
            return false;
        }
        return false;
    }

    @Override // xol.lostinfinity.item.classify.IMaxReducible
    public float reduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("sword_charge", 0);
        }
        float newMulti = reductionMultiplier - 0.1f;
        int chargeGained = Math.round((damage / player.func_110138_aP()) * 100.0f);
        int newChargeTotal = stack.func_77978_p().func_74762_e("sword_charge") + chargeGained;
        if (newChargeTotal > 250) {
            newChargeTotal = 250;
        }
        stack.func_77978_p().func_74768_a("sword_charge", newChargeTotal);
        return newMulti;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "When held, reduces max health damage taken by 10%.");
        tooltip.add(TextFmt.Light_Purple + "Gain 1 charge per 1% of life taken as damage.");
        boolean mark = false;
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("sword_charge")) {
            mark = true;
        }
        if (mark) {
            int charges = stack.func_77978_p().func_74762_e("sword_charge");
            int percent_multi = Math.floorDiv(charges, 10);
            tooltip.add(TextFmt.Gold + "Charges: " + charges + "/250. This deals " + (percent_multi * 10) + "% health damage.");
        }
        tooltip.add(TextFmt.Dark_Gray + "Damage is dealt in 10% increments. Deals 33% damage to non-players.");
        tooltip.add(TextFmt.Green + "Greatly reduces damage from sonic attacks and doubles current charge.");
    }
}
