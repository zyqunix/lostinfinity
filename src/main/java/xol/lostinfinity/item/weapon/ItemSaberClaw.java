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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemSaberClaw.class */
public class ItemSaberClaw extends ItemSword implements IMaxAttack {
    public ItemSaberClaw(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean hasOffhand = attacker.func_184592_cb().func_77973_b() instanceof ItemSaberClaw;
        float damageDealt = IMaxAttack.dealMaxHealth((Entity) attacker, target, 5, hasOffhand ? 3.0f : 1.0f).getDamageDealt();
        if (hasOffhand) {
            attacker.func_70691_i(damageDealt);
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Deals 20% Max Health Damage");
        tooltip.add(TextFmt.Gold + "If also wielding a Saber Claw in the offhand:");
        tooltip.add(TextFmt.Light_Purple + "Deals 60% Max Health Damage Instead");
        tooltip.add(TextFmt.Green + "Lifesteals for 100% of Damage Dealt");
    }
}
