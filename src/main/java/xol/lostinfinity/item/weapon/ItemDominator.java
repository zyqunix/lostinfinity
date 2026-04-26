package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemDominator.class */
public class ItemDominator extends ItemSword implements IMaxAttack {
    public ItemDominator(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        float damageMulti = 3.0f + (3.0f * (1.0f - (target.func_110143_aJ() / target.func_110138_aP())));
        if (attacker.func_110143_aJ() == attacker.func_110138_aP()) {
            float amount = (target.func_110138_aP() / 4.0f) * damageMulti;
            IMaxAttack.dealTrueDamage(attacker, target, amount);
            return true;
        }
        IMaxAttack.dealMaxHealth((Entity) attacker, target, 4, damageMulti);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 75% max health damage per hit.");
        tooltip.add(TextFmt.Red + "Deals up 150% max health damage depending on your target's missing health.");
        tooltip.add(TextFmt.Italic + "(Lower health deals more damage)");
        tooltip.add(TextFmt.Green + "Deals true damage while you are on full life.");
    }
}
