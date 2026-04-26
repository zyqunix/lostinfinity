package xol.lostinfinity.block.generator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class BlockTetherCreator extends BlockGenerator implements IMaxAttack {
    public BlockTetherCreator(String name) {
        super(name, 3.0f, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }
    @Override // xol.lostinfinity.block.generator.BlockGenerator
    public void activateGenerator(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, int power_upgrade, int efficiency_upgrade, int range_upgrade, int depth_upgrade, UUID placer_uuid) {
        for (EntityPlayer detected_player : worldIn.func_72872_a(EntityPlayer.class, new AxisAlignedBB(pos).func_72314_b(20.0d + ((double) (range_upgrade * 8)), 10.0d + ((double) (depth_upgrade * 4)), 20.0d + ((double) (range_upgrade * 8))))) {
            if (!detected_player.func_110124_au().equals(placer_uuid) && is_detectable(detected_player)) {
                detected_player.setSpawnDimension(Integer.valueOf(worldIn.field_73011_w.getDimension()));
                detected_player.func_180473_a(detected_player.func_180425_c(), true);
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "Tethers nearby players spawn points when given a Celestial Redstone.");
        tooltip.add(TextFmt.Italic + "Per Module Upgrades:");
        tooltip.add(TextFmt.Red + "NW: Power Modules | NO EFFECT");
        tooltip.add(TextFmt.Aqua + "NE: Efficiency Modules | 16% Reduced Consumption");
        tooltip.add(TextFmt.Light_Purple + "SE: Depth Modules | 4 Blocks Up/Down");
        tooltip.add(TextFmt.Yellow + "SW: Range Modules | 8 Block Radius");
    }
}
