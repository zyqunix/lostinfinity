package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.IHealReactive;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.item.classify.IModeSelect;
public class ItemAstralBastion extends ItemBasic implements IMaxNullable, IModeSelect, IHealReactive {
    public ItemAstralBastion(String regName) {
        super(regName, TabsInit.TAB_AUXWEP);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Green + "Heals you receive are doubled.");
        tooltip.add(TextFmt.Gold + "Whenever you heal, store that amount (adding to any amount stored).");
        tooltip.add(TextFmt.Underline + "When you take max health damage, you block damage equal to your stored damage.");
    }
    @Override // xol.lostinfinity.item.classify.IHealReactive
    public float itemHealReaction(EntityPlayer player, float healAmount, ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("shield_mode", 0);
            stack.func_77978_p().func_74776_a("stored_damage", 0.0f);
        }
        float storedDamage = stack.func_77978_p().func_74760_g("stored_damage");
        int shieldMode = stack.func_77978_p().func_74762_e("shield_mode");
        float newHeal = healAmount * 2.0f;
        if (newHeal >= player.func_110138_aP() * 0.05f) {
            if (storedDamage + newHeal <= player.func_110138_aP() * 10.0f) {
                stack.func_77978_p().func_74776_a("stored_damage", storedDamage + newHeal);
                if (shieldMode == 1) {
                    player.func_145747_a(new TextComponentString(TextFmt.Black + String.format("%.2f Damage Stored", Float.valueOf(storedDamage + newHeal))));
                }
            } else {
                stack.func_77978_p().func_74776_a("stored_damage", player.func_110138_aP() * 10.0f);
            }
        }
        return newHeal;
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("shield_mode", 0);
            stack.func_77978_p().func_74776_a("stored_damage", 0.0f);
        }
        int shieldMode = stack.func_77978_p().func_74762_e("shield_mode");
        if (shieldMode == 0) {
            player.func_145747_a(new TextComponentString(TextFmt.Italic + "Monitoring stored damage"));
            stack.func_77978_p().func_74768_a("shield_mode", 1);
        } else {
            player.func_145747_a(new TextComponentString(TextFmt.Italic + "No longer monitoring stored damage"));
            stack.func_77978_p().func_74768_a("shield_mode", 0);
        }
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float nullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack) {
        float finalDamage;
        float newStored;
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("shield_mode", 0);
            stack.func_77978_p().func_74776_a("stored_damage", 0.0f);
        }
        float storedDamage = stack.func_77978_p().func_74760_g("stored_damage");
        if (storedDamage > 0.0f && newDamage > 0.0f) {
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 0.6f + (player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        }
        if (storedDamage > player.func_110138_aP() * 10.0f) {
            storedDamage = player.func_110138_aP() * 10.0f;
        }
        float newStored2 = storedDamage;
        if (storedDamage > newDamage) {
            newStored = newStored2 - newDamage;
            finalDamage = 0.0f;
        } else {
            finalDamage = newDamage - storedDamage;
            newStored = 0.0f;
        }
        stack.func_77978_p().func_74776_a("stored_damage", newStored);
        return finalDamage;
    }
}
