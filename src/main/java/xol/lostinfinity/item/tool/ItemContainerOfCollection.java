package xol.lostinfinity.item.tool;
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
public class ItemContainerOfCollection extends Item {
    public ItemContainerOfCollection(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Collects the souls of creatures you slay.");
        tooltip.add(TextFmt.Light_Purple + "Mobs grant 1 soul, Players Grant 50");
        if (stack.func_77942_o()) {
            tooltip.add(TextFmt.Gold + "Collection Progress: " + stack.func_77978_p().func_74762_e("charge") + " / 250");
        }
    }
}
