package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.misc.EntityDimensionalMerchant;
import xol.lostinfinity.mob.entity.misc.EntityMarkOfInfiniteDespair;
import xol.lostinfinity.util.data.CustomRayTraceResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemRapidTunnelingDevice.class */
public class ItemRapidTunnelingDevice extends Item implements ICustomRaytrace {
    public ItemRapidTunnelingDevice(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 3, EntityLivingBase.class);
            if (trace_result != null && trace_result.getResultEntity() != null) {
                EntityMarkOfInfiniteDespair mark = new EntityMarkOfInfiniteDespair(worldIn);
                if (trace_result.getResultEntity() instanceof EntityPlayer) {
                    EntityPlayer stuck = (EntityPlayer) trace_result.getResultEntity();
                    mark.setOwner(playerIn);
                    mark.setPlayerTarget(stuck);
                    mark.func_70634_a(stuck.field_70165_t, stuck.field_70163_u + 1.5d, stuck.field_70161_v);
                    worldIn.func_72838_d(mark);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "You have sent " + stuck.func_70005_c_() + " tunneling down to the void."));
                    playerIn.func_184611_a(handIn, ItemStack.field_190927_a);
                }
                if (trace_result.getResultEntity() instanceof EntityDimensionalMerchant) {
                    EntityDimensionalMerchant stuck2 = trace_result.getResultEntity();
                    mark.setOwner(playerIn);
                    mark.func_70634_a(stuck2.field_70165_t, stuck2.field_70163_u - 20.5d, stuck2.field_70161_v);
                    worldIn.func_72838_d(mark);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "The " + stuck2.func_70005_c_() + " decided they would rather not, and traded you an item instead."));
                    playerIn.func_184611_a(handIn, new ItemStack(ItemInit.voidAlteredSpine));
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Attach this to an entity inside the Infinite Murk to make it tunnel downwards.");
    }
}
