package xol.lostinfinity.item.misc;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.ItemSoulbound;
public class ItemGeolocationOrb extends ItemBasic implements ItemSoulbound {
    public ItemGeolocationOrb(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
        func_77625_d(1);
    }
    private String typeToName(int type) {
        switch (type) {
            case 0:
                return "Blue Triangle";
            case 1:
                return "Green Pentagon";
            case 2:
                return "Red Square";
            case 3:
                return "Yellow Circle";
            default:
                return "Data Error";
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(soulBoundMessage());
        tooltip.add(TextFmt.Gold + "Needs to be taken to 3 Geolocators in the Cartographer's Labyrinth");
        if (stack.func_77942_o()) {
            tooltip.add(TextFmt.Green + "Geolocators Remaining: " + stack.func_77978_p().func_74762_e("geocount"));
            tooltip.add(TextFmt.Aqua + "Next Geolocator Type: " + typeToName(stack.func_77978_p().func_74762_e("geolocator")));
        }
    }
}
