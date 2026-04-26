package xol.lostinfinity.item.misc;

import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemBoxOfLife.class */
public class ItemBoxOfLife extends ItemBasic {
    public ItemBoxOfLife(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }

    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A box containing the essence of someone's life.");
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("PlayerContained")) {
            tooltip.add(TextFmt.Italic + "Contains the life of: " + stack.func_77978_p().func_74779_i("PlayerContained"));
        }
    }
}
