package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.misc.EntitySlimeStrider;
public class ItemBagOfSlime extends Item {
    public ItemBagOfSlime(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K && playerIn.func_70090_H()) {
            for (int sl = 0; sl < 3; sl++) {
                EntitySlimeStrider slime = new EntitySlimeStrider(worldIn);
                slime.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 0.2d, playerIn.field_70161_v);
                worldIn.func_72838_d(slime);
            }
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Green + "Use in water to unleash some quickly growing slime creatures.");
    }
}
