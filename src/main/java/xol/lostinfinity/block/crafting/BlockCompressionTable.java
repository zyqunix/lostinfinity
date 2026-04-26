package xol.lostinfinity.block.crafting;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityCompressionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.TabsInit;
public class BlockCompressionTable extends BlockBasicGui {
    public BlockCompressionTable(String name) {
        this(name, Material.field_151576_e);
    }
    public BlockCompressionTable(String name, Material material) {
        this(name, material, TabsInit.TAB_BLOCKS);
    }
    public BlockCompressionTable(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
        func_149647_a(tab);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityCompressionTable();
    }
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    private TileEntityCompressionTable getTE(World world, BlockPos pos) {
        return (TileEntityCompressionTable) world.func_175625_s(pos);
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.COMPRESSION_TABLE.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "Unleashes an extremely powerful shockwave when given a Celestial Emerald.");
        tooltip.add(TextFmt.Italic + "Per Module Upgrades:");
        tooltip.add(TextFmt.Red + "NW: Power Modules | 22% Damage");
        tooltip.add(TextFmt.Aqua + "NE: Efficiency Modules | 16% Reduced Consumption");
        tooltip.add(TextFmt.Light_Purple + "SE: Depth Modules | 4 Blocks Up/Down");
        tooltip.add(TextFmt.Yellow + "SW: Range Modules | 8 Block Radius");
    }
}
