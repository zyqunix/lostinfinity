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
import xol.lostinfinity.block.tileentity.TileEntityPolymerizationDevice;
import xol.lostinfinity.init.ItemInit;
public class BlockPolymerizationDevice extends BlockBasic implements ITileEntityProvider {
    public BlockPolymerizationDevice(String name) {
        super(name);
    }
    public boolean func_149716_u() {
        return true;
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPolymerizationDevice();
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.func_175625_s(pos);
        if (tileEntity != null && (tileEntity instanceof TileEntityPolymerizationDevice)) {
            TileEntityPolymerizationDevice deviceEntity = (TileEntityPolymerizationDevice) tileEntity;
            ItemStack stack = playerIn.func_184586_b(hand);
            if (stack.func_77973_b() == ItemInit.rainfallCollectorFull && !deviceEntity.isActivated()) {
                stack.func_190918_g(1);
            }
            if (!worldIn.field_72995_K) {
                deviceEntity.activate(playerIn, hand);
                return true;
            }
            return true;
        }
        return true;
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
}
