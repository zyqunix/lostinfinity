package xol.lostinfinity.block.crafting;

import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityChemistryTable;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/crafting/BlockChemistryTable.class */
public class BlockChemistryTable extends BlockBasicGui implements ITileEntityProvider {
    public BlockChemistryTable(String name) {
        this(name, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }

    public BlockChemistryTable(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
        func_149647_a(tab).func_149711_c(3.0f).func_149752_b(10.0f);
        func_149672_a(SoundType.field_185851_d);
    }

    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.CHEMISTRY_TABLE.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }

    @Nullable
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityChemistryTable();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        if (hasTileEntity(state)) {
            TileEntity tileentity = worldIn.func_175625_s(pos);
            if (tileentity instanceof TileEntityChemistryTable) {
                InventoryHelper.func_180175_a(worldIn, pos, (TileEntityChemistryTable) tileentity);
                worldIn.func_175666_e(pos, this);
                worldIn.func_175713_t(pos);
            }
        }
    }
}
