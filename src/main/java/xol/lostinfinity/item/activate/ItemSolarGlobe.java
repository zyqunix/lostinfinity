package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemSolarGlobe.class */
public class ItemSolarGlobe extends Item implements IModeSelect, ISwitchModels {
    public ItemSolarGlobe(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
        func_77625_d(1);
        setModelSwitch("floor", this, 3);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        double yPlace;
        if (!worldIn.field_72995_K) {
            if (worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                int roomRand_X = worldIn.field_73012_v.nextInt(1000) - (1000 / 2);
                int roomRand_Z = worldIn.field_73012_v.nextInt(1000) - (1000 / 2);
                if (roomRand_X == roomRand_Z) {
                    yPlace = 16.0d;
                } else {
                    yPlace = 25.0d;
                }
                ItemStack stack = playerIn.func_184586_b(handIn);
                if (!stack.func_77942_o()) {
                    stack.func_77982_d(new NBTTagCompound());
                }
                DimensionType goTo = DimensionInit.cartographerRealmTop;
                int teleStyle = stack.func_77978_p().func_74762_e("floor_data");
                if (teleStyle == 1) {
                    goTo = DimensionInit.cartographerRealmMid;
                } else if (teleStyle == 2) {
                    goTo = DimensionInit.cartographerRealmBot;
                }
                DimensionActivator.transferEntityWithCoords(playerIn, goTo, 15 + (160 * roomRand_X), yPlace, 15 + (160 * roomRand_Z));
            } else {
                DimensionActivator.transferEntity(playerIn, DimensionType.OVERWORLD);
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Teleports you in and out of the Cartographers Labyrinth.");
        tooltip.add(TextFmt.Gold + "Can select any floor to teleport to.");
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int floor = stack.func_77978_p().func_74762_e("floor_data");
        if (floor == 0) {
            stack.func_77978_p().func_74768_a("floor_data", 1);
            player.func_145747_a(new TextComponentString(TextFmt.Red + "Teleport set to middle (red)."));
        } else if (floor == 1) {
            stack.func_77978_p().func_74768_a("floor_data", 2);
            player.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Teleport set to bottom (purple)."));
        } else {
            stack.func_77978_p().func_74768_a("floor_data", 0);
            player.func_145747_a(new TextComponentString(TextFmt.Aqua + "Teleport set to top (blue)."));
        }
    }
}
