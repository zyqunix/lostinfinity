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
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemStarlitGlobe.class */
public class ItemStarlitGlobe extends Item {
    public ItemStarlitGlobe(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        double yPlace;
        if (!worldIn.field_72995_K) {
            if (worldIn.field_73011_w.func_186058_p() != DimensionInit.cartographerRealmTop) {
                int roomRand_X = worldIn.field_73012_v.nextInt(1000) - (1000 / 2);
                int roomRand_Z = worldIn.field_73012_v.nextInt(1000) - (1000 / 2);
                if (roomRand_X == roomRand_Z) {
                    yPlace = 16.0d;
                } else {
                    yPlace = 25.0d;
                }
                DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.cartographerRealmTop, 15 + (160 * roomRand_X), yPlace, 15 + (160 * roomRand_Z));
            } else {
                DimensionActivator.transferEntity(playerIn, DimensionType.OVERWORLD);
            }
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Teleports you in and out of the Cartographers Labyrinth.");
    }
}
