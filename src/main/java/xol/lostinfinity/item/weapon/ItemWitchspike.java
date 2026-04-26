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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemWitchspike.class */
public class ItemWitchspike extends ItemSword implements IMaxAttack {
    public ItemWitchspike(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_DEVIANTWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int potion_count = target.func_70651_bq().size();
        if (potion_count > 0) {
            IMaxAttack.dealMaxHealth((Entity) attacker, target, 5, potion_count);
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Per Potion Effect on Target:");
        tooltip.add(TextFmt.Gold + "Deals 20% Max Health Damage");
    }
}
