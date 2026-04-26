package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.murk.EntityDoomsday;
public class ItemDarkIncantation extends Item {
    public ItemDarkIncantation(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_GALAXY);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
            EntityDoomsday doomsday = new EntityDoomsday(worldIn);
            doomsday.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 3.0d, playerIn.field_70161_v);
            worldIn.func_72838_d(doomsday);
            playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "Doomsday: Thank you for freeing me " + playerIn.func_70005_c_() + ", but now you must die."));
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "When used in the Infinite Murk, calls upon Doomsday.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Light_Purple, TextFmt.Italic) + "A dark incantation, of forbidden magic.");
    }
}
