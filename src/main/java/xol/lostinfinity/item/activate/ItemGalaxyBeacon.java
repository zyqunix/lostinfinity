package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemGalaxyBeacon.class */
public class ItemGalaxyBeacon extends Item {
    public ItemGalaxyBeacon(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_GALAXY);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            if (worldIn.field_73011_w.func_186058_p() != DimensionInit.nonexistence) {
                BlockPos teleport = GalaxyCoordinates.galaxyDungeonEntry();
                DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.nonexistence, teleport.func_177958_n(), teleport.func_177956_o(), teleport.func_177952_p());
            } else {
                DimensionActivator.transferEntity(playerIn, DimensionType.OVERWORLD);
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Teleports you to the Galaxy Dungeon, located in Nonexistence.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Aqua, TextFmt.Italic) + "The Galaxy Dungeon is where ancient warriors used to train.");
        tooltip.add(TextFmt.Italic + "Strength, Agility and Intelligence are all important in battle.");
    }
}
