package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.ITargetable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemSpaceZapper extends ItemCooldown implements ICustomRaytrace, ICustomHoldPose {
    public ItemSpaceZapper(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = simpleBlockTrace(worldIn, playerIn, 60);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_7, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                if (trace_result != null) {
                    BlockPos pos = new BlockPos(trace_result.getGrabbedVector());
                    ITargetable iTargetableFunc_177230_c = worldIn.func_180495_p(pos).func_177230_c();
                    if (iTargetableFunc_177230_c instanceof ITargetable) {
                        iTargetableFunc_177230_c.targetedResult(worldIn, playerIn, pos);
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 100;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }
}
