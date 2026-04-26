package xol.lostinfinity.item.basics;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/basics/ItemRawInfinityStone.class */
public class ItemRawInfinityStone extends Item {
    public ItemRawInfinityStone(String regName) {
        func_77637_a(TabsInit.TAB_STONES);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A powerful ancient cube, containing an essence of conciousness.");
    }
}
