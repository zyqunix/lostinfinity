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
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.projectile.entity.EntityPicklePortal;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemPicklePortal extends ItemCooldown implements ICustomRaytrace {
    public ItemPicklePortal(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!worldIn.field_72995_K && (trace_result = forwardTrace(worldIn, playerIn, 10)) != null) {
                BlockPos resultPos = trace_result.getResultPos();
                EntityPicklePortal portal = new EntityPicklePortal(worldIn);
                portal.func_70107_b(resultPos.func_177958_n(), resultPos.func_177956_o(), resultPos.func_177952_p());
                portal.setCreator(playerIn);
                worldIn.func_72838_d(portal);
                worldIn.func_184133_a((EntityPlayer) null, resultPos, SoundInit.PICKLE_PORTAL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.PICKLE_PORTAL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Opens pickle portals.");
        tooltip.add(TextFmt.Gold + "Summons an army of picklemen to fight for you.");
    }
}
