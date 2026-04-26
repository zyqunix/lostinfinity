package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.tileentity.TileEntityAlignmentDialGame;
import xol.lostinfinity.init.BlockInit;
public class BlockAlignmentTile extends BlockBasicBoolState {
    public BlockAlignmentTile(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            for (int i = -15; i <= 15; i++) {
                for (int j = -15; j <= 15; j++) {
                    BlockPos check = pos.func_177982_a(i, 0, j);
                    if (worldIn.func_180495_p(check).func_177230_c().equals(BlockInit.alignmentDialGame) && worldIn.func_175625_s(check) != null) {
                        TileEntityAlignmentDialGame tileEntity = (TileEntityAlignmentDialGame) worldIn.func_175625_s(check);
                        tileEntity.toggleRing(pos);
                    }
                }
            }
            return true;
        }
        return true;
    }
}
