package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.item.basics.ItemBasic;
public class ItemTrial extends ItemBasic {
    private String mobName;
    public ItemTrial(String regName, CreativeTabs tab, String mobName) {
        super(regName, tab);
        this.mobName = mobName;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
            DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.celestialVoid, 521.0d, 32.0d, 527.0d);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Overworld: Teleport to the Celestial Trial Arena.");
        tooltip.add(TextFmt.Green + "Can be used in the Arena to begin the Trial of the " + this.mobName + ".");
    }
}
