package xol.lostinfinity.item.basics;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class ItemBasicDescription extends ItemBasic {
    private final String[] tooltips;
    public ItemBasicDescription(String regName, CreativeTabs tab, String tooltip) {
        super(regName, tab);
        this.tooltips = tooltip.split("\n");
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(Arrays.asList(this.tooltips));
    }
}
