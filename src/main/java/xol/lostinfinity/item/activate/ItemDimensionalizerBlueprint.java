package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.WorldGenStructure;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemDimensionalizerBlueprint.class */
public class ItemDimensionalizerBlueprint extends Item {
    public ItemDimensionalizerBlueprint(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
            if (!worldIn.field_72995_K) {
                new WorldGenStructure("celestialmaze/dimensionalizer_pad").func_180709_b(worldIn, worldIn.field_73012_v, playerIn.func_180425_c().func_177963_a(-4.5d, -2.0d, -4.5d));
            }
            playerIn.func_184586_b(handIn).func_190918_g(1);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Places an 11x11 dimensionalizer pad beneath you.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Dark_Aqua) + "Coordinators not included.");
    }
}
