package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.misc.ItemAugmentSlide;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemAugmenticonBox.class */
public class ItemAugmenticonBox extends ItemBasic {
    public ItemAugmenticonBox() {
        super("augmenticon_box", TabsInit.TAB_AUXMATS);
        func_77625_d(1);
    }

    public static NBTTagList getAugmentList(ItemStack stack) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbt = stack.func_77978_p();
        if (!nbt.func_150297_b("slides", 9)) {
            nbt.func_74782_a("slides", new NBTTagList());
        }
        return nbt.func_150295_c("slides", 3);
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Can have Augment Slides put inside while placed in an Augmentor.");
        tooltip.add(TextFmt.Bold + "Slides Entered: ");
        NBTTagList<NBTTagInt> list = getAugmentList(stack);
        for (NBTTagInt strTag : list) {
            tooltip.add(TextFmt.Italic + ItemAugmentSlide.SlideType.values()[strTag.func_150287_d()].description);
        }
        tooltip.add(TextFmt.Gold + "Drops Added at Ability Count:");
        tooltip.add(TextFmt.Gray + "1: Glowing Seeds" + TextFmt.Reset + TextFmt.Dark_Aqua + ", 4: Ether Chamber" + TextFmt.Reset + TextFmt.Aqua + ", 8: Chemical Synchronizer" + TextFmt.Reset + TextFmt.Dark_Purple + ", 12: Astrometal Amalgamater" + TextFmt.Reset + TextFmt.Light_Purple + ", 16: Rapid Illuminator" + TextFmt.Reset + TextFmt.Green + ", 20: Metamorphosis Core");
    }
}
