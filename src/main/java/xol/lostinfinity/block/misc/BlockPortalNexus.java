package xol.lostinfinity.block.misc;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.tileentity.TileEntityPortalNexus;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockPortalNexus.class */
public class BlockPortalNexus extends BlockBasicBoolState implements ITileEntityProvider {
    public BlockPortalNexus(String name) {
        super(name);
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPortalNexus();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
}
