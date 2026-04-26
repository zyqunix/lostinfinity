package xol.lostinfinity.item.tool;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IDeviator;
public class ItemUltimatumRod extends Item implements IDeviator {
    public ItemUltimatumRod(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer attacker, Entity target) {
        if (!superMutateCreature(stack, attacker, target) && !deviateCreature(stack, attacker, target)) {
            ultimatumMutation(stack, attacker, target);
            return true;
        }
        return true;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74757_a("teleporter_active", false);
        }
        if (!playerIn.func_70093_af()) {
            if (!worldIn.field_72995_K && stack.func_77978_p().func_74767_n("teleporter_active")) {
                if (worldIn.field_73011_w.func_186058_p() != DimensionInit.celestialVoid) {
                    DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.celestialVoid, 985.0d, 31.0d, 989.0d);
                } else {
                    DimensionActivator.transferEntity(playerIn, DimensionType.OVERWORLD);
                }
            }
        } else {
            boolean tele_enabled = !stack.func_77978_p().func_74767_n("teleporter_active");
            stack.func_77978_p().func_74757_a("teleporter_active", tele_enabled);
            if (!worldIn.field_72995_K) {
                if (tele_enabled) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Teleporter enabled."));
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLUX_MARK, SoundCategory.MASTER, 1.0f, 1.0f);
                } else {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Teleporter disabled."));
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Dark_Aqua + "Deviates creatures.");
        tooltip.add(TextFmt.Yellow + "Super-mutates deviants.");
        tooltip.add(TextFmt.Aqua + "Deviates powerful creatures when near a deviation energy source.");
        if (stack.func_77942_o()) {
            tooltip.add(TextFmt.Light_Purple + "Teleporter " + (stack.func_77978_p().func_74767_n("teleporter_active") ? "Enabled" : "Disabled"));
        }
    }
}
