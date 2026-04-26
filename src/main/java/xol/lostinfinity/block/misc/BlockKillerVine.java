package xol.lostinfinity.block.misc;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicCrop;
import xol.lostinfinity.block.tileentity.TileEntityKillerVine;
public class BlockKillerVine extends BlockBasicCrop implements ITileEntityProvider {
    public BlockKillerVine(String name) {
        super(name);
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityKillerVine();
    }
    public boolean func_149716_u() {
        return true;
    }
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.field_72995_K && this.field_149758_A) {
            TileEntity tileentity = worldIn.func_175625_s(pos);
            if (tileentity instanceof TileEntityKillerVine) {
                TileEntityKillerVine vineentity = (TileEntityKillerVine) tileentity;
                if (placer instanceof EntityPlayer) {
                    vineentity.setPlacer((EntityPlayer) placer);
                }
            }
        }
        super.func_180633_a(worldIn, pos, state, placer, stack);
    }
}
