package xol.lostinfinity.block.crafting;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.TabsInit;
public class BlockFusionTable extends BlockBasicGui implements ITileEntityProvider {
    public BlockFusionTable(String name) {
        super(name, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }
    public BlockFusionTable(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
        func_149647_a(tab).func_149711_c(3.0f).func_149752_b(10.0f);
        func_149672_a(SoundType.field_185851_d);
    }
    public TileEntity createTileEntity(World worldIn, IBlockState state) {
        return new TileEntityFusionTable();
    }
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.FUSION_TABLE.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gray + "Takes " + TextFmt.Aqua + "Ionite Bars " + TextFmt.Gray + "and " + TextFmt.Aqua + "Inverse Magnecronite" + TextFmt.Gray + ".");
    }
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        if (hasTileEntity(state)) {
            TileEntity tileentity = worldIn.func_175625_s(pos);
            if (tileentity instanceof TileEntityFusionTable) {
                InventoryHelper.func_180175_a(worldIn, pos, (TileEntityFusionTable) tileentity);
                worldIn.func_175666_e(pos, this);
                worldIn.func_175713_t(pos);
            }
        }
    }
}
