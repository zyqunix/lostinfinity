package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.misc.BlockVoidVacuum;
import xol.lostinfinity.block.tileentity.TileEntityVoidVacuum;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomRayTraceResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemElasticThread.class */
public class ItemElasticThread extends Item implements ICustomRaytrace {
    public ItemElasticThread(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K) {
            CustomRayTraceResult trace_result = standardFXTrace(worldIn, playerIn, 2, EnumParticleTypes.SMOKE_NORMAL, EntityLivingBase.class);
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            if (trace_result != null && trace_result.getResultEntity() != null) {
                EntityLivingBase stuck = trace_result.getResultEntity();
                if (stack.func_77978_p().func_74764_b("VacX")) {
                    BlockPos vacPos = new BlockPos(stack.func_77978_p().func_74769_h("VacX"), stack.func_77978_p().func_74769_h("VacY"), stack.func_77978_p().func_74769_h("VacZ"));
                    TileEntity tile_entity = worldIn.func_175625_s(vacPos);
                    Block result_block = worldIn.func_180495_p(vacPos).func_177230_c();
                    if ((result_block instanceof BlockVoidVacuum) && tile_entity != null && (tile_entity instanceof TileEntityVoidVacuum)) {
                        worldIn.func_184133_a((EntityPlayer) null, stuck.func_180425_c(), SoundInit.GENERIC_BOING, SoundCategory.BLOCKS, 1.5f, 1.0f);
                        worldIn.func_184133_a((EntityPlayer) null, vacPos, SoundInit.GENERIC_BOING, SoundCategory.BLOCKS, 1.5f, 1.0f);
                        TileEntityVoidVacuum vacuum = (TileEntityVoidVacuum) tile_entity;
                        vacuum.setTarget(stuck);
                        vacuum.setActive(true);
                        vacuum.resetPulled();
                        playerIn.func_184611_a(handIn, ItemStack.field_190927_a);
                    }
                } else {
                    stack.func_77978_p().func_186854_a("PlayerID", stuck.func_110124_au());
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "You have stuck one end of the thread to " + stuck.func_70005_c_()));
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Green + "A very elastic and sticky thread.");
    }
}
