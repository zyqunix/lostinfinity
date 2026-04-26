package xol.lostinfinity.item.tool;
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
public class ItemSecurityPass extends ItemBasic {
    private int security;
    public ItemSecurityPass(String regName, int sec) {
        super(regName, TabsInit.TAB_AUXMATS);
        this.security = 0;
        this.security = sec;
    }
    public int getSecurityLevel() {
        return this.security;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Use on security gates to grant security clearance.");
    }
}
