package xol.lostinfinity.block.activator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityDrillConsole;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockDrillConsole.class */
public class BlockDrillConsole extends BlockBasic implements ITileEntityProvider {
    public BlockDrillConsole(String name) {
        super(name);
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityDrillConsole();
    }

    public boolean func_149716_u() {
        return true;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity te;
        ItemStack stack = playerIn.func_184586_b(hand);
        if (stack.func_77973_b() == ItemInit.externalCloviniteBattery && (te = worldIn.func_175625_s(pos)) != null && (te instanceof TileEntityDrillConsole)) {
            ((TileEntityDrillConsole) te).activateDrill(playerIn);
            stack.func_190918_g(1);
            return true;
        }
        return true;
    }
}
