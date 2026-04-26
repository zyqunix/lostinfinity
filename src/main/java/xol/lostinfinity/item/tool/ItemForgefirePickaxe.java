package xol.lostinfinity.item.tool;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/tool/ItemForgefirePickaxe.class */
public class ItemForgefirePickaxe extends ItemPickaxe {
    public ItemForgefirePickaxe(Item.ToolMaterial material, String regName) {
        super(material);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        setNoRepair();
        ItemInit.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Can travel to the ancient lost mines of Nonexistence.");
        tooltip.add(TextFmt.Light_Purple + "Used to harvest rocks in the Star Forge mines.");
        if (stack.func_77942_o()) {
            for (Item item : ItemInit.getValidPickChargingCrystals()) {
                List<String> list = Arrays.asList(item.getRegistryName().func_110623_a().split("_"));
                String key = list.get(list.size() - 1);
                if (stack.func_77978_p().func_74764_b(key)) {
                    tooltip.add(TextFmt.Green + key + " -> " + stack.func_77978_p().func_74762_e(key) + " / 250 charges");
                }
            }
        }
    }
}
