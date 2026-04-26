package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.basics.ItemBasic;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemShipmentBox.class */
public class ItemShipmentBox extends ItemBasic {
    public ItemShipmentBox(String regName, CreativeTabs tab) {
        super(regName, tab);
        func_77637_a(tab);
        func_77625_d(1);
    }

    public static int getNewWeight(Item item, int weightIn) {
        return weightIn + getItemWeight(item);
    }

    public static int getItemWeight(Item item) {
        if (item == ItemInit.azureLeaf) {
            return 3;
        }
        if (item == ItemInit.constrictingVines) {
            return 7;
        }
        if (item == ItemInit.lucientIngot) {
            return 15;
        }
        if (item == ItemInit.bumbleBlossom) {
            return 10;
        }
        return 0;
    }

    public static ItemStack[] getItemArray() {
        ItemStack[] items = {ItemStack.field_190927_a, new ItemStack(ItemInit.azureLeaf, 1), new ItemStack(ItemInit.constrictingVines, 1), new ItemStack(ItemInit.lucientIngot, 1), new ItemStack(ItemInit.bumbleBlossom, 1)};
        return items;
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int weight = stack.func_77978_p().func_74762_e("weight");
        tooltip.add("Box Weight: " + weight + " lbs");
    }
}
