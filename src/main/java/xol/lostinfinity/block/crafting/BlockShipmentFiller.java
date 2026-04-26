package xol.lostinfinity.block.crafting;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityShipmentFiller;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
public class BlockShipmentFiller extends BlockBasicGui implements ITileEntityProvider {
    public BlockShipmentFiller(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
        func_149647_a(tab).func_149711_c(3.0f).func_149752_b(10.0f);
        func_149672_a(SoundType.field_185851_d);
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.SHIPMENT_FILLER.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return super.func_180660_a(state, rand, fortune);
    }
    @Nullable
    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileEntityShipmentFiller();
    }
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Nullable
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityShipmentFiller();
    }
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        if (hasTileEntity(state)) {
            TileEntityShipmentFiller tileEntity = (TileEntityShipmentFiller) worldIn.func_175625_s(pos);
            tileEntity.dropInventory();
            worldIn.func_175666_e(pos, this);
            worldIn.func_175713_t(pos);
        }
    }
}
